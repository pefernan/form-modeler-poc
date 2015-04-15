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

    @Override
    public PersonRecordForm getForm() {

        Map<String, Object> values = getValues();

        Constructor[] constructors = PersonRecordForm.class.getConstructors();

        List<Object> paramsValues;

        for (Constructor constructor : constructors) {
            if (constructor.getAnnotation( FormConstructor.class ) != null) {
                Class[] parameterTypes = constructor.getParameterTypes();
                Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();

                if ( parameterAnnotations != null && parameterAnnotations.length == parameterTypes.length ) {
                    int index = 0;

                    paramsValues = new ArrayList(  );

                    for(Annotation[] annotations : parameterAnnotations) {
                        Class parameterType = parameterTypes[index++];
                        for(Annotation annotation : annotations){
                            if(annotation instanceof ParamDataHolder){
                                ParamDataHolder myAnnotation = (ParamDataHolder) annotation;
                                System.out.println("param: " + parameterType.getName());
                                System.out.println("name : " + myAnnotation.name());
                                if (values.containsKey( myAnnotation.name() )) {
                                    Object value = values.get( myAnnotation.name() );
                                    if (value == null) {
                                        try {
                                            paramsValues.add( parameterType.newInstance() );
                                        } catch ( Exception e ) {
                                            e.printStackTrace();
                                        }
                                    } else if (value.getClass().equals( parameterType )) {
                                        paramsValues.add( value );
                                    }
                                }
                            }
                        }
                    }

                    if (paramsValues.size() == parameterTypes.length) {
                        try {
                            return ( PersonRecordForm ) constructor.newInstance( paramsValues.toArray() );
                        } catch ( Exception e ) {
                            log.warn( "Error creating Object instance: ", e );
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return null;
    }

    protected Map<String, Object> getValues() {
        Map<String, Object> values = new HashMap<String, Object>(  );

        values.put( "identifier", String.valueOf( System.currentTimeMillis() ));
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
