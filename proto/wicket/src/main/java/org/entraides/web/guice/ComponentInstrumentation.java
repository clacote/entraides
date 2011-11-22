package org.entraides.web.guice;

import com.google.inject.Inject;
import com.google.inject.MembersInjector;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import org.apache.wicket.guice.FactoryGuiceProxyTargetLocator;
import org.apache.wicket.proxy.LazyInitProxyFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Mécanisme guice pour remplacer les références vers des objets guice par des proxy.
 */
public class ComponentInstrumentation {


    /**
     * Cet injecteur remplace le champ injecté par ul LazyInitProxy pour éviter les problèmes de sérialisation sur
     * tous les services injectés.
     * @param <T>
     */
    static class WicketProxyMembersInjector<T> implements MembersInjector<T> {

        private final Field field;

        WicketProxyMembersInjector(Field field) {

            this.field = field;
            field.setAccessible(true);
        }

        public void injectMembers(T t) {
            try {
                Object proxy = LazyInitProxyFactory.createProxy(field.getType(),
                        new FactoryGuiceProxyTargetLocator(field, null, false));

                field.set(t, proxy);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }

    /**
     * Enregistre un injecteur sur tous les champs non statiques avec l'annotation @Inject. Recherche les champs
     * dans la classe actuelle et dans les superclasses du projet.
     */
    static class ComponentTypeListener implements TypeListener {
        public <T> void hear(TypeLiteral<T> typeLiteral, TypeEncounter<T> typeEncounter) {
            Class<? super T> componentClass = typeLiteral.getRawType();
            while(acceptClass(componentClass)){
                for (Field field : componentClass.getDeclaredFields()) {
                    Inject injectAnnotation = field.getAnnotation(Inject.class);
                    if (!Modifier.isStatic(field.getModifiers()) && injectAnnotation != null)
                        typeEncounter.register(new WicketProxyMembersInjector<T>(field));
                }
                componentClass = componentClass.getSuperclass();
            }
        }

        private boolean acceptClass(Class componentClass) {
            return componentClass.getPackage().getName().startsWith("org.entraides");
        }
    }
}
