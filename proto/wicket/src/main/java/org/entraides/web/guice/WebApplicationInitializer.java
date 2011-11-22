package org.entraides.web.guice;

import org.apache.wicket.protocol.http.WebApplication;

/**
 * Initializer de l'application. Permet de sortir le code de la classe dérivée de WebApplication.
 */
public interface WebApplicationInitializer {

    void init(WebApplication application);
}
