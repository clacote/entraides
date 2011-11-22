package org.entraides.web.templating;

import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;

/**
 * Insertion des css et script partagés dans tout le site
 */
public abstract class BasePage extends WebPage {

    protected BasePage() {
    }

    protected BasePage(IModel<?> model) {
        super(model);
    }

    public BasePage(PageParameters pp) {
        super(pp);
    }

    /**
     * Insertion de toutes les css nécessaires.
     * 960.css    : grid pour assurer des alignements constants,
     * base.css   : remise au propre des styles par défaut,
     * style.css : layout du site (header, colonnes, ...)
     *
     * L'inclusion des styles par wicket permet de générer un numéro de version (checksum) qui, en paramétrant
     * la gestion des headers garantie une mise en cache à vie de la css dans le navigateur et supprime des requêtes.
     */
    @Override
    public void renderHead(IHeaderResponse response) {
        response.renderCSSReference(new PackageResourceReference(BasePage.class, "960.css"));
        response.renderCSSReference(new PackageResourceReference(BasePage.class, "base.css"));
        response.renderCSSReference(new PackageResourceReference(BasePage.class, "style.css"));
    }
}
