package com.info.jainzbitesproject.dao;

import java.util.List;

import com.info.jainzbitesproject.entity.Order;
import com.info.jainzbitesproject.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class OrderDAO {


    public static boolean placeOrder(Order order) {
        EntityManagerFactory factory = JPAUtil.getFactory();

        try (EntityManager manager = factory.createEntityManager()) {

            manager.getTransaction().begin();
            manager.persist(order);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static Order getById(int cid) {
        EntityManagerFactory factory = JPAUtil.getFactory();

        try (EntityManager manager = factory.createEntityManager()) {

            TypedQuery<Order> query =
                    manager.createQuery("FROM Order WHERE id =:id", Order.class);
            query.setParameter("id",cid );

            return (Order)query.getResultList().get(0);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
    public static List<Order> getAll() {
        EntityManagerFactory factory = JPAUtil.getFactory();

        try (EntityManager manager = factory.createEntityManager()) {

            TypedQuery<Order> query = manager.createQuery("FROM Order", Order.class);

            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
 
 
    public static List<Order> getByCustomer(int customerId) {
        EntityManagerFactory factory = JPAUtil.getFactory();

        try (EntityManager manager = factory.createEntityManager()) {

            TypedQuery<Order> query = manager.createQuery(
                    "FROM Order o WHERE o.customer.id=:cid",
                    Order.class);

            query.setParameter("cid", customerId);

            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
