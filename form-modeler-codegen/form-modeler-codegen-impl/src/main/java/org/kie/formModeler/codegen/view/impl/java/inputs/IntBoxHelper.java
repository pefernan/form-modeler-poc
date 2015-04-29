package org.kie.formModeler.codegen.view.impl.java.inputs;

import org.kie.formModeler.model.impl.IntBoxFieldDefinition;

/**
 * Created by pefernan on 4/28/15.
 */
public class IntBoxHelper extends AbstractInputCreatorHelper {

    @Override
    public String getSupportedFieldType() {
        return IntBoxFieldDefinition.class.getName();
    }

    @Override
    public String getInputWidget() {
        return "com.github.gwtbootstrap.client.ui.IntegerBox";
    }
}
