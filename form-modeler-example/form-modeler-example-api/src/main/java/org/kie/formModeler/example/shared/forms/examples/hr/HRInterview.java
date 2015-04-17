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
import org.kie.formModeler.model.DataHolderMeta;
import org.kie.formModeler.model.FieldMeta;
import org.kie.formModeler.model.FormMeta;
import org.kie.formModeler.model.annotation.DataHolder;
import org.kie.formModeler.model.annotation.DataHolderType;
import org.kie.formModeler.model.annotation.Form;
import org.kie.formModeler.model.annotation.FormConstructor;

/**
 * Created by pefernan on 4/16/15.
 */

@Form
@Bindable
@Portable
@Named("HRInterview")
public class HRInterview extends FormMeta {

    @NotEmpty
    @Size( min = 4, max = 20 )
    @DataHolder( name="name", type = DataHolderType.BASIC )
    private String name;

    @NotNull
    @Min( 18 )
    @Max( 100 )
    @DataHolder( name="age", type = DataHolderType.BASIC )
    private Integer age;

    @NotEmpty
    @Size( min = 8, max = 30 )
    @DataHolder( name="mail", type = DataHolderType.BASIC )
    private String mail;

    @NotNull
    @Min( 1 )
    @Max( 5 )
    @DataHolder( name="score", type = DataHolderType.BASIC )
    private Integer score;

    public HRInterview() {

    }

    @FormConstructor
    public HRInterview( @MapsTo( "name" ) String name,
            @MapsTo( "age" ) Integer age,
            @MapsTo( "mail" ) String mail,
            @MapsTo( "score" ) Integer score) {
        this.name = name;
        this.age = age;
        this.mail = mail;
        this.score = score;
    }

    @Override
    protected void init() {
        DataHolderMeta<String> nameHolder = new DataHolderMeta<String>("name", name, DataHolderType.BASIC);
        DataHolderMeta<Integer> ageHolder = new DataHolderMeta<Integer>("age", age, DataHolderType.BASIC);
        DataHolderMeta<String> mailHolder = new DataHolderMeta<String>("mail", mail, DataHolderType.BASIC);
        DataHolderMeta<Integer> scoreHolder = new DataHolderMeta<Integer>("score", score, DataHolderType.BASIC);

        dataHolders.put( "name", nameHolder );
        dataHolders.put( "age", ageHolder );
        dataHolders.put( "mail", mailHolder );
        dataHolders.put( "score", scoreHolder );

        FieldMeta<String> nameField = new FieldMeta<String>("name", "name");
        FieldMeta<Integer> ageField = new FieldMeta<Integer>("age", "age");
        FieldMeta<String> mailField = new FieldMeta<String>("mail", "mail");
        FieldMeta<Integer> scoreField = new FieldMeta<Integer>("score", "score");

        fields.put( "name", nameField );
        fields.put( "age", ageField );
        fields.put( "mail", mailField );
        fields.put( "score", scoreField );
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
