package org.entraides.web.guice;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.wicket.protocol.http.IWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;


/**
 * Filtrage des requêtes pour la gestion par Wicket.
 * <p/>
 */
@Singleton
public class WicketGuiceFilter extends WicketFilter {


    /**
     * L'application wicket instanciée par guice pour permettre l'injection au sein de
     * l'application d'autres composants.
     */
    private final WebApplication app;


    @Inject
    public WicketGuiceFilter(WebApplication app) {
        this.app = app;
    }


    @Override
    protected IWebApplicationFactory getApplicationFactory() {
        return new IWebApplicationFactory() {
            public WebApplication createApplication(WicketFilter filter) {
                return app;
            }

            public void destroy(WicketFilter filter) {
            }
        };
    }
}