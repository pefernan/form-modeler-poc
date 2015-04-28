package org.kie.formModeler.example.client.view.jbpm.form.task;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jbpm.console.ng.ht.forms.client.display.displayers.task.AbstractHumanTaskFormDisplayer;
import org.kie.formModeler.example.client.view.jbpm.form.view.FormView;
import org.kie.formModeler.example.client.view.jbpm.form.view.FormViewManager;
import org.kie.formModeler.example.service.FormLoaderService;
import org.kie.formModeler.example.service.FormModelerExampleJBPMService;
import org.kie.formModeler.model.meta.FormModel;

/**
 * Created by pefernan on 4/15/15.
 */
@Dependent
public class FormModelerExampleHumanTaskFormDisplayer extends AbstractHumanTaskFormDisplayer {

    @Inject
    protected FormViewManager formViewManager;

    @Inject
    protected Caller<FormLoaderService> formLoaderService;

    @Inject
    protected Caller<FormModelerExampleJBPMService> formModelerExampleJBPMService;

    protected FormView view;

    protected String contextId;
    protected String destination;
    protected String taskStatus;


    @Override
    protected void initDisplayer() {
        formContainer.clear();
        JSONValue jsonValue = JSONParser.parseStrict( formContent );

        JSONObject jsonObject = jsonValue.isObject();

        if (jsonObject != null) {
            JSONValue jsonContextId = jsonObject.get( "contextId" );

            if (jsonContextId == null) return;

            contextId = jsonContextId.isString().stringValue();

            JSONValue jsonDestination = jsonObject.get( "destination" );

            if (jsonDestination == null) return;

            destination = jsonDestination.isString().stringValue();

            JSONValue jsonTaskStatus = jsonObject.get( "taskStatus" );

            if (jsonTaskStatus == null) return;

            taskStatus = jsonTaskStatus.isString().stringValue();

            view = formViewManager.getView( destination );

            if (view == null) return;

            formLoaderService.call( new RemoteCallback<FormModel>() {
                @Override
                public void callback( FormModel definition ) {
                    view.setModel(  definition );
                    view.setReadOnly( !"InProgress".equals( taskStatus ) );
                    formContainer.add( view );
                }
            } ).getContext( contextId );
        }
    }

    @Override
    protected void completeFromDisplayer() {
        if (view.validate()) {
            formModelerExampleJBPMService.call( getCompleteTaskRemoteCallback(),
                    getUnexpectedErrorCallback() ).completeTaskFromContext( contextId, taskId, identity.getIdentifier(),  view.getModel());
        }
    }

    @Override
    protected void saveStateFromDisplayer() {
        if (view.validate()) {
            formModelerExampleJBPMService.call( getSaveTaskStateCallback(),
                    getUnexpectedErrorCallback() ).saveTaskStateFromRenderContext( contextId, taskId, view.getModel() );
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
    public boolean supportsContent( String content ) {
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
