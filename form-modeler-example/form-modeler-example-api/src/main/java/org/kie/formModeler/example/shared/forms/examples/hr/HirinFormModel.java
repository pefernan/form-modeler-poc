package org.kie.formModeler.example.shared.forms.examples.hr;

import javax.inject.Named;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.kie.formModeler.model.meta.FormModel;

/**
 * Created by pefernan on 4/17/15.
 */
@Bindable
@Portable
@Named("hiring")
public class HirinFormModel extends FormModel {

    @NotEmpty
    @Size( min = 4, max = 20 )
    @Bound(property = "name")
    private String name;

    public HirinFormModel() {
    }

    public HirinFormModel( @MapsTo("name") String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}
