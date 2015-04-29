package org.kie.formModeler.codegen.model;

import org.kie.formModeler.codegen.SourceGenerationContext;

/**
 * Created by pefernan on 4/27/15.
 */
public interface FormModelSourceGenerator {
    public String generateFormModelSource(SourceGenerationContext context);
}
