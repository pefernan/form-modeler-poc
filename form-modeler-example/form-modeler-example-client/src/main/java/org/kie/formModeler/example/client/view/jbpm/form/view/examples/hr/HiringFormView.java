package org.kie.formModeler.example.client.view.jbpm.form.view.examples.hr;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.gwtbootstrap.client.ui.TextBox;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.InitialState;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.kie.formModeler.example.client.view.jbpm.form.view.FormView;
import org.kie.formModeler.example.shared.forms.examples.hr.Hiring;

/**
 * Created by pefernan on 3/24/15.
 */
@Templated
@Named("hiring")
public class HiringFormView extends FormView<Hiring> {

    @Inject
    @DataField
    private TextBox name;


    @PostConstruct
    protected void init() {

    }

    @Override
    protected void doBind(Hiring model) {
        binder = DataBinder.forModel( model, InitialState.FROM_MODEL );

        binder.bind( name, "name" );
    }

    @Override
    public void setReadOnly( boolean readOnly ) {
        name.setReadOnly( readOnly );
    }
}
