package org.entraides.web.login;

import com.google.inject.Inject;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.entraides.web.ServiceUser;
import org.entraides.web.templating.OneColPage;

public class PageInscription extends OneColPage {

    @Inject
    ServiceUser serviceUser;

    String email;

    public PageInscription(PageParameters pp) {
        super(pp);
        setStatelessHint(true);
        final TextField<String> field = new TextField<String>("email",
                new PropertyModel<String>(this, "email"));
        field.add(EmailAddressValidator.getInstance());
        field.setRequired(true);

        StatelessForm<?> statelessForm = new StatelessForm("statelessform") {

            @Override
            protected void onSubmit() {
                serviceUser.log("Création user pour : " + field.getDefaultModelObject());
                info("Un email de validation vous a été envoyé.");
            }
        };
        statelessForm.add(field);
        add(statelessForm);
        add(new FeedbackPanel("feedback"));
    }
}
