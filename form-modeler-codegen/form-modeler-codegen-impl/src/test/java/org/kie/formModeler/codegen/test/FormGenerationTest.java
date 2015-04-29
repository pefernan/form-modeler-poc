package org.kie.formModeler.codegen.test;

import java.util.Date;
import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.formModeler.codegen.SourceGenerationContext;
import org.kie.formModeler.codegen.model.FormModelSourceGenerator;
import org.kie.formModeler.codegen.test.utils.WeldJUnit4Runner;
import org.kie.formModeler.codegen.view.FormHTMLTemplateSourceGenerator;
import org.kie.formModeler.codegen.view.FormJavaTemplateSourceGenerator;
import org.kie.formModeler.model.DataHolder;
import org.kie.formModeler.model.FieldDefinition;
import org.kie.formModeler.model.FormDefinition;
import org.kie.formModeler.model.impl.DateBoxFieldDefinition;
import org.kie.formModeler.model.impl.IntBoxFieldDefinition;
import org.kie.formModeler.model.impl.TextAreaFieldDefinition;
import org.kie.formModeler.model.impl.TextBoxFieldDefinition;
import org.kie.formModeler.service.FieldManager;

import static org.junit.Assert.*;

/**
 * Created by pefernan on 4/28/15.
 */
@RunWith(WeldJUnit4Runner.class)
public class FormGenerationTest {
    protected String modelPack = "org.kie.formModeler.example.model";
    protected String viewPack = "org.kie.formModeler.example.client.view";

    @Inject
    protected FormModelSourceGenerator modelSourceGenerator;

    @Inject
    protected FormJavaTemplateSourceGenerator javaTemplateSourceGenerator;

    @Inject
    protected FormHTMLTemplateSourceGenerator htmlTemplateSourceGenerator;

    @Inject
    FieldManager fieldManager;

    @Test
    public void testFormSourcesGeneration() {
        FormDefinition formDefinition = generateFormDefinition();

        assertNotNull( formDefinition );

        SourceGenerationContext context = new SourceGenerationContext( formDefinition, modelPack, "MyFormExampleFormModel", viewPack, formDefinition.getName() );

        String model = modelSourceGenerator.generateFormModelSource( context );
        //System.out.println(model);
        assertNotNull( model );

        String javaTemplate = javaTemplateSourceGenerator.generateJavaTemplateSource( context );
        //System.out.println(javaTemplate);
        assertNotNull( javaTemplate );

        String htmlTemplate = htmlTemplateSourceGenerator.generateHTMLTemplateSource( context );

        assertNotNull( htmlTemplate );
    }

    protected FormDefinition generateFormDefinition()  {
        FormDefinition formDefinition = new FormDefinition();
        formDefinition.setName( "MyFormExample" );

        DataHolder person = new DataHolder( "person", "org.kie.formModeler.example.model.Person" );
        DataHolder identifier = new DataHolder( "identifier", String.class.getName() );

        formDefinition.addDataHolder( person );
        formDefinition.addDataHolder( identifier );

        IntBoxFieldDefinition id = ( IntBoxFieldDefinition ) fieldManager.getDefinitionByValueType( Integer.class );
        id.setName( "identifier" );
        id.setBindingExpression( "identifier" );
        id.setLabel( "Identifier:" );
        id.setPlaceHolder( "Identifier" );

        TextBoxFieldDefinition name = ( TextBoxFieldDefinition ) fieldManager.getDefinitionByType( TextBoxFieldDefinition.class.getName() );
        name.setName( "person_name" );
        name.setBindingExpression( "person.name" );
        name.setLabel( "Person Name:" );
        name.setPlaceHolder( "Person Name" );

        TextBoxFieldDefinition surname = ( TextBoxFieldDefinition ) fieldManager.getDefinitionByType( TextBoxFieldDefinition.class.getName() );
        surname.setName( "person_surname" );
        surname.setBindingExpression( "person.surname" );
        surname.setLabel( "Person Surname:" );
        surname.setPlaceHolder( "Person Surname" );

        DateBoxFieldDefinition birthday = ( DateBoxFieldDefinition ) fieldManager.getDefinitionByValueType( Date.class );
        birthday.setBindingExpression( "person.birthday" );
        birthday.setName( "person_birthday" );
        birthday.setLabel( "Person Birthday:" );
        birthday.setPlaceHolder( "Person Birthday" );

        TextAreaFieldDefinition address = ( TextAreaFieldDefinition ) fieldManager.getDefinitionByType( TextAreaFieldDefinition.class.getName() );
        address.setName( "person_address" );
        address.setBindingExpression( "person.address" );
        address.setLabel( "Person Address:" );
        address.setPlaceHolder( "Person Address" );

        FieldDefinition married = fieldManager.getDefinitionByValueType( Boolean.class );
        married.setName( "person_married" );
        married.setBindingExpression( "person.married" );
        married.setLabel( "Married" );

        formDefinition.addField( id );
        formDefinition.addField( name );
        formDefinition.addField( surname );
        formDefinition.addField( birthday );
        formDefinition.addField( address );
        formDefinition.addField( married );

        return formDefinition;
    }
}
