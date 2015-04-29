package org.kie.formModeler.codegen.view.impl.java.inputs;

import org.kie.formModeler.model.impl.TextBoxFieldDefinition;

/**
 * Created by pefernan on 4/28/15.
 */
public class TextBoxHelper extends AbstractInputCreatorHelper {

    @Override
    public String getSupportedFieldType() {
        return TextBoxFieldDefinition.class.getName();
    }

    @Override
    public String getInputWidget() {
        return "com.github.gwtbootstrap.client.ui.TextBox";
    }
}
