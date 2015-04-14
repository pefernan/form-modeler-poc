package org.kie.formModeler.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class FormDefinition {
    protected List<FieldDefinition> fields = new ArrayList<FieldDefinition>(  );

    {
        initFields();
    }

    protected abstract void initFields();

    public List<FieldDefinition> getFields() {
        return fields;
    }

    public void setFields( List<FieldDefinition> fields ) {
        this.fields = fields;
    }

    public void addField( FieldDefinition field ) {
        this.fields.add( field );
    }

    public void removeField( String fieldId) {
        for (Iterator<FieldDefinition> it = fields.iterator(); it.hasNext();) {
            FieldDefinition definition = it.next();
            if (definition.getName().equals( fieldId ) ) {
                it.remove();
                return;
            }
        }
    }
}
