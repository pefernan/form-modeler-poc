package org.kie.formModeler.codegen.view.impl.java.inputs;

import org.kie.formModeler.model.impl.CheckBoxFieldDefinition;

/**
 * Created by pefernan on 4/28/15.
 */
public class CheckBoxHelper extends AbstractInputCreatorHelper {

    @Override
    public String getSupportedFieldType() {
        return CheckBoxFieldDefinition.class.getName();
    }

    @Override
    public String getInputWidget() {
        return "com.github.gwtbootstrap.client.ui.CheckBox";
    }
}
