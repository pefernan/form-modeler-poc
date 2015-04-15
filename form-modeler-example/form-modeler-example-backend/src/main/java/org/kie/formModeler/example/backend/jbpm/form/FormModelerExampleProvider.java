package org.kie.formModeler.example.backend.jbpm.form;

import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import com.google.gson.Gson;
import org.jbpm.kie.services.impl.form.provider.AbstractFormProvider;
import org.jbpm.services.api.model.ProcessDefinition;
import org.kie.api.task.model.Task;
import org.kie.formModeler.example.service.FormLoaderService;
import org.kie.formModeler.model.FormDefinition;
import org.kie.formModeler.model.annotation.Form;

/**
 * Created by pefernan on 4/15/15.
 */
@Dependent
public class FormModelerExampleProvider extends AbstractFormProvider {

    @Inject
    private Instance<FormDefinition> instances;

    @Inject
    protected FormLoaderService formLoaderService;

    private Map<String, FormDefinition> formDefinitions = new HashMap<String, FormDefinition>(  );

    @PostConstruct
    protected void init() {
        for (FormDefinition definition : instances) {
            for (Annotation annotation : instances.getClass().getAnnotations()) {
                if (annotation instanceof Form) {
                    formDefinitions.put( ( ( Form ) annotation ).name(), definition );
                }
            }
        }
    }

    @Override
    public int getPriority() {
        return -1;
    }

    @Override
    public String render( String s, ProcessDefinition processDefinition, Map<String, Object> context ) {
        return null;
    }

    @Override
    public String render( String s, Task task, ProcessDefinition processDefinition, Map<String, Object> context ) {
        return getFormRenderingInfo( getTaskFormName( task ), context );
    }

    @Override
    protected String getFormSuffix() {
        return "";
    }

    protected String getFormRenderingInfo( String destination, Map<String, Object> renderContext ) {
        FormDefinition formDefinition = formDefinitions.get( destination );
        if (formDefinition != null) {

            Map outputs = ( Map ) renderContext.get("outputs");
            if (outputs == null) outputs = new HashMap();

            Map inputs = (Map) renderContext.get("inputs");

            Map<String, Object> params = mergeTaskVariables( inputs, outputs );

            String contextId = formLoaderService.initContext( formDefinition.getClass().getName(), params );

            if (contextId == null) return null;

            Map result = new HashMap(  );
            result.put( "handler", "FormModelerExampleProvider" );
            result.put( "destination", destination );
            result.put( "contextId", contextId );

            Gson gson = new Gson();
            return gson.toJson( result );
        }
        return null;
    }

    protected Map<String, Object> mergeTaskVariables(Map<String, Object> inputs, Map<String, Object> outputs) {
        Map<String, Object> result = new HashMap<String, Object>(  );

        // A better mechanism to merge the variables, but for as example works fine
        for (String key : inputs.keySet()) {
            if (key.startsWith( "in_" )) result.put( key.substring( 3 ), inputs.get( key ) );
        }
        for (String key : outputs.keySet()) {
            if (key.startsWith( "out_" )) result.put( key.substring( 3 ), outputs.get( key ) );
        }

        return result;
    }
}
