package org.entraides.web.guice;

import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;

/**
 * Paramètrage de la servlet pour la gestion de la persistance (session in view).
 *
 * On exclut toutes les ressources statiques de la persistance.
 *
 * Cette classe n'est pas sensée changer.
*/
public class ModulePersistFilter extends ServletModule {
    @Override
    protected void configureServlets() {
        install(new JpaPersistModule("dev"));
        filterRegex("/*^(?!.*(swf|jpg|jpeg|png|ico|gif|css|js)).*$").through(PersistFilter.class);
    }
}
