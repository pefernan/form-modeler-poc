package org.kie.formModeler.codegen.roaster.view.inputs;

import org.kie.formModeler.codegen.roaster.view.InputCreatorHelper;

/**
 * Created by pefernan on 4/28/15.
 */
public abstract class AbstractInputCreatorHelper implements InputCreatorHelper {

    @Override
    public boolean isInjectable() {
        return true;
    }

    @Override
    public String getInitLiteral() {
        return null;
    }

    @Override
    public String getReadonlyMethod( String fieldName, String readonlyParam ) {
        return fieldName + ".setReadOnly( " + readonlyParam + ");";
    }
}
