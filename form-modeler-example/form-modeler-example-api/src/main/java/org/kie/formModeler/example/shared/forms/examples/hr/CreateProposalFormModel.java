package org.kie.formModeler.example.shared.forms.examples.hr;

import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.kie.formModeler.model.meta.FormModel;

/**
 * Created by pefernan on 4/17/15.
 */
@Bindable
@Portable
@Named("CreateProposal")
public class CreateProposalFormModel extends FormModel {

    @NotNull
    @Min( 1 )
    @Max( 5 )
    private Integer hrScore;

    @NotNull
    @Min( 1 )
    @Max( 5 )
    private Integer techScore;

    @NotNull
    @Min( 1000 )
    @Max( 500000 )
    private Integer offering;

    public CreateProposalFormModel() {
    }

    public CreateProposalFormModel( @MapsTo("hr_score") Integer hrScore,
            @MapsTo("tech_score") Integer techScore,
            @MapsTo("offering") Integer offering ) {
        this.hrScore = hrScore;
        this.techScore = techScore;
        this.offering = offering;
    }

    public Integer getHrScore() {
        return hrScore;
    }

    public void setHrScore( Integer hrScore ) {
        this.hrScore = hrScore;
    }

    public Integer getTechScore() {
        return techScore;
    }

    public void setTechScore( Integer techScore ) {
        this.techScore = techScore;
    }

    public Integer getOffering() {
        return offering;
    }

    public void setOffering( Integer offering ) {
        this.offering = offering;
    }
}
