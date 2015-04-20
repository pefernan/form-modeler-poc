package org.kie.formModeler.model;

/**
 * Created by pefernan on 4/19/15.
 */
public abstract class DataHolderFieldMeta<F extends FormMeta, T> {

    public abstract String getName();
    public abstract T getValue(F meta);
    public abstract void setValue(F meta, T value);
}
