package org.entraides.web.templating;

import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Pour les pages qui ne nécessitent que les header et footer
 */
public abstract class OneColPage extends BasePage{

    protected OneColPage() {
    }

    protected OneColPage(IModel<?> model) {
        super(model);
    }

    protected OneColPage(PageParameters pp) {
        super(pp);
    }
}
