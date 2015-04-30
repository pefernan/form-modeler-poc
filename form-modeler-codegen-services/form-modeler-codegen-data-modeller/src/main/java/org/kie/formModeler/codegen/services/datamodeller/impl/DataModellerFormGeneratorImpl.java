package org.kie.formModeler.codegen.services.datamodeller.impl;

import java.util.Date;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.jboss.errai.security.shared.api.identity.User;
import org.kie.formModeler.codegen.SourceGenerationContext;
import org.kie.formModeler.codegen.model.FormModelSourceGenerator;
import org.kie.formModeler.codegen.services.datamodeller.DataModellerFormGenerator;
import org.kie.formModeler.codegen.view.FormHTMLTemplateSourceGenerator;
import org.kie.formModeler.codegen.view.FormJavaTemplateSourceGenerator;
import org.kie.formModeler.model.DataHolder;
import org.kie.formModeler.model.FieldDefinition;
import org.kie.formModeler.model.FormDefinition;
import org.kie.formModeler.model.impl.AbstractIntputFieldDefinition;
import org.kie.formModeler.service.FieldManager;
import org.kie.workbench.common.screens.datamodeller.model.DataObjectTO;
import org.kie.workbench.common.screens.datamodeller.model.ObjectPropertyTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uberfire.backend.server.util.Paths;
import org.uberfire.io.IOService;
import org.uberfire.java.nio.base.options.CommentedOption;
import org.uberfire.java.nio.file.Path;
import org.uberfire.rpc.SessionInfo;

/**
 * Created by pefernan on 4/29/15.
 */
public class DataModellerFormGeneratorImpl implements DataModellerFormGenerator {
    private static transient Logger log = LoggerFactory.getLogger( DataModellerFormGeneratorImpl.class );

    public static final String[] RESTRICTED_PROPERTY_NAMES = new String[]{"serialVersionUID"};

    @Inject
    protected FieldManager fieldManager;

    @Inject
    @Named("ioStrategy")
    private IOService ioService;

    @Inject
    private SessionInfo sessionInfo;

    @Inject
    private User identity;

    @Inject
    private FormModelSourceGenerator modelSourceGenerator;

    @Inject
    private FormJavaTemplateSourceGenerator javaTemplateSourceGenerator;

    @Inject
    private FormHTMLTemplateSourceGenerator htmlTemplateSourceGenerator;

    @Override
    public void generateFormForDataObject( DataObjectTO dataObject ) {
        FormDefinition form = new FormDefinition();
        form.setName( dataObject.getName() );

        String holderName = WordUtils.uncapitalize( dataObject.getName() );

        DataHolder holder = new DataHolder( holderName, dataObject.getClassName() );

        form.addDataHolder( holder );

        for (ObjectPropertyTO property : dataObject.getProperties()) {
            if ( ArrayUtils.contains(RESTRICTED_PROPERTY_NAMES, property.getName()) ) continue;

            String propertyName = holderName + "_" + property.getName();
            FieldDefinition field = fieldManager.getDefinitionByValueType( property.getClassName() );

            if (field == null) continue;

            form.addField( field );

            field.setName( propertyName );
            field.setLabel( propertyName );
            field.setBindingExpression( holderName + "." + property.getName() );

            if (field instanceof AbstractIntputFieldDefinition) {
                ((AbstractIntputFieldDefinition) field).setPlaceHolder( propertyName );
            }
        }

        SourceGenerationContext context = new SourceGenerationContext( form, dataObject.getPackageName(), dataObject.getPackageName() );

        String modelSource = modelSourceGenerator.generateFormModelSource( context );
        String javaTemplate = javaTemplateSourceGenerator.generateJavaTemplateSource( context );
        String htmlTemplate = htmlTemplateSourceGenerator.generateHTMLTemplateSource( context );

        if ( StringUtils.isEmpty( modelSource ) || StringUtils.isEmpty( javaTemplate ) || StringUtils.isEmpty( htmlTemplate )) {
            log.warn( "Unable to generate the required form assets for Data Object: {}", dataObject.getClassName() );
            return;
        }


        Path path = Paths.convert( dataObject.getPath() );
        Path parent = path.getParent();

        Path modelPath = parent.resolve( context.getModelName() + ".java" );

        ioService.write( modelPath, modelSource, makeCommentedOption( "Added Java Source for Form Model '" + dataObject.getClassName() + "'" ) );

        Path javaPath = parent.resolve( context.getViewName() + ".java" );

        ioService.write( javaPath, javaTemplate, makeCommentedOption( "Added Java Source for Form Template '" + dataObject.getClassName() + "'" ) );

        Path htmlPath = parent.resolve( context.getViewName() + ".html" );

        ioService.write( htmlPath, htmlTemplate, makeCommentedOption( "Added HTML Source for Form Template '" + dataObject.getClassName() + "'" ) );
    }

    public CommentedOption makeCommentedOption( String commitMessage ) {
        final String name = identity.getIdentifier();
        final Date when = new Date();

        final CommentedOption option = new CommentedOption( sessionInfo.getId(),
                name,
                null,
                commitMessage,
                when );
        return option;
    }
}
