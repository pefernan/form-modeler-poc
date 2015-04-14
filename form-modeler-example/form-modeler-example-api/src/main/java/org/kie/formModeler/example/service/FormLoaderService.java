package org.kie.formModeler.example.service;

import org.jboss.errai.bus.server.annotations.Remote;
import org.kie.formModeler.example.shared.PersonRecordForm;

/**
 * Created by pefernan on 4/14/15.
 */
@Remote
public interface FormLoaderService {

    PersonRecordForm getForm();

}
