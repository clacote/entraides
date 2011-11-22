package org.entraides.web.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * Point d'entrée de toute la configuration guice.
 * <p/>
 * L'injecteur est composé de module pour plus facilement permettre leur réutilisation
 * dans différents contextes runtime (prod, tests,...)
 */
public class ConfigWww extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return buildInjector();
    }

    Injector buildInjector() {
        return Guice.createInjector(
                new ModuleWicket(),
                new ModulePersistFilter(),
                new ModuleEntraidesWicket()
        );
    }

}