package org.kie.formModeler.example.client.view.jbpm.form.view.examples.hr;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.gwtbootstrap.client.ui.IntegerBox;
import com.github.gwtbootstrap.client.ui.TextBox;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.kie.formModeler.example.client.view.jbpm.form.view.FormView;
import org.kie.formModeler.example.shared.forms.examples.hr.HRInterviewFormModel;

/**
 * Created by pefernan on 3/24/15.
 */
@Templated
@Named("HRInterview")
public class HRInterviewFormView extends FormView<HRInterviewFormModel> {

    @Inject
    @Bound
    @DataField
    private TextBox name;

    @Inject
    @Bound
    @DataField
    private IntegerBox age;

    @Inject
    @Bound
    @DataField
    private TextBox mail;

    @Inject
    @Bound
    @DataField
    private IntegerBox score;


    @PostConstruct
    protected void init() {

    }

    @Override
    public void setReadOnly( boolean readOnly ) {
        name.setReadOnly( readOnly );
        age.setReadOnly( readOnly );
        mail.setReadOnly( readOnly );
        score.setReadOnly( readOnly );
    }

    @Override 
    protected void initInputNames() {
        inputNames.add( "name" );
        inputNames.add( "age" );
        inputNames.add( "mail" );
        inputNames.add( "score" );
    }
}
