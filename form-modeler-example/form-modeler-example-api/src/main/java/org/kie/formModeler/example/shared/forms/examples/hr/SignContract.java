package org.kie.formModeler.example.shared.forms.examples.hr;

import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
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
@Named("SignContract")
public class SignContract extends FormMeta {
    public static final NameDataHolderMeta _NAME = new NameDataHolderMeta( );
    public static final OfferingDataHolderMeta _OFFERING = new OfferingDataHolderMeta();
    public static final SignedDataHolderMeta _SIGNED = new SignedDataHolderMeta();

    @NotEmpty
    @Size( min = 4, max = 20 )
    @DataHolder( name="name", type = DataHolderType.BASIC )
    private String name;

    @NotNull
    @Min( 1000 )
    @Max( 500000 )
    @DataHolder( name="offering", type = DataHolderType.BASIC )
    private Integer offering;

    @DataHolder( name="signed", type = DataHolderType.BASIC )
    private Boolean signed;

    public SignContract() {
    }

    @FormConstructor
    public SignContract( @MapsTo("name") String name,
            @MapsTo( "offering" ) Integer offering,
            @MapsTo( "signed" ) Boolean signed) {
        this.name = name;
        this.offering = offering;
        this.signed = signed;
    }

    @Override
    protected void init() {
        fieldNames.add( "name" );
        fieldNames.add( "offering" );
        fieldNames.add( "signed" );
        dataHolderMetas.add( _NAME );
        dataHolderMetas.add( _OFFERING );
        dataHolderMetas.add( _SIGNED );
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Integer getOffering() {
        return offering;
    }

    public void setOffering( Integer offering ) {
        this.offering = offering;
    }

    public Boolean getSigned() {
        return signed;
    }

    public void setSigned( Boolean signed ) {
        this.signed = signed;
    }

    @Portable
    public static class NameDataHolderMeta extends DataHolderMeta<SignContract, String> {
        public static final NameDataHolderFieldMeta _NAME = new NameDataHolderFieldMeta();

        @Override
        public String getName() {
            return "name";
        }

        @Override
        public String getModel( SignContract formMeta ) {
            return formMeta.getName();
        }

        @Override
        public DataHolderType getType() {
            return DataHolderType.BASIC;
        }
    }

    @Portable
    public static class NameDataHolderFieldMeta extends DataHolderFieldMeta<SignContract, String> {

        @Override
        public String getName() {
            return "name";
        }

        @Override
        public String getValue( SignContract meta ) {
            return meta.getName();
        }

        @Override
        public void setValue( SignContract meta, String value ) {
            meta.setName( value );
        }
    }

    @Portable
    public static class OfferingDataHolderMeta extends DataHolderMeta<SignContract, Integer> {
        public static final OfferingDataHolderFieldMeta _OFFERING = new OfferingDataHolderFieldMeta();

        @Override
        public String getName() {
            return "offering";
        }

        @Override
        public Integer getModel( SignContract formMeta ) {
            return formMeta.getOffering();
        }

        @Override
        public DataHolderType getType() {
            return DataHolderType.BASIC;
        }
    }

    @Portable
    public static class OfferingDataHolderFieldMeta extends DataHolderFieldMeta<SignContract, Integer> {

        @Override
        public String getName() {
            return "offering";
        }

        @Override
        public Integer getValue( SignContract meta ) {
            return meta.getOffering();
        }

        @Override
        public void setValue( SignContract meta, Integer value ) {
            meta.setOffering( value );
        }
    }

    @Portable
    public static class SignedDataHolderMeta extends DataHolderMeta<SignContract, Boolean> {
        public static final SignedDataHolderFieldMeta _SIGNED = new SignedDataHolderFieldMeta();

        @Override
        public String getName() {
            return "signed";
        }

        @Override
        public Boolean getModel( SignContract formMeta ) {
            return formMeta.getSigned();
        }

        @Override
        public DataHolderType getType() {
            return DataHolderType.BASIC;
        }
    }

    @Portable
    public static class SignedDataHolderFieldMeta extends DataHolderFieldMeta<SignContract, Boolean> {

        @Override
        public String getName() {
            return "signed";
        }

        @Override
        public Boolean getValue( SignContract meta ) {
            return meta.getSigned();
        }

        @Override
        public void setValue( SignContract meta, Boolean value ) {
            meta.setSigned( value );
        }
    }
}
