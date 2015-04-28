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
import org.kie.formModeler.model.meta.FormModel;

/**
 * Created by pefernan on 4/17/15.
 */
@Bindable
@Portable
@Named("SignContract")
public class SignContractFormModel extends FormModel {

    @NotEmpty
    @Size( min = 4, max = 20 )
    private String name;

    @NotNull
    @Min( 1000 )
    @Max( 500000 )
    private Integer offering;

    private Boolean signed;

    public SignContractFormModel() {
    }

    public SignContractFormModel( @MapsTo("name") String name,
            @MapsTo("offering") Integer offering,
            @MapsTo("signed") Boolean signed ) {
        this.name = name;
        this.offering = offering;
        this.signed = signed;
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
