package org.kie.formModeler.example.shared.forms.examples.hr;

import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.kie.formModeler.model.DataHolderFieldMeta;
import org.kie.formModeler.model.DataHolderMeta;
import org.kie.formModeler.model.FormMeta;
import org.kie.formModeler.model.annotation.DataHolder;
import org.kie.formModeler.model.annotation.DataHolderType;
import org.kie.formModeler.model.annotation.Form;
import org.kie.formModeler.model.annotation.FormConstructor;

/**
 * Created by pefernan on 4/17/15.
 */
@Form
@Bindable
@Portable
@Named("CreateProposal")
public class CreateProposal extends FormMeta {
    public static final HRScoreDataHolderMeta _HR_SCORE = new HRScoreDataHolderMeta();
    public static final TechScoreDataHolderMeta _TECH_SCORE = new TechScoreDataHolderMeta();
    public static final OfferingDataHolderMeta _OFFERING = new OfferingDataHolderMeta();

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

    @FormConstructor
    public CreateProposal( @MapsTo( "hr_score" )Integer hrScore,
            @MapsTo( "tech_score" )Integer techScore,
            @MapsTo( "offering" )Integer offering ) {
        this.hrScore = hrScore;
        this.techScore = techScore;
        this.offering = offering;
    }

    @Override
    protected void init() {
        fieldNames.add( "hr_score" );
        fieldNames.add( "tech_score" );
        fieldNames.add( "offering" );
        dataHolderMetas.add( _HR_SCORE );
        dataHolderMetas.add( _TECH_SCORE );
        dataHolderMetas.add( _OFFERING );
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

    @Portable
    public static class TechScoreDataHolderMeta extends DataHolderMeta<CreateProposal, Integer> {
        public static final TechScoreDataHolderFieldMeta _TECH_SCORE = new TechScoreDataHolderFieldMeta();

        @Override
        public String getName() {
            return "tech_score";
        }

        @Override
        public Integer getModel( CreateProposal formMeta ) {
            return formMeta.getTechScore();
        }

        @Override
        public DataHolderType getType() {
            return DataHolderType.BASIC;
        }
    }

    @Portable
    public static class TechScoreDataHolderFieldMeta extends DataHolderFieldMeta<CreateProposal, Integer> {

        @Override
        public String getName() {
            return "tech_score";
        }

        @Override
        public Integer getValue( CreateProposal meta ) {
            return meta.getTechScore();
        }

        @Override
        public void setValue( CreateProposal meta, Integer value ) {
            meta.setTechScore( value );
        }
    }

    @Portable
    public static class HRScoreDataHolderMeta extends DataHolderMeta<CreateProposal, Integer> {
        public static final HRScoreDataHolderFieldMeta _HR_SCORE = new HRScoreDataHolderFieldMeta();

        @Override
        public String getName() {
            return "hr_score";
        }

        @Override
        public Integer getModel( CreateProposal formMeta ) {
            return formMeta.getHrScore();
        }

        @Override
        public DataHolderType getType() {
            return DataHolderType.BASIC;
        }
    }

    @Portable
    public static class HRScoreDataHolderFieldMeta extends DataHolderFieldMeta<CreateProposal, Integer> {

        @Override
        public String getName() {
            return "hr_score";
        }

        @Override
        public Integer getValue( CreateProposal meta ) {
            return meta.getHrScore();
        }

        @Override
        public void setValue( CreateProposal meta, Integer value ) {
            meta.setHrScore( value );
        }
    }

    @Portable
    public static class OfferingDataHolderMeta extends DataHolderMeta<CreateProposal, Integer> {
        public static final OfferingDataHolderFieldMeta _OFFERING = new OfferingDataHolderFieldMeta();

        @Override
        public String getName() {
            return "offering";
        }

        @Override
        public Integer getModel( CreateProposal formMeta ) {
            return formMeta.getOffering();
        }

        @Override
        public DataHolderType getType() {
            return DataHolderType.BASIC;
        }
    }

    @Portable
    public static class OfferingDataHolderFieldMeta extends DataHolderFieldMeta<CreateProposal, Integer> {

        @Override
        public String getName() {
            return "offering";
        }

        @Override
        public Integer getValue( CreateProposal meta ) {
            return meta.getOffering();
        }

        @Override
        public void setValue( CreateProposal meta, Integer value ) {
            meta.setOffering( value );
        }
    }
}
