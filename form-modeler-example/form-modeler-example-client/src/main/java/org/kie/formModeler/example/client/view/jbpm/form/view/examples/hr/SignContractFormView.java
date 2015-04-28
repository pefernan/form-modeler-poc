package org.kie.formModeler.example.client.view.jbpm.form.view.examples.hr;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.gwtbootstrap.client.ui.CheckBox;
import com.github.gwtbootstrap.client.ui.IntegerBox;
import com.github.gwtbootstrap.client.ui.TextBox;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.kie.formModeler.example.client.view.jbpm.form.view.FormView;
import org.kie.formModeler.example.shared.forms.examples.hr.SignContractFormModel;

/**
 * Created by pefernan on 3/24/15.
 */
@Templated
@Named("SignContract")
public class SignContractFormView extends FormView<SignContractFormModel> {

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
    public void setReadOnly( boolean readOnly ) {
        name.setReadOnly( readOnly );
        offering.setReadOnly( readOnly );
        signed.setEnabled( !readOnly );
    }

    @Override 
    protected void initInputNames() {
        inputNames.add( "name" );
        inputNames.add( "offering" );
        inputNames.add( "signed" );
    }
}
