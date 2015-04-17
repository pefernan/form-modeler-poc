package org.kie.formModeler.example.backend.service;

import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.errai.bus.server.annotations.Service;
import org.jbpm.console.ng.bd.service.KieSessionEntryPoint;
import org.jbpm.console.ng.ht.service.TaskLifeCycleService;
import org.jbpm.console.ng.ht.service.TaskOperationsService;
import org.kie.formModeler.example.service.FormLoaderService;
import org.kie.formModeler.example.service.FormModelerExampleJBPMService;
import org.kie.formModeler.model.FormMeta;

/**
 * Created by pefernan on 4/16/15.
 */
@Service
@ApplicationScoped
public class FormModelerExampleJBPMServiceImpl implements FormModelerExampleJBPMService {
    @Inject
    FormLoaderService formLoaderService;

    @Inject
    private KieSessionEntryPoint kieSessionEntryPoint;

    @Inject
    private TaskLifeCycleService taskServices;

    @Inject
    private TaskOperationsService taskOperationsServices;

    public Long startProcessFromRenderContext(String ctxUID, String domainId, String processId, String correlationKey, FormMeta definition) {
        clearContext( ctxUID );
        return kieSessionEntryPoint.startProcess(domainId, processId, correlationKey, getFormResult( definition, false ));
    }

    @Override
    public Long saveTaskStateFromRenderContext(String ctxUID, Long taskId, FormMeta definition) {
        return taskOperationsServices.saveContent( taskId, getFormResult( definition, true ) );
    }

    @Override
    public void completeTaskFromContext( String ctxUID, Long taskId, String identityName, FormMeta definition ) {
        clearContext( ctxUID );
        taskServices.complete(taskId,  identityName, getFormResult( definition, true ));
    }

    protected Map<String, Object> getFormResult( FormMeta formMeta, boolean addPreffix ) {
        HashMap<String, Object> result = new HashMap<String, Object>(  );

        if ( formMeta == null) return result;

        for (String holderId : formMeta.getDataHoldersIdentifiers()) {
            String key = holderId;

            if (addPreffix) key = "out_" + holderId;

            result.put( key, formMeta.getDataHolder( holderId ).getModel() );
        }

        return result;
    }

    @Override
    public void clearContext(String ctxUID) {
        formLoaderService.removeContext( ctxUID );
    }

}
