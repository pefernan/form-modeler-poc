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
import org.kie.formModeler.model.annotation.FormConstructor;

/**
 * Created by pefernan on 4/14/15.
 */
@org.kie.formModeler.model.annotation.Form
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
        DataHolderMeta<String> idHolder = new DataHolderMeta<String>("identifier", identifier, DataHolderType.BASIC);
        DataHolderMeta<Person> personHolder = new DataHolderMeta<Person>("person", person, DataHolderType.MODEL);

        dataHolders.put( "identifier", idHolder );
        dataHolders.put( "person", personHolder );

        FieldMeta<String> id = new FieldMeta<String>("identifier", "identifier");
        FieldMeta<String> name = new FieldMeta<String>("person_name", "person.name");
        FieldMeta<String> surname = new FieldMeta<String>("person_surname", "person.surname");
        FieldMeta<Date> birthday = new FieldMeta<Date>("person_birthday", "person.birthday");
        FieldMeta<String> address = new FieldMeta<String>("person_address", "person.address");

        fields.put( "identifier", id );
        fields.put( "person_name", name );
        fields.put( "person_surname", surname );
        fields.put( "person_birthday", birthday );
        fields.put( "person_address", address );
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
