package org.kie.formModeler.codegen.view.impl.html.inputs;

import org.kie.formModeler.model.impl.IntBoxFieldDefinition;

/**
 * Created by pefernan on 4/29/15.
 */
public class IntBoxTemplate extends AbstractInputTemplateProvider {

    @Override
    public String getSupportedFieldType() {
        return IntBoxFieldDefinition.class.getName();
    }
}
