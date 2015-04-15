package org.kie.formModeler.example.service;

import java.util.Map;

import org.jboss.errai.bus.server.annotations.Remote;
import org.kie.formModeler.example.shared.PersonRecordForm;
import org.kie.formModeler.model.FormDefinition;

/**
 * Created by pefernan on 4/14/15.
 */
@Remote
public interface FormLoaderService {

    PersonRecordForm getForm();

    String initContext(String className, Map<String, Object> params);

    FormDefinition getContext( String contextId );

    void removeContext( String contextId );
}
