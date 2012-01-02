package de.bht.se2.ideafactory.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class PersistenceManager {
    private static final PersistenceManager instance = new PersistenceManager();

    private PersistenceManager() {

    }

    public static PersistenceManager getInstance() {
        return instance;
    }

    public EntityManagerFactory createEntityManagerFactory() {
        return Persistence
                .createEntityManagerFactory("ideafactoryPersistenceUnit2");
    }
}