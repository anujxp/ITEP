package com.info.jainzbitesproject.dao;



import java.util.List;

import com.info.jainzbitesproject.entity.Category;
import com.info.jainzbitesproject.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class CategoryDAO {

  
    public static boolean save(Category category) {
        EntityManagerFactory factory = JPAUtil.getFactory();

        try (EntityManager manager = factory.createEntityManager()) {

            manager.getTransaction().begin();
            manager.persist(category);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

  
    public static List<Category> getAll() {
        EntityManagerFactory factory = JPAUtil.getFactory();

        try (EntityManager manager = factory.createEntityManager()) {

            TypedQuery<Category> query =
                    manager.createQuery("FROM Category", Category.class);

            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

   
    public static Category getById(int id) {
        EntityManagerFactory factory = JPAUtil.getFactory();

        try (EntityManager manager = factory.createEntityManager()) {

            return manager.find(Category.class, id);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}

