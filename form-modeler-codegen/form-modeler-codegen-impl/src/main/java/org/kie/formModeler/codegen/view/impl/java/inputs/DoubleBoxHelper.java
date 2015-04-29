package org.kie.formModeler.codegen.view.impl.java.inputs;

import org.kie.formModeler.model.impl.DoubleBoxFieldDefinition;

/**
 * Created by pefernan on 4/28/15.
 */
public class DoubleBoxHelper extends AbstractInputCreatorHelper {

    @Override
    public String getSupportedFieldType() {
        return DoubleBoxFieldDefinition.class.getName();
    }

    @Override
    public String getInputWidget() {
        return "com.github.gwtbootstrap.client.ui.DoubleBox";
    }
}
