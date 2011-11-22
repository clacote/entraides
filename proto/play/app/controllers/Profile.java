package controllers;

import models.Member;


import org.apache.commons.lang.StringUtils;
import play.Logger;
import play.data.validation.Email;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.i18n.Messages;
import play.mvc.Controller;

public class Profile extends Controller {

    public static void index() {
        render();
    }

    public static void registration_email(@Required @Email String email) {
        if (Validation.hasErrors()) {
            render("Profile/index.html");
        }
        session.put("email", email);
        render("Profile/identite.html");
    }

    public static void save(@Required String login, @Required String password, @Required String password2, @Email String email) {
        Logger.info("login {" + login + "}, email {" + email + "}");

        if (!password.equals(password2)) {
            //FIXME : message erreur?
            // Mieux qu'une string? A internationaliser!
            String msg_error = Messages.get("profil.error.password");
            Logger.error(msg_error);
            render("Profile/identite.html", msg_error, login, password);
        }

        if (Member.findByLogin(login) != null) {
            //FIXME : message erreur?
            String msg_error = Messages.get("profil.error.duplicate.login", login);
            Logger.error(msg_error);
            render("Profile/identite.html", msg_error, login, password);
        }
        if (validation.hasErrors()) {
            Logger.error(validation.errors().toString());
            render("Profile/identite.html", login);
        }
        Member member = new Member(login);
        member.email = session.get("email");;
        member.password = password;
        member.updateProfile();
        session.put("login", login);
        Logger.info(Messages.get("profil.registred.log", login));
        show(member.login);
    }

    public static void update(String localisation, String bio, String[] interests, String newInterests) {
        String login = session.get("login");
        Member member = Member.findByLogin(login);
        member.localisation = localisation;
        member.bio = bio;
        if (interests != null) {
            member.updateInterests(interests);
        }
        if (validation.hasErrors()) {
            Logger.error(validation.errors().toString());
            render("Profile/complet.html", member, newInterests);
        }

        if (newInterests != null) {
            member.addInterests(StringUtils.splitByWholeSeparator(newInterests, ","));
        }

        member.updateProfile();
        flash.success(Messages.get("profil.complet.success", login));
        Logger.info(Messages.get("profil.complet.log", login));
        render("Application/index.html");
    }

    public static void show(String login) {
        Logger.info("Profil " + login);
        render("Profile/complet.html", login);
    }
}