package org.kie.formModeler.codegen.roaster.view.inputs;

import org.kie.formModeler.model.impl.DateBoxFieldDefinition;

/**
 * Created by pefernan on 4/28/15.
 */
public class DateBoxHelper extends AbstractInputCreatorHelper {

    @Override
    public String getSupportedFieldType() {
        return DateBoxFieldDefinition.class.getName();
    }

    @Override
    public boolean isInjectable() {
        return false;
    }

    @Override
    public String getInputWidget() {
        return "com.github.gwtbootstrap.datepicker.client.ui.DateBox";
    }

    @Override
    public String getInitLiteral() {
        return "new DateBox()";
    }
}
