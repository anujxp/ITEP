package com.needconnect.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Util {
	private static final String PERSISTENCE_UNIT_NAME = "need_connect_unit"; 
    private static EntityManagerFactory factory;

    static {
        try {
            // Create the factory once when the class is loaded
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static EntityManager getEntityManager() {
        if (factory == null) {
            throw new IllegalStateException("EntityManagerFactory is not initialized.");
        }
        return factory.createEntityManager();
    }
}
