package org.kie.formModeler.model.meta;

import java.util.ArrayList;
import java.util.List;

public abstract class FormModel {
    protected List<String> fieldNames = new ArrayList<String>(  );
    protected List<DataHolderMeta> dataHolderMetas = new ArrayList<DataHolderMeta>(  );

    public DataHolderMeta[] getDataHolders() {
        return dataHolderMetas.toArray( new DataHolderMeta[ dataHolderMetas.size()] );
    }

    public List<String> getFieldNames() {
        return fieldNames;
    }
}
