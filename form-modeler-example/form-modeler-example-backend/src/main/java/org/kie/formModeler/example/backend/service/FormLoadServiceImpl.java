package org.kie.formModeler.example.backend.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;

import org.jboss.errai.bus.server.annotations.Service;
import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.kie.formModeler.example.model.Person;
import org.kie.formModeler.example.service.FormLoaderService;
import org.kie.formModeler.example.shared.forms.examples.person.PersonRecordForm;
import org.kie.formModeler.model.FormMeta;
import org.kie.formModeler.model.annotation.FormConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pefernan on 4/14/15.
 */
@Service
@ApplicationScoped
public class FormLoadServiceImpl implements FormLoaderService {

    private static transient Logger log = LoggerFactory.getLogger( FormLoadServiceImpl.class );

    protected Map<String, FormMeta> contexts = new HashMap<String, FormMeta>();

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
                                if ( annotation instanceof MapsTo ) {
                                    MapsTo myAnnotation = ( MapsTo ) annotation;
                                    if ( values.containsKey( myAnnotation.value() ) ) {
                                        Object value = values.get( myAnnotation.value() );
                                        if ( value == null ) {
                                            try {
                                                paramsValues.add( parameterType.newInstance() );
                                            } catch ( Exception e ) {
                                                log.info( "Error creating parameter instance '" + myAnnotation.value() + "' for type '" + parameterType.getName() + "'" );
                                                paramsValues.add( null );
                                            }
                                        } else if ( value.getClass().equals( parameterType ) ) {
                                            paramsValues.add( value );
                                        }
                                    } else  {
                                        paramsValues.add( null );
                                    }
                                }
                            }
                        }
                        // If the constructor parameter size == parameter values that we can add we create the new instance
                        if ( paramsValues.size() == parameterTypes.length ) {
                            try {
                                FormMeta definition = ( FormMeta ) constructor.newInstance( paramsValues.toArray() );
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
    public FormMeta getContext( String contextId ) {
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
