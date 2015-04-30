package org.kie.formModeler.codegen.services.datamodeller;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

import org.guvnor.common.services.shared.metadata.model.Metadata;
import org.jboss.errai.bus.server.annotations.Service;
import org.kie.workbench.common.screens.datamodeller.backend.server.DataModelerServiceImpl;
import org.kie.workbench.common.screens.datamodeller.model.DataObjectTO;
import org.kie.workbench.common.screens.datamodeller.model.GenerationResult;
import org.uberfire.backend.vfs.Path;

/**
 * Created by pefernan on 4/30/15.
 */
@Service
@ApplicationScoped
@Specializes
public class DataModelerServiceImplExt extends DataModelerServiceImpl {

    @Inject
    private DataModellerFormGenerator formGenerator;

    @Override
    public GenerationResult saveSource( String source, Path path, DataObjectTO dataObjectTO, Metadata metadata, String commitMessage, String newPackageName, String newFileName ) {
        GenerationResult result = super.saveSource( source, path, dataObjectTO, metadata, commitMessage, newPackageName, newFileName );

        formGenerator.generateFormForDataObject( result.getDataObject() );

        return result;
    }
}
