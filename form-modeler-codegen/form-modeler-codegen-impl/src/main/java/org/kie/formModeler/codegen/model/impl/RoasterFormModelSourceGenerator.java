package org.kie.formModeler.codegen.model.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.lang.ArrayUtils;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.kie.formModeler.codegen.SourceGenerationContext;
import org.kie.formModeler.codegen.model.FormModelSourceGenerator;
import org.kie.formModeler.model.DataHolder;
import org.kie.formModeler.service.FieldManager;
import org.kie.workbench.common.services.shared.project.KieProjectService;

import static org.kie.formModeler.codegen.util.SourceGenerationUtil.*;

/**
 * Created by pefernan on 4/27/15.
 */
@ApplicationScoped
public class RoasterFormModelSourceGenerator implements FormModelSourceGenerator {

    @Inject
    private KieProjectService projectService;

    @Override
    public String generateFormModelSource( SourceGenerationContext context ) {

        JavaClassSource modelClass = Roaster.create( JavaClassSource.class );
        modelClass.setPackage( projectService.resolvePackage( context.getPath() ).getPackageName() )
                .setPublic()
                .setName( context.getModelName() );

        modelClass.setSuperType( FORM_MODEL_CLASS );

        modelClass.addAnnotation( ERRAI_PORTABLE );
        modelClass.addAnnotation( ERRAI_BINDABLE );
        modelClass.addAnnotation( INJECT_NAMED ).setStringValue( context.getModelName() );

        modelClass.addMethod()
                .setConstructor( true )
                .setPublic()
                .setBody( "" );

        MethodSource<JavaClassSource> constructor = modelClass
                .addMethod()
                .setConstructor( true )
                .setPublic();
        StringBuffer source = new StringBuffer(  );

        for ( DataHolder dataHolder : context.getFormDefinition().getDataHolders() ) {
            FieldSource<JavaClassSource> modelField = modelClass.addProperty( dataHolder.getType(), dataHolder.getName() ).getField();

            if ( ArrayUtils.contains( FieldManager.BASIC_TYPES, dataHolder.getType() ) ) {
                modelField.addAnnotation( VALIDATION_NOT_NULL );
            } else {
                modelField.addAnnotation( VALIDATION_VALID );
            }

            constructor.addParameter( dataHolder.getType(), dataHolder.getName() ).addAnnotation( ERRAI_MAPS_TO ).setStringValue( dataHolder.getName() );
            source.append( "this." )
                    .append( dataHolder.getName() )
                    .append( " = " )
                    .append( dataHolder.getName() )
                    .append( ";" );
        }

        constructor.setBody( source.toString() );

        return modelClass.toString();
    }
}
