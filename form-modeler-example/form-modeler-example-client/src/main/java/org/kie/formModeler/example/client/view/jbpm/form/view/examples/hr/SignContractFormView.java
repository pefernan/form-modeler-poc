package org.kie.formModeler.example.client.view.jbpm.form.view.examples.hr;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.gwtbootstrap.client.ui.CheckBox;
import com.github.gwtbootstrap.client.ui.IntegerBox;
import com.github.gwtbootstrap.client.ui.TextBox;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.InitialState;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.kie.formModeler.example.client.view.jbpm.form.view.FormView;
import org.kie.formModeler.example.shared.forms.examples.hr.Hiring;
import org.kie.formModeler.example.shared.forms.examples.hr.SignContract;

/**
 * Created by pefernan on 3/24/15.
 */
@Templated
@Named("SignContract")
public class SignContractFormView extends FormView<SignContract> {

    @Inject
    @DataField
    private TextBox name;

    @Inject
    @DataField
    private IntegerBox offering;

    @Inject
    @DataField
    private CheckBox signed;


    @PostConstruct
    protected void init() {

    }

    @Override
    protected void doBind(SignContract model) {
        binder = DataBinder.forModel( model, InitialState.FROM_MODEL );

        binder.bind( name, "name" );
        binder.bind( offering, "offering" );
        binder.bind( signed, "signed" );
    }
}
