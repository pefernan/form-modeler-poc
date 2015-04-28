package org.kie.formModeler.codegen.view;

import org.kie.formModeler.codegen.SourceGenerationContext;
import org.kie.formModeler.model.FormDefinition;

/**
 * Created by pefernan on 4/28/15.
 */
public interface FormJavaTemplateSourceGenerator {
    public String generateJavaTemplateSource(SourceGenerationContext context);
}
