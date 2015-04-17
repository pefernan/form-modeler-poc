package org.kie.formModeler.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class FormMeta {
    protected Map<String, FieldMeta> fields = new HashMap<String, FieldMeta>(  );
    protected Map<String, DataHolderMeta> dataHolders = new HashMap<String, DataHolderMeta>(  );

    {
        init();
    }

    protected abstract void init();

    public DataHolderMeta getDataHolder(String identifier) {
        return dataHolders.get( identifier );
    }

    public Set<String> getDataHoldersIdentifiers() {
        return dataHolders.keySet();
    }

    public FieldMeta getField(String fieldName) {
        return fields.get( fieldName );
    }

    public Set<String> getFieldNames() {
        return fields.keySet();
    }
}
