package models;

import java.util.Set;
import java.util.TreeSet;
import javax.persistence.*;

import org.apache.commons.lang.StringUtils;
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
    @NamedQuery(name = Member.QUERY_BYLOGIN, query = "from Member m where m.login=:login"),})
public class Member extends Model {

    static final String QUERY_BYLOGIN = "MemberByLogin";
    /** Internal login : functional key */
    @Column(nullable = false, unique = true, updatable = false)
    @IndexColumn(name = "login_UK_IDX", nullable = false)
    @Required
    public String login;
    @Required
    public String email;
    @Required
    public String password;
    public String localisation;
    @Lob
    public String bio;
    @ManyToMany(cascade = CascadeType.PERSIST)
    public Set<Interest> interests = new TreeSet<Interest>();

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

    public Member addInterest(String interest) {
        if (StringUtils.isNotBlank(interest)) {
            interests.add(Interest.findOrCreateByName(interest));
        }
        return this;
    }

    public Member addInterests(String... interests) {
        for (String interet : interests) {
            addInterest(interet);
        }
        return this;
    }

    public Member updateInterests(String... interests) {
        this.interests.clear();
        addInterests(interests);
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
        return login;
    }
}