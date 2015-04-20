package org.kie.formModeler.example.client.view.jbpm.form.view;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.errai.ioc.client.container.IOCBeanDef;
import org.jboss.errai.ioc.client.container.SyncBeanManager;

/**
 * Created by pefernan on 4/17/15.
 */
@ApplicationScoped
public class FormViewManager {

    @Inject
    protected SyncBeanManager iocManager;

    private Map<String, FormView> availableViews = new HashMap<String, FormView>(  );

    @PostConstruct
    public void init() {
        final Collection<IOCBeanDef<FormView>> formViewsBeans = iocManager.lookupBeans(FormView.class);
        if (formViewsBeans != null) {
            for (final IOCBeanDef formViewDef : formViewsBeans) {
                for (Object qualifier : formViewDef.getQualifiers()) {
                    if (qualifier instanceof Named ) {
                        availableViews.put( ((Named) qualifier).value(), ( FormView ) formViewDef.getInstance() );
                    }
                }
            }
        }
    }


    public FormView getView(String viewName) {
        return availableViews.get( viewName );
    }

}
