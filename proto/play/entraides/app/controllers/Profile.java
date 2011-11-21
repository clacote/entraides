package controllers;

import models.Member;


import play.Logger;
import play.data.validation.Email;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.mvc.Controller;

public class Profile extends Controller {

    public static void index() {
        render();
    }
    public static void edit(@Required String login) {
        Logger.info("Profil " + login);
        Member member = Member.findByLogin(login);
        Logger.info("Edition du profil " + member);
        render(member);
    }

    public static void registration_email(@Required @Email String email) {
        if (Validation.hasErrors()) {
            render("Profile/index.html");
        }
        session.put("email",email);
        render("Profile/identite.html");
    }

    public static void save(@Required String login, @Required String password, @Required String password2, @Email String email) {
        Logger.info("login {" + login + "}, email {" + email +"}");

        if (!password.equals(password2)){
            String msg_error_password = "Les mots de passe sont différents";
            Logger.error(msg_error_password);
            render("Profile/identite.html", msg_error_password, login, password);
        }
        if (validation.hasErrors()) {
            Logger.error(validation.errors().toString());
            render("Profile/identite.html", login);
        }
                
        Member member = new Member(login);
        member.email = session.get("email");;
        member.password = password;
        member.updateProfile();
        flash.success("Profil enregistré!");
        Logger.info("Profil enregistré");
        show(member.login);
    }

    public static void show(String login) {
        Logger.info("Profil " + login);
        Member member = Member.findByLogin(login);
        Logger.info("Profil " + member);
        render("Profile/show.html",member);
    }
}