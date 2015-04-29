package org.kie.formModeler.codegen;

import org.kie.formModeler.model.FormDefinition;

/**
 * Created by pefernan on 4/28/15.
 */
public class SourceGenerationContext {
    private FormDefinition formDefinition;

    private String modelPackage;
    private String modelName;

    private String viewPackage;
    private String viewName;

    public SourceGenerationContext( FormDefinition formDefinition, String modelPackage, String modelName, String viewPackage, String viewName ) {
        this.formDefinition = formDefinition;
        this.modelPackage = modelPackage;
        this.modelName = modelName;
        this.viewPackage = viewPackage;
        this.viewName = viewName;
    }

    public FormDefinition getFormDefinition() {
        return formDefinition;
    }

    public void setFormDefinition( FormDefinition formDefinition ) {
        this.formDefinition = formDefinition;
    }

    public String getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage( String modelPackage ) {
        this.modelPackage = modelPackage;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName( String modelName ) {
        this.modelName = modelName;
    }

    public String getViewPackage() {
        return viewPackage;
    }

    public void setViewPackage( String viewPackage ) {
        this.viewPackage = viewPackage;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName( String viewName ) {
        this.viewName = viewName;
    }
}
