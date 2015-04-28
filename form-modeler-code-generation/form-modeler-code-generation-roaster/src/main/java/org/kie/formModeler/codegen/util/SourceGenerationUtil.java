package org.kie.formModeler.codegen.util;

import java.util.Date;

/**
 * Created by pefernan on 4/27/15.
 */
public interface SourceGenerationUtil {

    public static final String[] BASIC_TYPES = new String[]{
            String.class.getName(),
            Integer.class.getName(),
            Short.class.getName(),
            Long.class.getName(),
            Float.class.getName(),
            Double.class.getName(),
            Boolean.class.getName(),
            Date.class.getName(),
            java.math.BigDecimal.class.getName(),
            java.math.BigInteger.class.getName(),
            int.class.getName(),
            long.class.getName(),
            boolean.class.getName(),
            short.class.getName(),
            double.class.getName()
    };

    public static final String JAVA_LANG_OVERRIDE = "java.lang.Override";

    public static final String ERRAI_PORTABLE = "org.jboss.errai.common.client.api.annotations.Portable";
    public static final String ERRAI_BINDABLE = "org.jboss.errai.databinding.client.api.Bindable";
    public static final String ERRAI_BOUND = "org.jboss.errai.ui.shared.api.annotations.Bound";

    public static final String ERRAI_MAPS_TO = "org.jboss.errai.common.client.api.annotations.MapsTo";
    public static final String ERRAI_TEMPLATED = "org.jboss.errai.ui.shared.api.annotations.Templated";

    public static final String INJECT_NAMED = "javax.inject.Named";
    public static final String INJECT_INJECT = "javax.inject.Inject";

    public static final String VALIDATION_VALID = "javax.validation.Valid";
    public static final String VALIDATION_NOT_NULL = "javax.validation.constraints.NotNull";
    public static final String HIBERNATE_NOT_EMPTY = "org.hibernate.validator.constraints.NotEmpty";

    public static final String FORM_VIEW_CLASS = "org.kie.formModeler.example.client.view.jbpm.form.view.FormView";
}
