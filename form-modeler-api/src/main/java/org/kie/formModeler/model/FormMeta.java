package org.kie.formModeler.model;

import java.util.ArrayList;
import java.util.List;

public abstract class FormMeta {
    protected List<String> fieldNames = new ArrayList<String>(  );
    protected List<DataHolderMeta> dataHolderMetas = new ArrayList<DataHolderMeta>(  );

    {
        init();
    }

    protected abstract void init();

    public DataHolderMeta[] getDataHolders() {
        return dataHolderMetas.toArray( new DataHolderMeta[ dataHolderMetas.size()] );
    }

    public List<String> getFieldNames() {
        return fieldNames;
    }
}
