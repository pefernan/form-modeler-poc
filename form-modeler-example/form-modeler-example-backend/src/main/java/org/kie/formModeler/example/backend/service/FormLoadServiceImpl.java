package org.kie.formModeler.example.backend.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;

import org.jboss.errai.bus.server.annotations.Service;
import org.kie.formModeler.example.model.Person;
import org.kie.formModeler.example.service.FormLoaderService;
import org.kie.formModeler.example.shared.PersonRecordForm;
import org.kie.formModeler.model.FormDefinition;
import org.kie.formModeler.model.annotation.FormConstructor;
import org.kie.formModeler.model.annotation.ParamDataHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pefernan on 4/14/15.
 */
@Service
@ApplicationScoped
public class FormLoadServiceImpl implements FormLoaderService {

    private static transient Logger log = LoggerFactory.getLogger( FormLoadServiceImpl.class );

    protected Map<String, FormDefinition> contexts = new HashMap<String, FormDefinition>();

    @Override
    public PersonRecordForm getForm() {

        String context = initContext( PersonRecordForm.class.getName(), getValues() );

        if ( context != null ) {
            return ( PersonRecordForm ) contexts.get( context );
        }

        return null;
    }

    @Override
    public String initContext( String className, Map<String, Object> values ) {

        try {
            // Obtain the constructors from the className
            Constructor[] constructors = Class.forName( className ).getConstructors();

            for ( Constructor constructor : constructors ) {
                // Search the constructor that is annotated as FormConstructor and has the right annotations
                // for the values we want to set
                if ( constructor.getAnnotation( FormConstructor.class ) != null ) {
                    Class[] parameterTypes = constructor.getParameterTypes();
                    Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();

                    if ( parameterAnnotations != null && parameterAnnotations.length == parameterTypes.length ) {
                        int index = 0;

                        List paramsValues = new ArrayList();

                        for ( Annotation[] annotations : parameterAnnotations ) {
                            Class parameterType = parameterTypes[ index++ ];
                            for ( Annotation annotation : annotations ) {
                                if ( annotation instanceof ParamDataHolder ) {
                                    ParamDataHolder myAnnotation = ( ParamDataHolder ) annotation;
                                    if ( values.containsKey( myAnnotation.name() ) ) {
                                        Object value = values.get( myAnnotation.name() );
                                        if ( value == null ) {
                                            try {
                                                paramsValues.add( parameterType.newInstance() );
                                            } catch ( Exception e ) {
                                                log.warn( "Error creating parameter instance: ", e );
                                            }
                                        } else if ( value.getClass().equals( parameterType ) ) {
                                            paramsValues.add( value );
                                        }
                                    }
                                }
                            }
                        }
                        // If the constructor parameter size == parameter values that we can add we create the new instance
                        if ( paramsValues.size() == parameterTypes.length ) {
                            try {
                                FormDefinition definition = ( FormDefinition ) constructor.newInstance( paramsValues.toArray() );
                                if ( definition != null ) {
                                    String contextId = String.valueOf( System.currentTimeMillis() );
                                    contexts.put( contextId, definition );
                                    return contextId;
                                }
                            } catch ( Exception e ) {
                                log.warn( "Error creating Object instance: ", e );
                            }
                        }
                    }
                }
            }
        } catch ( ClassNotFoundException e ) {
            log.warn( "Error getting class: ", e );
        }

        return null;
    }

    @Override
    public FormDefinition getContext( String contextId ) {
        return contexts.get( contextId );
    }

    @Override
    public void removeContext( String contextId ) {
        contexts.remove( contextId );
    }

    protected Map<String, Object> getValues() {
        Map<String, Object> values = new HashMap<String, Object>();

        values.put( "identifier", String.valueOf( System.currentTimeMillis() ) );
        values.put( "person", createPerson() );

        return values;
    }

    protected Person createPerson() {
        Person person = new Person();

        person.setName( "Pere" );
        person.setSurname( "Fernandez" );
        person.setAddress( "Sitges" );

        Calendar birth = Calendar.getInstance();

        birth.set( Calendar.DAY_OF_MONTH, 24 );
        birth.set( Calendar.MONTH, Calendar.FEBRUARY );
        birth.set( Calendar.YEAR, 1981 );

        person.setBirthday( birth.getTime() );

        return person;
    }
}
