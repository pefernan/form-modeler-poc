package org.kie.formModeler.codegen.services.datamodeller.impl;

import javax.inject.Inject;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.kie.formModeler.codegen.FormSourcesGenerator;
import org.kie.formModeler.codegen.services.datamodeller.DataModellerFormGenerator;
import org.kie.formModeler.model.DataHolder;
import org.kie.formModeler.model.FieldDefinition;
import org.kie.formModeler.model.FormDefinition;
import org.kie.formModeler.model.impl.AbstractIntputFieldDefinition;
import org.kie.formModeler.service.FieldManager;
import org.kie.workbench.common.screens.datamodeller.model.AnnotationDefinitionTO;
import org.kie.workbench.common.screens.datamodeller.model.AnnotationTO;
import org.kie.workbench.common.screens.datamodeller.model.DataObjectTO;
import org.kie.workbench.common.screens.datamodeller.model.ObjectPropertyTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pefernan on 4/29/15.
 */
public class DataModellerFormGeneratorImpl implements DataModellerFormGenerator {
    private static transient Logger log = LoggerFactory.getLogger( DataModellerFormGeneratorImpl.class );

    public static final String[] RESTRICTED_PROPERTY_NAMES = new String[]{"serialVersionUID"};

    @Inject
    protected FieldManager fieldManager;

    @Inject
    protected FormSourcesGenerator formSourcesGenerator;

    @Override
    public void generateFormForDataObject( DataObjectTO dataObject ) {

        if (dataObject.getProperties().isEmpty()) return;

        FormDefinition form = new FormDefinition();
        form.setName( dataObject.getName() );

        String holderName = WordUtils.uncapitalize( dataObject.getName() );

        DataHolder holder = new DataHolder( holderName, dataObject.getClassName() );

        form.addDataHolder( holder );

        for (ObjectPropertyTO property : dataObject.getProperties()) {
            if ( ArrayUtils.contains(RESTRICTED_PROPERTY_NAMES, property.getName()) ) continue;

            String propertyName = holderName + "_" + property.getName();
            FieldDefinition field = fieldManager.getDefinitionByValueType( property.getClassName() );

            if (field == null) continue;

            form.addField( field );

            field.setName( propertyName );
            String label = getPropertyLabel( property );
            field.setLabel( label );
            field.setBindingExpression( holderName + "." + property.getName() );

            if (field instanceof AbstractIntputFieldDefinition) {
                ((AbstractIntputFieldDefinition) field).setPlaceHolder( label );
            }
        }

        if (form.getFields().isEmpty()) return;

        formSourcesGenerator.generateFormSources( form, dataObject.getPath() );

    }

    private String getPropertyLabel( ObjectPropertyTO property ) {
        AnnotationTO labelAnnotation = property.getAnnotation( AnnotationDefinitionTO.LABEL_ANNOTATION );
        if ( labelAnnotation != null ) return labelAnnotation.getValue( AnnotationDefinitionTO.VALUE_PARAM ).toString();

        return property.getName();
    }


}
