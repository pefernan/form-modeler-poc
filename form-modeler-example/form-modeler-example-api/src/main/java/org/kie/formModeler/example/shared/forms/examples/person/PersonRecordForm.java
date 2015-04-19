package org.kie.formModeler.example.shared.forms.examples.person;

import java.util.Date;
import javax.inject.Named;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.kie.formModeler.example.model.Person;
import org.kie.formModeler.model.DataHolderMeta;
import org.kie.formModeler.model.FieldMeta;
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
public class PersonRecordForm extends FormMeta {

    @DataHolder( name="identifier", type = DataHolderType.BASIC )
    @NotEmpty
    private String identifier;

    @DataHolder( name = "person", type = DataHolderType.MODEL)
    @Valid
    private Person person;

    public PersonRecordForm() {

    }

    @FormConstructor
    public PersonRecordForm( @MapsTo("identifier") String identifier, @MapsTo("person") Person person ) {
        this.identifier = identifier;
        this.person = person;
    }

    @Override
    protected void init() {
        fieldNames.add( "identifier" );
        fieldNames.add( "person_name" );
        fieldNames.add( "person_surname" );
        fieldNames.add( "person_birthday" );
        fieldNames.add( "person_address" );
    }

    @Override
    public FieldMeta[] getFields() {
        FieldMeta[] metas = new FieldMeta[5];
        metas[0] = new FieldMeta<String>("identifier", "identifier");
        metas[1] = new FieldMeta<String>("person_name", "person.name");
        metas[2] = new FieldMeta<String>("person_surname", "person.surname");
        metas[3] = new FieldMeta<Date>("person_birthday", "person.birthday");
        metas[4] = new FieldMeta<String>("person_address", "person.address");
        return new FieldMeta[ 0 ];
    }

    @Override
    public DataHolderMeta[] getDataHolders() {
        DataHolderMeta[] metas = new DataHolderMeta[2];
        metas[0] = new DataHolderMeta<String>("identifier", identifier, DataHolderType.BASIC);
        metas[1] = new DataHolderMeta<Person>("person", person, DataHolderType.MODEL);
        return metas;
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
