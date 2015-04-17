package org.kie.formModeler.example.backend.jbpm.form;

import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gson.Gson;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.jbpm.kie.services.impl.form.provider.AbstractFormProvider;
import org.jbpm.services.api.model.ProcessDefinition;
import org.kie.api.task.model.Task;
import org.kie.formModeler.example.service.FormLoaderService;
import org.kie.formModeler.model.FormMeta;

/**
 * Created by pefernan on 4/15/15.
 */
@Dependent
public class FormModelerExampleProvider extends AbstractFormProvider {


    @Inject
    protected FormLoaderService formLoaderService;


    @PostConstruct
    protected void init() {
    }

    @Override
    public int getPriority() {
        return -1;
    }

    @Override
    public String render( String name, ProcessDefinition process, Map<String, Object> renderContext ) {
        FormMeta formMeta = BeanProvider.getContextualReference( process.getId(), true, FormMeta.class );

        if ( formMeta != null) {
            Map<String, Object> outputs = ( Map<String, Object> ) renderContext.get( "outputs" );
            return getFormRenderingInfo( process.getId(), formMeta, outputs );
        }
        return null;
    }

    @Override
    public String render( String s, Task task, ProcessDefinition processDefinition, Map<String, Object> renderContext ) {
        String taskName = getTaskFormName( task );

        FormMeta formMeta = BeanProvider.getContextualReference( taskName , true, FormMeta.class );

        if ( formMeta != null) {
            Map<String, Object> outputs = ( Map<String, Object> ) renderContext.get( "outputs" );

            // Reset values if there task doesn't has output content
            if (task.getTaskData().getOutputContentId() == -1) {
                for (String key : outputs.keySet()) {
                    outputs.put( key, null );
                }
            }
            Map result = mergeTaskVariables( ( Map<String, Object> ) renderContext.get( "inputs" ), outputs );

            return getFormRenderingInfo( taskName, formMeta, result );
        }

        return null;
    }

    @Override
    protected String getFormSuffix() {
        return "";
    }

    protected String getFormRenderingInfo (String destination, FormMeta form, Map<String, Object> params) {
        String contextId = formLoaderService.initContext( form.getClass().getName(), params );

        if (contextId == null) return null;

        Map result = new HashMap(  );
        result.put( "handler", "FormModelerExampleProvider" );
        result.put( "destination", destination );
        result.put( "contextId", contextId );

        Gson gson = new Gson();
        return gson.toJson( result );
    }

    protected Map<String, Object> mergeTaskVariables(Map<String, Object> inputs, Map<String, Object> outputs) {
        Map<String, Object> result = new HashMap<String, Object>(  );

        // A better mechanism to merge the variables, but for as example works fine
        for (String key : inputs.keySet()) {
            if (key.startsWith( "in_" )) result.put( key.substring( 3 ), inputs.get( key ) );
        }
        for (String key : outputs.keySet()) {
            if (!key.startsWith( "out_" )) continue;
            Object value = outputs.get( key );
            String paramKey = key.substring( 4 );
            if (value != null || (value == null && !result.containsKey( paramKey ))) result.put( paramKey, outputs.get( key ) );
        }

        return result;
    }
}
