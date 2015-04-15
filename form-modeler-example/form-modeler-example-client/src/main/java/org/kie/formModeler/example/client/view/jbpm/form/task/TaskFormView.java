package org.kie.formModeler.example.client.view.jbpm.form.task;

import java.util.Set;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.datepicker.client.ui.DateBox;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Composite;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.InitialState;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.kie.formModeler.example.shared.PersonRecordForm;
import org.kie.formModeler.model.FieldDefinition;

/**
 * Created by pefernan on 3/24/15.
 */
@Templated
public class TaskFormView extends Composite {

    @Inject
    protected Validator validator;

    protected DataBinder<PersonRecordForm> binder;

    private PersonRecordForm model;

    @Inject
    @DataField
    private TextBox identifier;

    @Inject
    @DataField
    private TextBox person_name;

    @Inject
    @DataField
    private TextBox person_surname;

    @Inject
    @DataField
    private DateBox person_birthday;

    @Inject
    @DataField
    private TextArea person_address;


    @PostConstruct
    protected void init() {

    }

    public void loadModel(PersonRecordForm model) {
        binder = DataBinder.forModel( model, InitialState.FROM_MODEL );

        binder.bind( identifier, "identifier" );
        binder.bind( person_name, "person.name" );
        binder.bind( person_surname, "person.surname");
        binder.bind( person_birthday, "person.birthday");
        binder.bind( person_address, "person.address");

        this.model = binder.getModel();
        clearFieldErrors();
    }

    protected void clearFieldErrors() {
        for (FieldDefinition field : model.getFields()) {
            Element group = Document.get().getElementById( field.getName() + "_control_group" );
            Element helpBlock = Document.get().getElementById( field.getName() + "_help_block" );
            if ( group != null ) group.removeClassName( "error" );
            if ( helpBlock != null ) helpBlock.setInnerHTML( "" );
        }
    }

    public boolean validate() {

        boolean isValid = true;

        clearFieldErrors();

        Set<ConstraintViolation<PersonRecordForm>> result = validator.validate( model );
        for (ConstraintViolation validation : result) {
            isValid = false;
            Element group = Document.get().getElementById( validation.getPropertyPath().toString().replace( ".", "_" ) + "_control_group" );
            Element helpBlock = Document.get().getElementById( validation.getPropertyPath().toString().replace( ".", "_" ) + "_help_block" );
            if ( group != null ) group.addClassName( "error" );
            if ( helpBlock != null ) helpBlock.setInnerHTML( validation.getMessage() );
        }
        return isValid;
    }
}
