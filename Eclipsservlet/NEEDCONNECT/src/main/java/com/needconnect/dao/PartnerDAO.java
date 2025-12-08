package com.needconnect.dao;

import java.util.List;
import com.needconnect.entity.Partner;
import com.needconnect.util.Util;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class PartnerDAO {

    /**
     * Register a new Partner
     */
    public boolean savePartner(Partner partner) {
        EntityTransaction transaction = null;
        try (EntityManager manager = Util.getEntityManager()) {
            transaction = manager.getTransaction();
            transaction.begin();
            
            manager.persist(partner);
            
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Login a Partner
     */
    public Partner loginPartner(String email, String password) {
        try (EntityManager manager = Util.getEntityManager()) {
            
            String jpql = "SELECT p FROM Partner p WHERE p.email = :email AND p.password = :password";
            TypedQuery<Partner> query = manager.createQuery(jpql, Partner.class);
            query.setParameter("email", email);
            query.setParameter("password", password);

            List<Partner> list = query.getResultList();
            
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}