package org.kie.formModeler.codegen.services.datamodeller;

import org.kie.workbench.common.screens.datamodeller.model.DataObjectTO;
import org.uberfire.backend.vfs.Path;

/**
 * Created by pefernan on 4/29/15.
 */
public interface DataModellerFormGenerator {
    public void generateFormForDataObject( DataObjectTO dataObject );
}
