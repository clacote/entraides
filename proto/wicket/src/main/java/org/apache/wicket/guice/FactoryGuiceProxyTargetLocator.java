package org.apache.wicket.guice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
*/
public class FactoryGuiceProxyTargetLocator extends GuiceProxyTargetLocator {
    public FactoryGuiceProxyTargetLocator(Field field, Annotation bindingAnnotation, boolean optional) {
        super(field, bindingAnnotation, optional);
    }
}
