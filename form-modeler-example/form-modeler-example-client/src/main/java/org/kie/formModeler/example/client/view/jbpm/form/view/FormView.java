package org.kie.formModeler.example.client.view.jbpm.form.view;

import java.util.Set;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Composite;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.kie.formModeler.model.FormMeta;

/**
 * Created by pefernan on 4/17/15.
 */
public abstract class FormView<T extends FormMeta> extends Composite {

    @Inject
    protected Validator validator;

    protected DataBinder<T> binder;

    protected T model;

    public void loadModel( T model ) {
        doBind( model );
        this.model = binder.getModel();
        clearFieldErrors();
    }

    protected abstract void doBind( T model );

    public abstract void setReadOnly( boolean readOnly );

    protected void clearFieldErrors() {
        for (String field : model.getFieldNames()) {
            Element group = Document.get().getElementById( field + "_control_group" );
            Element helpBlock = Document.get().getElementById( field + "_help_block" );
            if ( group != null ) group.removeClassName( "error" );
            if ( helpBlock != null ) helpBlock.setInnerHTML( "" );
        }
    }

    public boolean validate() {

        boolean isValid = true;

        clearFieldErrors();

        Set<ConstraintViolation<T>> result = validator.validate( model );
        for (ConstraintViolation validation : result) {
            String property = validation.getPropertyPath().toString().replace( ".", "_" );
            if (!model.getFieldNames().contains( property )) continue;
            isValid = false;
            Element group = Document.get().getElementById( property + "_control_group" );
            Element helpBlock = Document.get().getElementById( property + "_help_block" );
            if ( group != null ) group.addClassName( "error" );
            if ( helpBlock != null ) helpBlock.setInnerHTML( validation.getMessage() );
        }
        return isValid;
    }

    public T getForm() {
        return model;
    }
}
