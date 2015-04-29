package org.kie.formModeler.codegen.view.impl.html.inputs;

import java.io.InputStream;

import org.kie.formModeler.codegen.view.impl.html.InputTemplateProvider;
import org.kie.formModeler.model.impl.CheckBoxFieldDefinition;

/**
 * Created by pefernan on 4/29/15.
 */
public class CheckBoxTemplate implements InputTemplateProvider {

    @Override
    public String getSupportedFieldType() {
        return CheckBoxFieldDefinition.class.getName();
    }

    @Override
    public InputStream getTemplateInputStream() {
        return getClass().getResourceAsStream( "/org/kie/formModeler/codegen/view/impl/html/templates/checkbox.mv" );
    }
}
