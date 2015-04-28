package org.kie.formModeler.codegen.roaster.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.formModeler.codegen.SourceGenerationContext;
import org.kie.formModeler.codegen.model.FormModelSourceGenerator;
import org.kie.formModeler.codegen.roaster.test.utils.WeldJUnit4Runner;
import org.kie.formModeler.codegen.view.FormJavaTemplateSourceGenerator;
import org.kie.formModeler.model.DataHolder;
import org.kie.formModeler.model.FormDefinition;
import org.kie.formModeler.model.impl.CheckBoxFieldDefinition;
import org.kie.formModeler.model.impl.DateBoxFieldDefinition;
import org.kie.formModeler.model.impl.TextAreaFieldDefinition;
import org.kie.formModeler.model.impl.TextBoxFieldDefinition;

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
    protected FormJavaTemplateSourceGenerator templateSourceGenerator;

    @Test
    public void testFormSourcesGeneration() {
        FormDefinition formDefinition = generateFormDefinition();


        SourceGenerationContext context = new SourceGenerationContext( formDefinition, modelPack, "MyFormExampleFormModel", viewPack, formDefinition.getName() );

        String model = modelSourceGenerator.generateFormModelSource( context );
        //System.out.println(model);
        assertNotNull( model );

        String javaTemplate = templateSourceGenerator.generateJavaTemplateSource( context );

        System.out.println(javaTemplate);
        assertNotNull( javaTemplate );


    }

    protected FormDefinition generateFormDefinition()  {
        FormDefinition formDefinition = new FormDefinition();
        formDefinition.setName( "MyFormExample" );

        DataHolder person = new DataHolder( "person", "org.kie.formModeler.example.model.Person" );
        DataHolder identifier = new DataHolder( "identifier", String.class.getName() );

        formDefinition.addDataHolder( person );
        formDefinition.addDataHolder( identifier );

        TextBoxFieldDefinition id = new TextBoxFieldDefinition();
        id.setName( "identifier" );
        id.setBindingExpression( "identifier" );

        TextBoxFieldDefinition name = new TextBoxFieldDefinition();
        name.setName( "person_name" );
        name.setBindingExpression( "person.name" );

        TextBoxFieldDefinition surname = new TextBoxFieldDefinition();
        surname.setName( "person_surname" );
        surname.setBindingExpression( "person.surname" );

        DateBoxFieldDefinition birthday = new DateBoxFieldDefinition();
        birthday.setBindingExpression( "person.birthday" );
        birthday.setName( "person_birthday" );

        TextAreaFieldDefinition address = new TextAreaFieldDefinition();
        address.setName( "person_address" );
        address.setBindingExpression( "person.address" );

        CheckBoxFieldDefinition married = new CheckBoxFieldDefinition();
        married.setName( "person_married" );
        married.setBindingExpression( "person.married" );

        formDefinition.addField( id );
        formDefinition.addField( name );
        formDefinition.addField( surname );
        formDefinition.addField( birthday );
        formDefinition.addField( address );
        formDefinition.addField( married );



        return formDefinition;
    }
}
