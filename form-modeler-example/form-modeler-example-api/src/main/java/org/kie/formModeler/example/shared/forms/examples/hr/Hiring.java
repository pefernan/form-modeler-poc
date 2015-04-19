package org.kie.formModeler.example.shared.forms.examples.hr;

import javax.inject.Named;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
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
@Named("hiring")
public class Hiring extends FormMeta {

    @NotEmpty
    @Size( min = 4, max = 20 )
    @DataHolder( name="name", type = DataHolderType.BASIC )
    private String name;

    public Hiring() {
    }

    @FormConstructor
    public Hiring( @MapsTo( "name" ) String name) {
        this.name = name;
    }

    @Override
    protected void init() {
        fieldNames.add( "name" );
    }

    @Override
    public DataHolderMeta[] getDataHolders() {
        DataHolderMeta[] metas = new DataHolderMeta[1];
        metas[0] = new DataHolderMeta<String>("name", name, DataHolderType.BASIC);
        return metas;
    }

    @Override
    public FieldMeta[] getFields() {
        FieldMeta[] metas = new FieldMeta[1];
        metas[0] = new FieldMeta<String>("name", "name");
        return metas;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}
