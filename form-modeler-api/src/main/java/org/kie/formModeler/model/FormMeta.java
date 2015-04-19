package org.kie.formModeler.model;

import java.util.ArrayList;
import java.util.List;

public abstract class FormMeta {
    protected List<String> fieldNames = new ArrayList<String>(  );

    {
        init();
    }

    protected abstract void init();

    public abstract DataHolderMeta[] getDataHolders();

    public abstract FieldMeta[] getFields();

    public List<String> getFieldNames() {
        return fieldNames;
    }
}
