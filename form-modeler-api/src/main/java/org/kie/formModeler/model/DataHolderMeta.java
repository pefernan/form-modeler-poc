package org.kie.formModeler.model;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.kie.formModeler.model.annotation.DataHolderType;

/**
 * Created by pefernan on 4/16/15.
 */
@Portable
public class DataHolderMeta<T> {
    private String name;
    private T model;
    private DataHolderType type;

    public DataHolderMeta() {
    }

    public DataHolderMeta( String name, T model, DataHolderType type ) {
        this.name = name;
        this.model = model;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public T getModel() {
        return model;
    }

    public void setModel( T model ) {
        this.model = model;
    }

    public DataHolderType getType() {
        return type;
    }

    public void setType( DataHolderType type ) {
        this.type = type;
    }
}
