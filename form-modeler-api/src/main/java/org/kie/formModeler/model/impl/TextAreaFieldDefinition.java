package org.kie.formModeler.model.impl;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.kie.formModeler.model.FieldDefinition;

/**
 * Created by pefernan on 3/19/15.
 */
@Portable
public class TextAreaFieldDefinition extends FieldDefinition<String> {

    protected Integer width = 15;
    protected Integer height = 4;

    @Override
    public String getType() {
        return String.class.getName();
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth( Integer width ) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight( Integer height ) {
        this.height = height;
    }
}
