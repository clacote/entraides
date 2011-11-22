package controllers;

import models.Member;

/**
 * By default, the login page will accept any login/password.
 * To customize it application has to provide a Security provider which extend Secure.Security class
 * 
 * Entraides authentication (not OpenID)
 * 
 * @author Agnes <agnes.crepet@gmail.com>
 */
public class Security extends Secure.Security {



  public static boolean authenticate(String username, String password) {
      Member member = Member.findByLogin(username);
      if (member!= null && member.password.equals(password))
          return true;
      else
          return false;
  }
    


    
    static void onDisconnected() {
        Application.index();
    }
}
   