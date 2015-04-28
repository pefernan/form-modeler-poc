package org.kie.formModeler.codegen.roaster.view.inputs;

import org.kie.formModeler.model.impl.TextAreaFieldDefinition;

/**
 * Created by pefernan on 4/28/15.
 */
public class TextAreaHelper extends AbstractInputCreatorHelper {

    @Override
    public String getSupportedFieldType() {
        return TextAreaFieldDefinition.class.getName();
    }

    @Override
    public String getInputWidget() {
        return "com.github.gwtbootstrap.client.ui.TextArea";
    }
}
