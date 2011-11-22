package org.entraides.web;

import com.google.inject.Inject;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.entraides.web.templating.OneColPage;

public class HomePage extends OneColPage {

    @Inject
    ServiceUser user;

    public HomePage(PageParameters pp) {
        super(pp);
        user.log("Injection ok");
    }
}
