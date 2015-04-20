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
@Named("TechInterview")
public class TechInterview extends FormMeta {
    public static final NameDataHolderMeta _NAME = new NameDataHolderMeta( );
    public static final AgeDataHolderMeta _AGE = new AgeDataHolderMeta( );
    public static final MailDataHolderMeta _MAIL = new MailDataHolderMeta( );
    public static final SkillsDataHolderMeta _SKILLS = new SkillsDataHolderMeta( );
    public static final ScoreDataHolderMeta _SCORE = new ScoreDataHolderMeta( );
    public static final TwitterDataHolderMeta _TWITTER = new TwitterDataHolderMeta( );
    

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
    @DataHolder( name="skills", type = DataHolderType.BASIC )
    private String skills;

    @NotEmpty
    @Size( min = 8, max = 30 )
    @DataHolder( name="mail", type = DataHolderType.BASIC )
    private String mail;

    @NotNull
    @Min( 1 )
    @Max( 5 )
    @DataHolder( name="score", type = DataHolderType.BASIC )
    private Integer score;

    @NotEmpty
    @Size( min = 4, max = 20 )
    @DataHolder( name="twitter", type = DataHolderType.BASIC )
    private String twitter;

    public TechInterview() {

    }

    @FormConstructor
    public TechInterview( @MapsTo("name") String name,
            @MapsTo("age") Integer age,
            @MapsTo("mail") String mail,
            @MapsTo("skills") String skills,
            @MapsTo("score") Integer score,
            @MapsTo("twitter") String twitter) {
        this.name = name;
        this.age = age;
        this.mail = mail;
        this. skills = skills;
        this.score = score;
        this.twitter = twitter;
    }

    @Override
    protected void init() {
        fieldNames.add( "name" );
        fieldNames.add( "age" );
        fieldNames.add( "mail" );
        fieldNames.add( "skills" );
        fieldNames.add( "score" );
        fieldNames.add( "twitter" );

        dataHolderMetas.add( _NAME );
        dataHolderMetas.add( _AGE );
        dataHolderMetas.add( _MAIL );
        dataHolderMetas.add( _SKILLS );
        dataHolderMetas.add( _SCORE );
        dataHolderMetas.add( _TWITTER );
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

    public String getSkills() {
        return skills;
    }

    public void setSkills( String skills ) {
        this.skills = skills;
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

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter( String twitter ) {
        this.twitter = twitter;
    }

    @Portable
    public static class NameDataHolderMeta extends DataHolderMeta<TechInterview, String> {
        public static final NameDataHolderFieldMeta _NAME = new NameDataHolderFieldMeta();

        @Override
        public String getName() {
            return "name";
        }

        @Override
        public String getModel( TechInterview formMeta ) {
            return formMeta.getName();
        }

        @Override
        public DataHolderType getType() {
            return DataHolderType.BASIC;
        }
    }

    @Portable
    public static class NameDataHolderFieldMeta extends DataHolderFieldMeta<TechInterview, String> {

        @Override
        public String getName() {
            return "name";
        }

        @Override
        public String getValue( TechInterview meta ) {
            return meta.getName();
        }

        @Override
        public void setValue( TechInterview meta, String value ) {
            meta.setName( value );
        }
    }

    @Portable
    public static class AgeDataHolderMeta extends DataHolderMeta<TechInterview, Integer> {
        public static final AgeDataHolderFieldMeta _AGE = new AgeDataHolderFieldMeta();

        @Override
        public String getName() {
            return "age";
        }

        @Override
        public Integer getModel( TechInterview formMeta ) {
            return formMeta.getAge();
        }

        @Override
        public DataHolderType getType() {
            return DataHolderType.BASIC;
        }
    }

    @Portable
    public static class AgeDataHolderFieldMeta extends DataHolderFieldMeta<TechInterview, Integer> {

        @Override
        public String getName() {
            return "age";
        }

        @Override
        public Integer getValue( TechInterview meta ) {
            return meta.getAge();
        }

        @Override
        public void setValue( TechInterview meta, Integer value ) {
            meta.setAge( value );
        }
    }

    @Portable
    public static class MailDataHolderMeta extends DataHolderMeta<TechInterview, String> {
        public static final MailDataHolderFieldMeta _MAIL = new MailDataHolderFieldMeta();

        @Override
        public String getName() {
            return "mail";
        }

        @Override
        public String getModel( TechInterview formMeta ) {
            return formMeta.getMail();
        }

        @Override
        public DataHolderType getType() {
            return DataHolderType.BASIC;
        }
    }

    @Portable
    public static class MailDataHolderFieldMeta extends DataHolderFieldMeta<TechInterview, String> {

        @Override
        public String getName() {
            return "mail";
        }

        @Override
        public String getValue( TechInterview meta ) {
            return meta.getMail();
        }

        @Override
        public void setValue( TechInterview meta, String value ) {
            meta.setMail( value );
        }
    }
    
    @Portable
    public static class SkillsDataHolderMeta extends DataHolderMeta<TechInterview, String> {
        public static final SkillsDataHolderFieldMeta _SKILLS = new SkillsDataHolderFieldMeta();

        @Override
        public String getName() {
            return "skills";
        }

        @Override
        public String getModel( TechInterview formMeta ) {
            return formMeta.getSkills();
        }

        @Override
        public DataHolderType getType() {
            return DataHolderType.BASIC;
        }
    }

    @Portable
    public static class SkillsDataHolderFieldMeta extends DataHolderFieldMeta<TechInterview, String> {

        @Override
        public String getName() {
            return "skills";
        }

        @Override
        public String getValue( TechInterview meta ) {
            return meta.getSkills();
        }

        @Override
        public void setValue( TechInterview meta, String value ) {
            meta.setSkills( value );
        }
    }

    @Portable
    public static class ScoreDataHolderMeta extends DataHolderMeta<TechInterview, Integer> {
        public static final ScoreDataHolderFieldMeta _SCORE = new ScoreDataHolderFieldMeta();

        @Override
        public String getName() {
            return "score";
        }

        @Override
        public Integer getModel( TechInterview formMeta ) {
            return formMeta.getScore();
        }

        @Override
        public DataHolderType getType() {
            return DataHolderType.BASIC;
        }
    }

    @Portable
    public static class ScoreDataHolderFieldMeta extends DataHolderFieldMeta<TechInterview, Integer> {

        @Override
        public String getName() {
            return "score";
        }

        @Override
        public Integer getValue( TechInterview meta ) {
            return meta.getScore();
        }

        @Override
        public void setValue( TechInterview meta, Integer value ) {
            meta.setScore( value );
        }
    }

    @Portable
    public static class TwitterDataHolderMeta extends DataHolderMeta<TechInterview, String> {
        public static final TwitterDataHolderFieldMeta _TWITTER = new TwitterDataHolderFieldMeta();

        @Override
        public String getName() {
            return "twitter";
        }

        @Override
        public String getModel( TechInterview formMeta ) {
            return formMeta.getTwitter();
        }

        @Override
        public DataHolderType getType() {
            return DataHolderType.BASIC;
        }
    }

    @Portable
    public static class TwitterDataHolderFieldMeta extends DataHolderFieldMeta<TechInterview, String> {

        @Override
        public String getName() {
            return "twitter";
        }

        @Override
        public String getValue( TechInterview meta ) {
            return meta.getTwitter();
        }

        @Override
        public void setValue( TechInterview meta, String value ) {
            meta.setTwitter( value );
        }
    }
}
