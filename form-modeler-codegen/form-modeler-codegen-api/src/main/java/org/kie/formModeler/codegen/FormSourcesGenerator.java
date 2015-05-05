package org.kie.formModeler.codegen;

import org.kie.formModeler.model.FormDefinition;
import org.uberfire.backend.vfs.Path;

/**
 * Created by pefernan on 5/5/15.
 */
public interface FormSourcesGenerator {

    void generateFormSources( FormDefinition form, Path resourcePath );
}
