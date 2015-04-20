package org.kie.formModeler.example.client.view.jbpm.form.view.examples.person;

import javax.inject.Inject;
import javax.inject.Named;

import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.TextBox;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.InitialState;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.kie.formModeler.example.client.view.jbpm.form.view.FormView;
import org.kie.formModeler.example.shared.forms.examples.person.PersonForm;

/**
 * Created by pefernan on 4/20/15.
 */
@Templated
@Named("PersonForm")
public class PersonFormView extends FormView<PersonForm> {

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
    private TextArea person_address;


    @Override
    protected void doBind( PersonForm model ) {
        binder = DataBinder.forModel( model, InitialState.FROM_MODEL );

        binder.bind( identifier, "identifier" );
        binder.bind( person_name, "person.name" );
        binder.bind( person_surname, "person.surname");
        binder.bind( person_address, "person.address");
    }

    @Override
    public void setReadOnly( boolean readOnly ) {
        identifier.setReadOnly( readOnly );
        person_name.setReadOnly( readOnly );
        person_surname.setReadOnly( readOnly );
        person_address.setReadOnly( readOnly );
    }
}
