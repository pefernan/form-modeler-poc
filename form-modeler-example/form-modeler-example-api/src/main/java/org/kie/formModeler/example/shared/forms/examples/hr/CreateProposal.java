package org.kie.formModeler.example.shared.forms.examples.hr;

import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.kie.formModeler.model.DataHolderMeta;
import org.kie.formModeler.model.FieldMeta;
import org.kie.formModeler.model.FormMeta;
import org.kie.formModeler.model.annotation.DataHolder;
import org.kie.formModeler.model.annotation.DataHolderType;
import org.kie.formModeler.model.annotation.Form;

/**
 * Created by pefernan on 4/17/15.
 */
@Form
@Bindable
@Portable
@Named("CreateProposal")
public class CreateProposal extends FormMeta {

    @NotNull
    @Min( 1 )
    @Max( 5 )
    @DataHolder( name="hr_score", type = DataHolderType.BASIC )
    private Integer hrScore;

    @NotNull
    @Min( 1 )
    @Max( 5 )
    @DataHolder( name="tech_score", type = DataHolderType.BASIC )
    private Integer techScore;

    @NotNull
    @Min( 1000 )
    @Max( 500000 )
    @DataHolder( name="offering", type = DataHolderType.BASIC )
    private Integer offering;

    public CreateProposal() {
    }

    public CreateProposal( @MapsTo( "hr_score" )Integer hrScore,
            @MapsTo( "tech_score" )Integer techScore,
            @MapsTo( "offering" )Integer offering ) {
        this.hrScore = hrScore;
        this.techScore = techScore;
        this.offering = offering;
    }

    @Override
    protected void init() {
        DataHolderMeta<Integer> hrScoreHolder = new DataHolderMeta<Integer>("hr_score", hrScore, DataHolderType.BASIC);
        DataHolderMeta<Integer> techScoreHolder = new DataHolderMeta<Integer>("tech_score", techScore, DataHolderType.BASIC);
        DataHolderMeta<Integer> offeringHolder = new DataHolderMeta<Integer>("offering", offering, DataHolderType.BASIC);

        dataHolders.put( "hr_score", hrScoreHolder );
        dataHolders.put( "tech_score", techScoreHolder );
        dataHolders.put( "offering", offeringHolder );

        FieldMeta<Integer> hrScoreField = new FieldMeta<Integer>("hr_score", "hr_score");
        FieldMeta<Integer> techScoreField = new FieldMeta<Integer>("tech_score", "tech_score");
        FieldMeta<Integer> offeringField = new FieldMeta<Integer>("offering", "offering");

        fields.put( "hr_score", hrScoreField );
        fields.put( "tech_score", techScoreField );
        fields.put( "offering", offeringField );
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