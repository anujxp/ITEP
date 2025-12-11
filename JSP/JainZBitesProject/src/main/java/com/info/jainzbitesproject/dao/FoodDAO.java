package com.info.jainzbitesproject.dao;


import java.util.List;

import com.info.jainzbitesproject.entity.Food;
import com.info.jainzbitesproject.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class FoodDAO {

 
    public static boolean save(Food food) {
        EntityManagerFactory factory = JPAUtil.getFactory();

        try (EntityManager manager = factory.createEntityManager()) {

            manager.getTransaction().begin();
            manager.persist(food);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

  
    public static List<Food> getAll() {
        EntityManagerFactory factory = JPAUtil.getFactory();

        try (EntityManager manager = factory.createEntityManager()) {

            TypedQuery<Food> query =
                    manager.createQuery("FROM Food", Food.class);

            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
    
    
    public static boolean update(Food food) {
        EntityManagerFactory factory = JPAUtil.getFactory();

        try (EntityManager manager = factory.createEntityManager()) {

            manager.getTransaction().begin();
            manager.merge(food);   // merge = update
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(int id) {
        EntityManagerFactory factory = JPAUtil.getFactory();

        try (EntityManager manager = factory.createEntityManager()) {

            Food f = manager.find(Food.class, id);

            if (f == null)
                return false;

            manager.getTransaction().begin();
            manager.remove(f);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public static Food getById(int id) {
        EntityManagerFactory factory = JPAUtil.getFactory();

        try (EntityManager manager = factory.createEntityManager()) {

            return manager.find(Food.class, id);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
