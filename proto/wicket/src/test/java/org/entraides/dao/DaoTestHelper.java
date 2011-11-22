package org.entraides.dao;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class DaoTestHelper {

    protected Injector injector;

    protected EntityManager em;

    public DaoTestHelper(Module... modules) {

        injector = Guice.createInjector(new JpaPersistModule("dev"));
        injector.getInstance(PersistService.class).start();
        em = injector.getInstance(EntityManager.class);
    }

    public void TX_COMMIT() {
        em.getTransaction().commit();
    }

    public void TX_BEGIN() {
        TX_ROLLBACK();
        em.getTransaction().begin();
    }

    public void TX_ROLLBACK() {
        final EntityTransaction transaction = em.getTransaction();
        if (transaction.isActive())
            transaction.rollback();
    }

    public void createEntity(Object o) {
        TX_BEGIN();
        em.persist(o);
        TX_COMMIT();
    }

    public void modify(Object o) {
        TX_BEGIN();
        em.merge(o);
        TX_COMMIT();
    }

    public void remove(Object o) {
        TX_BEGIN();
        em.remove(o);
        TX_COMMIT();
    }
}
