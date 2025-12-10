package com.needconnect.dao;

import java.util.List;
import com.needconnect.entity.Admin;
import com.needconnect.util.Util;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class AdminDAO {

    public Admin loginAdmin(String email, String password) {
        try (EntityManager manager = Util.getEntityManager()) {
            String jpql = "SELECT a FROM Admin a WHERE a.email = :email AND a.password = :password";
            TypedQuery<Admin> query = manager.createQuery(jpql, Admin.class);
            query.setParameter("email", email);
            query.setParameter("password", password);

            List<Admin> list = query.getResultList();
            if (list.isEmpty()) return null;
            return list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}