package org.entraides.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "ENT_USER")
public class User implements Serializable{

// -------------------------- INNER CLASSES --------------------------

// -------------------------- STATIC --------------------------


// -------------------------- INSTANCE --------------------------


    private Date created = new Date();

    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private Date lastSession;

    private String login;

    /**
     * L'utilisateur a indiqué qu'il ne souhaitait plus être contacté.
     */
    private Boolean nePlusContacter;

    private String password;

    /**
     * Le nombre de session créés par l'utilisateur
     */
    private int sessionCount;


    private String userAgent;

    /**
     * Clef d'identification de l'utilisateur (cookie).
     */
    @Column(unique = true)
    private String userKey;

// ------------------------ AUTRES METHODES ------------------------


// ------------------------ CANONICAL METHODS ------------------------

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", userKey='" + userKey + '\'' +
                ", created=" + created +
                ", nePlusContacter=" + nePlusContacter +
                ", sessioncount=" + sessionCount +
                '}';
    }

// --------------------- GETTER / SETTER METHODS --------------------- 

    public Date getCreated() {
        return created;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null) {
            this.email = null;
            return;
        }
        this.email = email.toLowerCase();
    }

    public Long getId() {
        return id;
    }

    public Date getLastSession() {
        return lastSession;
    }

    public void setLastSession(Date lastSession) {
        this.lastSession = lastSession;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Boolean getNePlusContacter() {
        return nePlusContacter;
    }

    public void setNePlusContacter(Boolean nePlusContacter) {
        this.nePlusContacter = nePlusContacter;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSessionCount() {
        return sessionCount;
    }

    public void setSessionCount(int sessionCount) {
        this.sessionCount = sessionCount;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String uniqueID) {
        userKey = uniqueID;
    }

}
