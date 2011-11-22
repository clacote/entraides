package org.entraides.web.wicket;

import org.apache.wicket.protocol.http.WebApplication;
import org.entraides.web.HomePage;
import org.entraides.web.login.PageInscription;

/**
 * Définition de l'application au sens wicket. Gère le mapping des pages (url).
 *
 * Pour démarrer simplement le serveur web, lancer org.entraides.Start#main(String[])
 */
public class ApplicationWicket extends WebApplication {
    @Override
    public Class<HomePage> getHomePage() {
        return HomePage.class;
    }

    @Override
    protected void init() {
        mountPage("user/creation", PageInscription.class);
    }
}
