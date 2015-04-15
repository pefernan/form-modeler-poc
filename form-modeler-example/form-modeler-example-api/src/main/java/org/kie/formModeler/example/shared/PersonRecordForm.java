package org.kie.formModeler.example.shared;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.errai.databinding.client.api.Bindable;
import org.kie.formModeler.example.model.Person;
import org.kie.formModeler.model.FormDefinition;
import org.kie.formModeler.model.annotation.DataHolder;
import org.kie.formModeler.model.annotation.DataHolderType;
import org.kie.formModeler.model.annotation.Form;
import org.kie.formModeler.model.annotation.FormConstructor;
import org.kie.formModeler.model.annotation.ParamDataHolder;
import org.kie.formModeler.model.impl.DateBox;
import org.kie.formModeler.model.impl.TextBox;

/**
 * Created by pefernan on 4/14/15.
 */
@Form( name= "PersonForm" )
@Bindable
public class PersonRecordForm extends FormDefinition {

    @DataHolder( name="identifier", type = DataHolderType.BASIC )
    @NotEmpty
    private String identifier;

    @DataHolder( name = "person", type = DataHolderType.MODEL)
    @Valid
    private Person person;

    public PersonRecordForm() {

    }

    @FormConstructor
    public PersonRecordForm( @ParamDataHolder(name = "identifier") String identifier, @ParamDataHolder(name = "person") Person person ) {
        this.identifier = identifier;
        this.person = person;
    }

    @Override
    protected void initFields() {
        TextBox id = new TextBox();
        id.setName( "identifier" );
        id.setLabel( "Identifier:" );
        id.setSize( 15 );
        id.setMaxLength( 15 );
        id.setBindingExpression( "identifier" );

        TextBox name = new TextBox();
        name.setName( "person_name" );
        name.setLabel( "Name:" );
        name.setSize( 15 );
        name.setMaxLength( 15 );
        name.setBindingExpression( "person.name" );

        TextBox surname = new TextBox();
        surname.setName( "person_surname" );
        surname.setLabel( "Surname:" );
        surname.setSize( 25 );
        surname.setMaxLength( 25 );
        surname.setBindingExpression( "person.surname" );

        DateBox birthday = new DateBox();
        birthday.setName( "person_birthday" );
        birthday.setLabel( "Birthday:" );
        birthday.setSize( 15 );
        birthday.setMaxLength( 15 );
        birthday.setBindingExpression( "person.birthday" );

        TextBox address = new TextBox();
        address.setName( "person_address" );
        address.setLabel( "Address:" );
        address.setSize( 25 );
        address.setMaxLength( 25 );
        address.setBindingExpression( "person.address" );

        fields.add( id );
        fields.add( name );
        fields.add( surname );
        fields.add( birthday );
        fields.add( address );
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier( String identifier ) {
        this.identifier = identifier;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson( Person person ) {
        this.person = person;
    }
}
