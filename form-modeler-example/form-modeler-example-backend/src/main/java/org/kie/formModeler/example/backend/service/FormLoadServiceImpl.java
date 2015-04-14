package org.kie.formModeler.example.backend.service;

import java.util.Calendar;
import javax.enterprise.context.ApplicationScoped;

import org.jboss.errai.bus.server.annotations.Service;
import org.kie.formModeler.example.model.Person;
import org.kie.formModeler.example.service.FormLoaderService;
import org.kie.formModeler.example.shared.PersonRecordForm;

/**
 * Created by pefernan on 4/14/15.
 */
@Service
@ApplicationScoped
public class FormLoadServiceImpl implements FormLoaderService {

    @Override
    public PersonRecordForm getForm() {
        Person person = createPerson();
        return new PersonRecordForm( String.valueOf( System.currentTimeMillis() ), person);
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
