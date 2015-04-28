package org.kie.formModeler.example.client.view.jbpm.form.view.examples.hr;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.gwtbootstrap.client.ui.TextBox;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.kie.formModeler.example.client.view.jbpm.form.view.FormView;
import org.kie.formModeler.example.shared.forms.examples.hr.HirinFormModel;

/**
 * Created by pefernan on 3/24/15.
 */
@Templated
@Named("hiring")
public class HiringFormView extends FormView<HirinFormModel> {

    @Inject
    @Bound
    @DataField
    private TextBox name;


    @PostConstruct
    protected void init() {

    }

    @Override
    public void setReadOnly( boolean readOnly ) {
        name.setReadOnly( readOnly );
    }

    @Override
    protected void initInputNames() {
        inputNames.add( "name" );
    }
}
