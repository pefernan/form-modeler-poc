package org.kie.formModeler.example.client.view.jbpm.form.view.examples.hr;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.gwtbootstrap.client.ui.IntegerBox;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.InitialState;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.kie.formModeler.example.client.view.jbpm.form.view.FormView;
import org.kie.formModeler.example.shared.forms.examples.hr.CreateProposal;

/**
 * Created by pefernan on 3/24/15.
 */
@Templated
@Named("CreateProposal")
public class CreateProposalFormView extends FormView<CreateProposal> {

    @Inject
    @DataField
    private IntegerBox hrScore;

    @Inject
    @DataField
    private IntegerBox techScore;

    @Inject
    @DataField
    private IntegerBox offering;


    @PostConstruct
    protected void init() {

    }

    @Override
    protected void doBind(CreateProposal form) {
        binder = DataBinder.forModel( form, InitialState.FROM_MODEL );

        binder.bind( hrScore, "hr_score");
        binder.bind( techScore, "tech_score" );
        binder.bind( offering, "offering" );
    }
}
