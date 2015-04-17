package org.kie.formModeler.model;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * Created by pefernan on 4/16/15.
 */
@Portable
public class FieldMeta<T> {
    private String name;
    private String bindingExpression;

    public FieldMeta( String name, String bindingExpression ) {
        this.name = name;
        this.bindingExpression = bindingExpression;
    }

    public FieldMeta() {

    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getBindingExpression() {
        return bindingExpression;
    }

    public void setBindingExpression( String bindingExpression ) {
        this.bindingExpression = bindingExpression;
    }
}
