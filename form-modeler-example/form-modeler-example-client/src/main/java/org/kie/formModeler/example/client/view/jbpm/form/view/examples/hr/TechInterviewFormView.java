package org.kie.formModeler.example.client.view.jbpm.form.view.examples.hr;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.gwtbootstrap.client.ui.IntegerBox;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.TextBox;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.InitialState;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.kie.formModeler.example.client.view.jbpm.form.view.FormView;
import org.kie.formModeler.example.shared.forms.examples.hr.HRInterview;
import org.kie.formModeler.example.shared.forms.examples.hr.TechInterview;

/**
 * Created by pefernan on 3/24/15.
 */
@Templated
@Named("TechInterview")
public class TechInterviewFormView extends FormView<TechInterview> {

    @Inject
    @DataField
    private TextBox name;

    @Inject
    @DataField
    private IntegerBox age;

    @Inject
    @DataField
    private TextBox mail;

    @Inject
    @DataField
    private TextArea skills;

    @Inject
    @DataField
    private IntegerBox score;

    @Inject
    @DataField
    private TextBox twitter;


    @PostConstruct
    protected void init() {

    }

    @Override
    protected void doBind(TechInterview model) {
        binder = DataBinder.forModel( model, InitialState.FROM_MODEL );

        binder.bind( name, "name" );
        binder.bind( age, "age" );
        binder.bind( mail, "mail");
        binder.bind( skills, "skills");
        binder.bind( score, "score");
        binder.bind( twitter, "twitter" );
    }

    @Override
    public void setReadOnly( boolean readOnly ) {
        name.setReadOnly( readOnly );
        age.setReadOnly( readOnly );
        mail.setReadOnly( readOnly );
        skills.setReadOnly( readOnly );
        score.setReadOnly( readOnly );
        twitter.setReadOnly( readOnly );
    }
}
