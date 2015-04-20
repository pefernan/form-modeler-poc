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
import org.kie.formModeler.model.DataHolderFieldMeta;
import org.kie.formModeler.model.DataHolderMeta;
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
    public static final NameDataHolderMeta _NAME = new NameDataHolderMeta( );
    public static final AgeDataHolderMeta _AGE = new AgeDataHolderMeta( );
    public static final MailDataHolderMeta _MAIL = new MailDataHolderMeta( );
    public static final ScoreDataHolderMeta _SCORE = new ScoreDataHolderMeta( );

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
        fieldNames.add( "name" );
        fieldNames.add( "age" );
        fieldNames.add( "mail" );
        fieldNames.add( "score" );
        dataHolderMetas.add( _NAME );
        dataHolderMetas.add( _AGE );
        dataHolderMetas.add( _MAIL );
        dataHolderMetas.add( _SCORE );
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

    @Portable
    public static class NameDataHolderMeta extends DataHolderMeta<HRInterview, String> {
        public static final NameDataHolderFieldMeta _NAME = new NameDataHolderFieldMeta();

        @Override
        public String getName() {
            return "name";
        }

        @Override
        public String getModel( HRInterview formMeta ) {
            return formMeta.getName();
        }

        @Override
        public DataHolderType getType() {
            return DataHolderType.BASIC;
        }
    }

    @Portable
    public static class NameDataHolderFieldMeta extends DataHolderFieldMeta<HRInterview, String> {

        @Override
        public String getName() {
            return "name";
        }

        @Override
        public String getValue( HRInterview meta ) {
            return meta.getName();
        }

        @Override
        public void setValue( HRInterview meta, String value ) {
            meta.setName( value );
        }
    }

    @Portable
    public static class AgeDataHolderMeta extends DataHolderMeta<HRInterview, Integer> {
        public static final AgeDataHolderFieldMeta _AGE = new AgeDataHolderFieldMeta();

        @Override
        public String getName() {
            return "age";
        }

        @Override
        public Integer getModel( HRInterview formMeta ) {
            return formMeta.getAge();
        }

        @Override
        public DataHolderType getType() {
            return DataHolderType.BASIC;
        }
    }

    @Portable
    public static class AgeDataHolderFieldMeta extends DataHolderFieldMeta<HRInterview, Integer> {

        @Override
        public String getName() {
            return "age";
        }

        @Override
        public Integer getValue( HRInterview meta ) {
            return meta.getAge();
        }

        @Override
        public void setValue( HRInterview meta, Integer value ) {
            meta.setAge( value );
        }
    }

    @Portable
    public static class MailDataHolderMeta extends DataHolderMeta<HRInterview, String> {
        public static final MailDataHolderFieldMeta _MAIL = new MailDataHolderFieldMeta();

        @Override
        public String getName() {
            return "mail";
        }

        @Override
        public String getModel( HRInterview formMeta ) {
            return formMeta.getMail();
        }

        @Override
        public DataHolderType getType() {
            return DataHolderType.BASIC;
        }
    }

    @Portable
    public static class MailDataHolderFieldMeta extends DataHolderFieldMeta<HRInterview, String> {

        @Override
        public String getName() {
            return "mail";
        }

        @Override
        public String getValue( HRInterview meta ) {
            return meta.getMail();
        }

        @Override
        public void setValue( HRInterview meta, String value ) {
            meta.setMail( value );
        }
    }

    @Portable
    public static class ScoreDataHolderMeta extends DataHolderMeta<HRInterview, Integer> {
        public static final ScoreDataHolderFieldMeta _SCORE = new ScoreDataHolderFieldMeta();

        @Override
        public String getName() {
            return "score";
        }

        @Override
        public Integer getModel( HRInterview formMeta ) {
            return formMeta.getScore();
        }

        @Override
        public DataHolderType getType() {
            return DataHolderType.BASIC;
        }
    }

    @Portable
    public static class ScoreDataHolderFieldMeta extends DataHolderFieldMeta<HRInterview, Integer> {

        @Override
        public String getName() {
            return "score";
        }

        @Override
        public Integer getValue( HRInterview meta ) {
            return meta.getScore();
        }

        @Override
        public void setValue( HRInterview meta, Integer value ) {
            meta.setScore( value );
        }
    }
}
