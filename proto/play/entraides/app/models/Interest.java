package models;

import javax.persistence.Entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import play.db.jpa.Model;

/**
 * Member Interest
 * @author agnes.crepet@gmail.com
 */
@Entity
public class Interest extends Model implements Comparable<Interest> {

    public String name;
    
    private Interest(String name) {
        this.name = name;
    }

    /**
     * Because want something like lazy interest creation
     * we will always get them using the findOrCreateByName(String name) factory method.
     * @param name
     * @return Interest
     */
    public static Interest findOrCreateByName(String name) {
        Interest interest = Interest.find("byName", name).first();
        if (interest == null) {
            interest = new Interest(name);
        }
        return interest;
    }   

    public int compareTo(Interest otherInterest) {
        return name.compareTo(otherInterest.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Interest other = (Interest) obj;
        return new EqualsBuilder().append(this.name, other.name).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.name).toHashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
