package org.kie.formModeler.example.shared.forms.examples.hr;

import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.kie.formModeler.model.meta.FormModel;

/**
 * Created by pefernan on 4/16/15.
 */
@Bindable
@Portable
@Named("HRInterview")
public class HRInterviewFormModel extends FormModel {

    @NotEmpty
    @Size( min = 4, max = 20 )
    private String name;

    @NotNull
    @Min( 18 )
    @Max( 100 )
    private Integer age;

    @NotEmpty
    @Size( min = 8, max = 30 )
    private String mail;

    @NotNull
    @Min( 1 )
    @Max( 5 )
    private Integer score;

    public HRInterviewFormModel() {

    }

    public HRInterviewFormModel( @MapsTo("name") String name,
            @MapsTo("age") Integer age,
            @MapsTo("mail") String mail,
            @MapsTo("score") Integer score ) {
        this.name = name;
        this.age = age;
        this.mail = mail;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge( Integer age ) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail( String mail ) {
        this.mail = mail;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore( Integer score ) {
        this.score = score;
    }
}
