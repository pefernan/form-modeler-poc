package org.kie.formModeler.model;

import org.kie.formModeler.model.annotation.DataHolderType;

/**
 * Created by pefernan on 4/19/15.
 */
public abstract class DataHolderMeta<F extends FormMeta, T> {

    public abstract String getName( );
    public abstract T getModel( F formMeta );
    public abstract DataHolderType getType();
}
