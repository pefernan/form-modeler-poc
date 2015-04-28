package org.kie.formModeler.codegen.roaster.view;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.source.ParameterSource;
import org.kie.formModeler.codegen.SourceGenerationContext;
import org.kie.formModeler.codegen.view.FormJavaTemplateSourceGenerator;
import org.kie.formModeler.model.FieldDefinition;

import static org.kie.formModeler.codegen.util.SourceGenerationUtil.*;

/**
 * Created by pefernan on 4/28/15.
 */
@ApplicationScoped
public class FormJavaTemplateSourceGeneratorRoaster implements FormJavaTemplateSourceGenerator {
    public static final String READONLY_PARAM = "readOnly";

    @Inject
    private Instance<InputCreatorHelper> creatorInstances;

    private Map<String, InputCreatorHelper> creatorHelpers = new HashMap<String, InputCreatorHelper>(  );

    @PostConstruct
    protected void init() {
        for (InputCreatorHelper helper : creatorInstances) {
            creatorHelpers.put( helper.getSupportedFieldType(), helper );
        }
    }

    @Override
    public String generateJavaTemplateSource( SourceGenerationContext context ) {
        JavaClassSource viewClass = Roaster.create( JavaClassSource.class );

        viewClass.setPackage( context.getViewPackage() )
                .setPublic()
                .setName( context.getViewName() )
                .setSuperType( FORM_VIEW_CLASS + "<" + context.getModelName() + ">" );


        viewClass.addImport( context.getModelPackage() + "." + context.getModelName() );

        viewClass.addAnnotation( ERRAI_TEMPLATED );
        viewClass.addAnnotation( INJECT_NAMED ).setStringValue( context.getFormDefinition().getName() );

        StringBuffer inputNames = new StringBuffer(  );
        StringBuffer readOnlyMethod = new StringBuffer(  );

        for ( FieldDefinition fieldDefinition : context.getFormDefinition().getFields() ) {
            InputCreatorHelper helper = creatorHelpers.get( fieldDefinition.getCode() );
            if (helper == null) continue;

            FieldSource<JavaClassSource> field = viewClass.addProperty( helper.getInputWidget(), fieldDefinition.getName() ).getField();
            field.setPrivate();

            if (helper.isInjectable()) field.addAnnotation( INJECT_INJECT );
            else field.setLiteralInitializer( helper.getInitLiteral() );

            field.addAnnotation( ERRAI_BOUND ).setStringValue( "property", fieldDefinition.getBindingExpression() );

            inputNames.append( "inputNames.add(\"" ).append( fieldDefinition.getName() ).append( "\");" );
            readOnlyMethod.append( helper.getReadonlyMethod( fieldDefinition.getName(), READONLY_PARAM ) );
        }

        viewClass.addMethod()
                .setName( "initInputNames" )
                .setBody( inputNames.toString() )
                .setPublic()
                .setReturnTypeVoid()
                .addAnnotation( JAVA_LANG_OVERRIDE );

        MethodSource<JavaClassSource> readonlyMethod = viewClass.addMethod()
                .setName( "setReadOnly" )
                .setBody( readOnlyMethod.toString() )
                .setPublic()
                .setReturnTypeVoid();
        readonlyMethod.addParameter( boolean.class, READONLY_PARAM );
        readonlyMethod.addAnnotation( JAVA_LANG_OVERRIDE );

        return viewClass.toString();
    }
}
