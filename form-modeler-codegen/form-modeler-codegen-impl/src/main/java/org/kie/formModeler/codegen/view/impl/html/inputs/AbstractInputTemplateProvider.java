package org.kie.formModeler.codegen.view.impl.html.inputs;

import java.io.InputStream;

import org.kie.formModeler.codegen.view.impl.html.InputTemplateProvider;

/**
 * Created by pefernan on 4/29/15.
 */
public abstract class AbstractInputTemplateProvider implements InputTemplateProvider {

    @Override
    public InputStream getTemplateInputStream() {
        return getClass().getResourceAsStream( "/org/kie/formModeler/codegen/view/impl/html/templates/textinput.mv" );
    }
}
