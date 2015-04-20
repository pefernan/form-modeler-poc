package org.kie.formModeler.example.shared.forms.examples.person;

import javax.inject.Named;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.kie.formModeler.example.model.Person;
import org.kie.formModeler.model.DataHolderFieldMeta;
import org.kie.formModeler.model.DataHolderMeta;
import org.kie.formModeler.model.FormMeta;
import org.kie.formModeler.model.annotation.DataHolder;
import org.kie.formModeler.model.annotation.DataHolderType;
import org.kie.formModeler.model.annotation.Form;
import org.kie.formModeler.model.annotation.FormConstructor;

/**
 * Created by pefernan on 4/14/15.
 */
@Form
@Bindable
@Portable
@Named("PersonForm")
public class PersonForm extends FormMeta {
    public static final IdentifierDataHolderMeta _IDENTIFIER = new IdentifierDataHolderMeta();
    public static final PersonDataHolderMeta _PERSON = new PersonDataHolderMeta();

    @DataHolder( name="identifier", type = DataHolderType.BASIC )
    @NotEmpty
    private String identifier;

    @DataHolder( name = "person", type = DataHolderType.MODEL)
    @Valid
    private Person person;

    public PersonForm() {

    }

    @FormConstructor
    public PersonForm( @MapsTo("identifier") String identifier, @MapsTo("person") Person person ) {
        this.identifier = identifier;
        this.person = person;
    }

    @Override
    protected void init() {
        fieldNames.add( "identifier" );
        fieldNames.add( "person_name" );
        fieldNames.add( "person_surname" );
        fieldNames.add( "person_address" );

        dataHolderMetas.add( _IDENTIFIER );
        dataHolderMetas.add( _PERSON );
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

    @Portable
    public static class IdentifierDataHolderMeta extends DataHolderMeta<PersonForm, String> {
        public static final IdentifierDataHolderFieldMeta _IDENTIFIER = new IdentifierDataHolderFieldMeta();

        @Override
        public String getName() {
            return "identifier";
        }

        @Override
        public String getModel( PersonForm formMeta ) {
            return formMeta.getIdentifier();
        }

        @Override
        public DataHolderType getType() {
            return DataHolderType.BASIC;
        }
    }

    @Portable
    public static class IdentifierDataHolderFieldMeta extends DataHolderFieldMeta<PersonForm, String> {

        @Override
        public String getName() {
            return "identifier";
        }

        @Override
        public String getValue( PersonForm meta ) {
            return meta.getIdentifier();
        }

        @Override
        public void setValue( PersonForm meta, String value ) {
            meta.setIdentifier( value );
        }
    }

    @Portable
    public static class PersonDataHolderMeta extends DataHolderMeta<PersonForm, Person> {
        public static final PersonNameDataHolderFieldMeta _NAME = new PersonNameDataHolderFieldMeta( _PERSON );
        public static final PersonSurnameDataHolderFieldMeta _SURNAME = new PersonSurnameDataHolderFieldMeta( _PERSON );
        public static final PersonAddressDataHolderFieldMeta _ADDRESS = new PersonAddressDataHolderFieldMeta( _PERSON );

        @Override
        public String getName() {
            return "person";
        }

        @Override
        public Person getModel( PersonForm formMeta ) {
            return formMeta.getPerson();
        }

        @Override
        public DataHolderType getType() {
            return DataHolderType.MODEL;
        }
    }

    @Portable
    public static class PersonNameDataHolderFieldMeta extends DataHolderFieldMeta<PersonForm, String> {
        private PersonDataHolderMeta parent;

        public PersonNameDataHolderFieldMeta( @MapsTo("parent") PersonDataHolderMeta parent ) {
            this.parent = parent;
        }

        @Override
        public String getName() {
            return "person_name";
        }

        @Override
        public String getValue( PersonForm meta ) {
            return parent.getModel( meta ).getName();
        }

        @Override
        public void setValue( PersonForm meta, String value ) {
            parent.getModel( meta ).setName( value );
        }
    }

    @Portable
    public static class PersonSurnameDataHolderFieldMeta extends DataHolderFieldMeta<PersonForm, String> {
        private PersonDataHolderMeta parent;

        public PersonSurnameDataHolderFieldMeta( @MapsTo("parent") PersonDataHolderMeta parent ) {
            this.parent = parent;
        }

        @Override
        public String getName() {
            return "person_surname";
        }

        @Override
        public String getValue( PersonForm meta ) {
            return parent.getModel( meta ).getSurname();
        }

        @Override
        public void setValue( PersonForm meta, String value ) {
            parent.getModel( meta ).setSurname( value );
        }
    }

    @Portable
    public static class PersonAddressDataHolderFieldMeta extends DataHolderFieldMeta<PersonForm, String> {
        private PersonDataHolderMeta parent;

        public PersonAddressDataHolderFieldMeta( @MapsTo("parent") PersonDataHolderMeta parent ) {
            this.parent = parent;
        }

        @Override
        public String getName() {
            return "person_address";
        }

        @Override
        public String getValue( PersonForm meta ) {
            return parent.getModel( meta ).getAddress();
        }

        @Override
        public void setValue( PersonForm meta, String value ) {
            parent.getModel( meta ).setAddress( value );
        }
    }
}
