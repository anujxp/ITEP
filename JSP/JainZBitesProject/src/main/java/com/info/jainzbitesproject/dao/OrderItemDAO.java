package com.info.jainzbitesproject.dao;


import java.util.List;

import com.info.jainzbitesproject.entity.OrderItem;
import com.info.jainzbitesproject.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class OrderItemDAO {

   
    public static boolean save(OrderItem item) {
        EntityManagerFactory factory = JPAUtil.getFactory();

        try (EntityManager manager = factory.createEntityManager()) {

            manager.getTransaction().begin();
            manager.persist(item);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    // Get all order items
    public static List<OrderItem> getAll() {
        EntityManagerFactory factory = JPAUtil.getFactory();

        try (EntityManager manager = factory.createEntityManager()) {

            TypedQuery<OrderItem> query =
                    manager.createQuery("FROM OrderItem", OrderItem.class);

            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

  
    public static OrderItem getById(int id) {
        EntityManagerFactory factory = JPAUtil.getFactory();

        try (EntityManager manager = factory.createEntityManager()) {
            return manager.find(OrderItem.class, id);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}


