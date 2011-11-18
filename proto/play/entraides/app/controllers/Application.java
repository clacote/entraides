package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

/**
 * Main controller
 * @author Agnes <agnes.crepet@gmail.com>
 */
public class Application extends Controller {

    public static void index() {
        
        // DEV MODE : discard connected user if not found in DB (when you restarted your local dev application with initial data)
        String login = Security.connected();
        if (login != null && Member.findByLogin(login) == null) {
            session.remove("username");
        }
        render();
    }

    public static void members() {
        List<Member> members = Member.findAll();
        Logger.info(members.size() + " membres");
        render("Application/list.html", members);
    }
}