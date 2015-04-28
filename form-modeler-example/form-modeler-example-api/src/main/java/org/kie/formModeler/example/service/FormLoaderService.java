package org.kie.formModeler.example.service;

import java.util.Map;

import org.jboss.errai.bus.server.annotations.Remote;
import org.kie.formModeler.model.meta.FormModel;

/**
 * Created by pefernan on 4/14/15.
 */
@Remote
public interface FormLoaderService {

    String initContext(String className, Map<String, Object> params);

    FormModel getContext( String contextId );

    void removeContext( String contextId );
}
