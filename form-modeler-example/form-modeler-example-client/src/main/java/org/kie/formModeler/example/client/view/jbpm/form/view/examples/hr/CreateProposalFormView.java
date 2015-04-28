package org.kie.formModeler.example.client.view.jbpm.form.view.examples.hr;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.gwtbootstrap.client.ui.IntegerBox;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.kie.formModeler.example.client.view.jbpm.form.view.FormView;
import org.kie.formModeler.example.shared.forms.examples.hr.CreateProposalFormModel;

/**
 * Created by pefernan on 3/24/15.
 */
@Templated
@Named("CreateProposal")
public class CreateProposalFormView extends FormView<CreateProposalFormModel> {

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
    public void setReadOnly( boolean readOnly ) {
        hrScore.setReadOnly( readOnly );
        techScore.setReadOnly( readOnly );
        offering.setReadOnly( readOnly );
    }

    @Override 
    protected void initInputNames() {
        inputNames.add( "hr_score" );
        inputNames.add( "tech_score" );
        inputNames.add( "offering" );
    }
}
