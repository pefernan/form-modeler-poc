package org.kie.formModeler.example.model;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;

/**
 * Created by pefernan on 4/14/15.
 */
@Bindable
@Portable
public class Person {

    @NotEmpty
    private String name;

    @NotEmpty
    @Size( min = 4, max = 20 )
    private String surname;

    @NotNull
    private Date birthday;

    @NotEmpty
    private String address;

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname( String surname ) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday( Date birthday ) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress( String address ) {
        this.address = address;
    }
}
