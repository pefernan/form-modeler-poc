package org.kie.formModeler.example.shared.forms.examples.hr;

import javax.inject.Named;
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
@Named("hiring")
public class Hiring extends FormMeta {
    public static final NameDataHolderMeta _NAME = new NameDataHolderMeta( );

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
        dataHolderMetas.add( _NAME );
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @Portable
    public static class NameDataHolderMeta extends DataHolderMeta<Hiring, String> {
        public static final NameDataHolderFieldMeta _NAME = new NameDataHolderFieldMeta();

        @Override
        public String getName() {
            return "name";
        }

        @Override
        public String getModel( Hiring formMeta ) {
            return formMeta.getName();
        }

        @Override
        public DataHolderType getType() {
            return DataHolderType.BASIC;
        }
    }

    @Portable
    public static class NameDataHolderFieldMeta extends DataHolderFieldMeta<Hiring, String> {

        @Override
        public String getName() {
            return "name";
        }

        @Override
        public String getValue( Hiring meta ) {
            return meta.getName();
        }

        @Override
        public void setValue( Hiring meta, String value ) {
            meta.setName( value );
        }
    }
}
