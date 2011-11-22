package org.entraides.dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import org.entraides.domain.User;

import javax.persistence.EntityManager;

/**
 */
public class UserDao {

    @Inject
    Provider<EntityManager> em;

    @Transactional
    public void create(User user){
        em.get().persist(user);
    }
    @Transactional
    public void modify(User user){
        em.get().merge(user);
    }
    @Transactional
    public void remove(User user){
        em.get().remove(user);
    }
}
