package org.kie.formModeler.example.shared.forms.examples.person;

import javax.inject.Named;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.kie.formModeler.example.model.Person;
import org.kie.formModeler.model.meta.FormModel;

/**
 * Created by pefernan on 4/14/15.
 */
@Bindable
@Portable
@Named("PersonForm")
public class PersonFormModel extends FormModel {

    @NotEmpty
    private String identifier;

    @Valid
    private Person person;

    public PersonFormModel() {

    }

    public PersonFormModel( @MapsTo("identifier") String identifier, @MapsTo("person") Person person ) {
        this.identifier = identifier;
        this.person = person;
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
