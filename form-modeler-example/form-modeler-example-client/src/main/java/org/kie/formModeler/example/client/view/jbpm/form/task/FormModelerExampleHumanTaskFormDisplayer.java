package org.kie.formModeler.example.client.view.jbpm.form.task;

import javax.inject.Inject;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jbpm.console.ng.ht.forms.client.display.displayers.task.AbstractHumanTaskFormDisplayer;
import org.kie.formModeler.example.service.FormLoaderService;
import org.kie.formModeler.model.FormDefinition;

/**
 * Created by pefernan on 4/15/15.
 */
public class FormModelerExampleHumanTaskFormDisplayer extends AbstractHumanTaskFormDisplayer {

    @Inject
    private TaskFormView view;

    @Inject
    private Caller<FormLoaderService> formLoaderService;

    @Override
    protected void initDisplayer() {
        JSONValue jsonValue = JSONParser.parseStrict( formContent );

        JSONObject jsonObject = jsonValue.isObject();

        if (jsonObject != null) {
            JSONValue jsonContextId = jsonObject.get( "contextId" );

            if (jsonContextId == null) return;

            String contextId = jsonContextId.isString().stringValue();

            formLoaderService.call( new RemoteCallback<FormDefinition>() {
                @Override
                public void callback( FormDefinition definition ) {
                    view.loadModel( ( org.kie.formModeler.example.shared.PersonRecordForm ) definition );
                }
            } ).getContext( contextId );
        }
    }

    @Override
    protected void completeFromDisplayer() {
        if (view.validate()) {
            Window.alert("Complete!");
        }
    }

    @Override
    protected void saveStateFromDisplayer() {
        if (view.validate()) {
            Window.alert("Save!");
        }
    }

    @Override
    protected void startFromDisplayer() {
        super.start();
    }

    @Override
    protected void claimFromDisplayer() {
        super.claim();
    }

    @Override
    protected void releaseFromDisplayer() {
        super.claim();
    }

    @Override
    public
    boolean supportsContent( String content ) {
        try {
            JSONValue jsonValue = JSONParser.parseStrict( content );

            JSONObject jsonObject;

            if ((jsonObject = jsonValue.isObject()) == null) return false;

            jsonValue = jsonObject.get( "handler" );

            if (jsonValue.isString() == null) return false;

            return jsonValue.isString().stringValue().equals( "FormModelerExampleProvider" );
        } catch ( Exception e ) {
        }
        return false;
    }

    @Override
    public int getPriority() {
        return -1;
    }
}
