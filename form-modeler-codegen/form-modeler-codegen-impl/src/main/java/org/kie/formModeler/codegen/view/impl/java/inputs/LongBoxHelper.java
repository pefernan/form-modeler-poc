package org.kie.formModeler.codegen.view.impl.java.inputs;

import org.kie.formModeler.model.impl.LongBoxFieldDefinition;

/**
 * Created by pefernan on 4/28/15.
 */
public class LongBoxHelper extends AbstractInputCreatorHelper {

    @Override
    public String getSupportedFieldType() {
        return LongBoxFieldDefinition.class.getName();
    }

    @Override
    public String getInputWidget() {
        return "com.github.gwtbootstrap.client.ui.LongBox";
    }
}
