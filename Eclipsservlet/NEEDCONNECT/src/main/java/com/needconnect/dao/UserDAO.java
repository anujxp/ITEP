package com.needconnect.dao;

import java.util.List;

import com.needconnect.entity.User;
import com.needconnect.util.Util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.TypedQuery;

public class UserDAO {
	public static boolean saveUser(User user) {
		EntityTransaction transaction = null;
		try (EntityManager manager = Util.getEntityManager();) {
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(user);
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
	
	public User loginUser(String email, String password) {
        // No transaction needed for read-only, but we still use try-with-resources for safety
        try (EntityManager manager = Util.getEntityManager()) {
            
            String jpql = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password";
            TypedQuery<User> query = manager.createQuery(jpql, User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);

            List<User> list = query.getResultList();
            
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	public boolean checkEmailExists(String email) {
        try (EntityManager manager = Util.getEntityManager()) {
            
            String jpql = "SELECT COUNT(u) FROM User u WHERE u.email = :email";
            TypedQuery<Long> query = manager.createQuery(jpql, Long.class);
            query.setParameter("email", email);

            Long count = query.getSingleResult();
            
            return count > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
