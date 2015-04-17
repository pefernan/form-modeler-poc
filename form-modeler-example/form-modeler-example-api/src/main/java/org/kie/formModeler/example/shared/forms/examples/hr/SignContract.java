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
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.kie.formModeler.model.DataHolderMeta;
import org.kie.formModeler.model.FieldMeta;
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
        DataHolderMeta<String> nameHolder = new DataHolderMeta<String>("name", name, DataHolderType.BASIC);
        DataHolderMeta<Integer> offeringHolder = new DataHolderMeta<Integer>("offering", offering, DataHolderType.BASIC);
        DataHolderMeta<Boolean> signedHolder = new DataHolderMeta<Boolean>( "signed", signed, DataHolderType.BASIC );

        dataHolders.put( "name", nameHolder );
        dataHolders.put( "offering", offeringHolder );
        dataHolders.put( "signed", signedHolder );

        FieldMeta<String> nameField = new FieldMeta<String>("name", "name");
        FieldMeta<Integer> offeringField = new FieldMeta<Integer>("offering", "offering");
        FieldMeta<Boolean> signedField = new FieldMeta<Boolean>( "signed", "signed" );

        fields.put( "name", nameField );
        fields.put( "offering", offeringField );
        fields.put( "signed", signedField );
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
}