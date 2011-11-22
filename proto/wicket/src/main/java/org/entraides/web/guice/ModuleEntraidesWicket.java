package org.entraides.web.guice;

import com.google.inject.*;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.protocol.http.WebApplication;
import org.entraides.web.wicket.ApplicationWicket;

/**
 * Dans cette classe, les différents binding de la couche web sont ajoutés au fur et à mesure de l'ajout de
 * fonctionnalités
 */
public class ModuleEntraidesWicket extends AbstractModule{

    @Override
    protected void configure() {
//        bind(WebApplication.class).to(ApplicationWicket.class).in(Singleton.class);
    }

    @Provides
    @Inject
    @Singleton
    protected WebApplication buildApplication(ApplicationWicket app, Injector injector){
        app.getComponentInstantiationListeners().add(new GuiceComponentInjector(app, injector));
        return app;
    }
}
