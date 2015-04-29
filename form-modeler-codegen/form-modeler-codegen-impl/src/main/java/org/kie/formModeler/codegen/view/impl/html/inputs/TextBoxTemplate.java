package org.kie.formModeler.codegen.view.impl.html.inputs;

import org.kie.formModeler.model.impl.TextBoxFieldDefinition;

/**
 * Created by pefernan on 4/29/15.
 */
public class TextBoxTemplate extends AbstractInputTemplateProvider {

    @Override
    public String getSupportedFieldType() {
        return TextBoxFieldDefinition.class.getName();
    }
}
