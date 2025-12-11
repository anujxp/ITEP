package com.info.jainzbitesproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.info.jainzbitesproject.entity.Cart;
import com.info.jainzbitesproject.util.DBUtil;
import com.info.jainzbitesproject.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class CartDAO {

   
    public static boolean add(Cart cart) {
        EntityManagerFactory factory = JPAUtil.getFactory();

        try (EntityManager manager = factory.createEntityManager()) {

            manager.getTransaction().begin();
            manager.persist(cart);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

  
    public static List<Cart> getByCustomer(int customerId) {
        EntityManagerFactory factory = JPAUtil.getFactory();

        try (EntityManager manager = factory.createEntityManager()) {

            TypedQuery<Cart> query = manager.createQuery(
                    "FROM Cart c WHERE c.customer.id=:cid",
                    Cart.class);

            query.setParameter("cid", customerId);

            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


    public static boolean delete(int cartId) {
        EntityManagerFactory factory = JPAUtil.getFactory();

        try (EntityManager manager = factory.createEntityManager()) {

            Cart c = manager.find(Cart.class, cartId);

            if (c == null)
                return false;

            manager.getTransaction().begin();
            manager.remove(c);
            manager.getTransaction().commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
        
        
        
        public static void addToCart(int customerId, int productId, int quantity) {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                con = DBUtil.getConnection();

                // Step 1: Check if product already exists in this customer's cart
                String checkQuery = "SELECT quantity FROM cart WHERE customer_id = ? AND product_id = ?";
                ps = con.prepareStatement(checkQuery);
                ps.setInt(1, customerId);
                ps.setInt(2, productId);
                rs = ps.executeQuery();

                if (rs.next()) {
                    // Already present → update quantity
                    int existingQty = rs.getInt("quantity");
                    int newQty = existingQty + quantity;

                    String updateQuery = "UPDATE cart SET quantity = ? WHERE customer_id = ? AND product_id = ?";
                    ps = con.prepareStatement(updateQuery);
                    ps.setInt(1, newQty);
                    ps.setInt(2, customerId);
                    ps.setInt(3, productId);

                    ps.executeUpdate();
                } else {
                    // Not present → insert new row
                    String insertQuery = "INSERT INTO cart(customer_id, product_id, quantity) VALUES(?,?,?)";
                    ps = con.prepareStatement(insertQuery);
                    ps.setInt(1, customerId);
                    ps.setInt(2, productId);
                    ps.setInt(3, quantity);

                    ps.executeUpdate();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(con, ps, rs);
            }
        }

    
}

