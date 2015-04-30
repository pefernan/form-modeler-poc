package org.kie.formModeler.codegen;

import org.kie.formModeler.model.FormDefinition;

/**
 * Created by pefernan on 4/28/15.
 */
public class SourceGenerationContext {
    public static final String FORM_MODEL_SUFFIX = "FormModel";
    public static final String FORM_VIEW_SUFFIX = "FormView";

    private FormDefinition formDefinition;

    private String modelPackage;
    private String modelName;

    private String viewPackage;
    private String viewName;

    public SourceGenerationContext( FormDefinition formDefinition, String modelPackage, String viewPackage ) {
        this.formDefinition = formDefinition;
        this.modelPackage = modelPackage;
        this.viewPackage = viewPackage;
        this.modelName = formDefinition.getName() + FORM_MODEL_SUFFIX;
        this.viewName = formDefinition.getName() + FORM_VIEW_SUFFIX;
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
