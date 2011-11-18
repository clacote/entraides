package models;


import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import org.hibernate.annotations.IndexColumn;
import play.data.validation.Required;
import play.db.jpa.*;

/**
 * An Entraides member.
 * @author Agnes <agnes.crepet@gmail.com>
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Member.QUERY_BYLOGIN, query = "from Member m where m.login=:login"),
})
public class Member extends Model {

    static final String QUERY_BYLOGIN = "MemberByLogin";
    /** Internal login : functional key */
    @Column(nullable = false, unique = true, updatable = false)
    @IndexColumn(name = "login_UK_IDX", nullable = false)
    @Required
    public String login;
    @Required
    public String email;
    public String firstname;
    @Required
    public String password;
    public String lastname;
    /** Name under which he wants to be displayed */
    @Required
    public String displayName;
    /** User-defined description, potentially as MarkDown */
    @Lob
    @Required
    public String description;
    /** Twitter account name */
    public String twitterName;
    /** Google+ ID, i.e https://plus.google.com/{ThisFuckingLongNumberInsteadOfABetterId} as seen on Google+' profile link */
    public String googlePlusId;



    public Member(String login) {
        this.login = login;
    }



    /**
     * Find unique member having given login.
     * Seems this request is very often used, it's better to used it (more efficient with named query usage) instead of Play! find("byLogin", login)
     * @param login Login to find
     * @return Member found, null if none.
     */
    public static <M extends Member> M findByLogin(final String login) {
        M member = null;
        try {
            member = (M) em().createNamedQuery(QUERY_BYLOGIN).setParameter("login", login).getSingleResult();
        } catch (NoResultException ex) {
            member = null;
        }
        return member;
    }



    /**
     * Register user a new Entraides user
     */
    public Member register() {
        save();
        return this;
    }

    /**
     * Update user profile
     */
    public Member updateProfile() {
        save();
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Member other = (Member) obj;
        return new EqualsBuilder().append(this.login, other.login).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.login).toHashCode();
    }

    /**
     * Display member. WARNING : used on UI as main display of user.
     * @return 
     */
    @Override
    public String toString() {
        return displayName;
    }

}