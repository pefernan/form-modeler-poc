package org.kie.formModeler.codegen.services.datamodeller.impl;

import javax.inject.Inject;

import org.kie.formModeler.codegen.services.datamodeller.DataModellerFormGenerator;
import org.kie.formModeler.service.FieldManager;
import org.kie.workbench.common.screens.datamodeller.model.DataObjectTO;
import org.uberfire.backend.vfs.Path;

/**
 * Created by pefernan on 4/29/15.
 */
public class DataModellerFormGeneratorImpl implements DataModellerFormGenerator {

    @Inject
    protected FieldManager fieldManager;

    @Override
    public void generateFormForDataObject( DataObjectTO dataObject, Path dataObjectPath ) {

    }
}
