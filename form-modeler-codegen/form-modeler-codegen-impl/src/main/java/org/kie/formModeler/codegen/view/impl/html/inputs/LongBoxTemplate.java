package org.kie.formModeler.codegen.view.impl.html.inputs;

import org.kie.formModeler.model.impl.LongBoxFieldDefinition;

/**
 * Created by pefernan on 4/29/15.
 */
public class LongBoxTemplate extends AbstractInputTemplateProvider {

    @Override
    public String getSupportedFieldType() {
        return LongBoxFieldDefinition.class.getName();
    }
}
