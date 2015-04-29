package org.kie.formModeler.codegen.view.impl.html.inputs;

import org.kie.formModeler.model.impl.DateBoxFieldDefinition;

/**
 * Created by pefernan on 4/29/15.
 */
public class DateBoxTemplate extends AbstractInputTemplateProvider {

    @Override
    public String getSupportedFieldType() {
        return DateBoxFieldDefinition.class.getName();
    }
}
