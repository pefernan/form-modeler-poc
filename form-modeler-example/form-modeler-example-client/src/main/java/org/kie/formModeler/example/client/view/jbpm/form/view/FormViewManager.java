package org.kie.formModeler.example.client.view.jbpm.form.view;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
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
        final Collection<IOCBeanDef<FormView>> processDisplayersBeans = iocManager.lookupBeans(FormView.class);
        if (processDisplayersBeans != null) {
            for (final IOCBeanDef displayerDef : processDisplayersBeans) {
                for (Object qualifier : displayerDef.getQualifiers()) {
                    if (qualifier instanceof Named ) {
                        availableViews.put( ((Named) qualifier).value(), ( FormView ) displayerDef.getInstance() );
                    }
                }
            }
        }
    }


    public FormView getView(String viewName) {
        return availableViews.get( viewName );
    }

}
