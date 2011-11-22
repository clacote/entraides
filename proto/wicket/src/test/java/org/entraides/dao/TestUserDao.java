package org.entraides.dao;

import org.entraides.domain.User;
import org.junit.Test;

/**
 */
public class TestUserDao extends DaoTestHelper{

    @Test
    public void createUser(){
        User user = new User();
        user.setLogin("login");
        user.setEmail("mail@mail.com");

        createEntity(user);
    }
}
