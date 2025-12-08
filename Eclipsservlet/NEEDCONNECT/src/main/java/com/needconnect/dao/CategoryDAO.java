package com.needconnect.dao;

import java.util.List;
import com.needconnect.entity.Category;
import com.needconnect.util.Util;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class CategoryDAO {

    /**
     * 1. Save a new Category (Admin use)
     */
    public boolean saveCategory(Category category) {
        EntityTransaction transaction = null;
        try (EntityManager manager = Util.getEntityManager()) {
            transaction = manager.getTransaction();
            transaction.begin();
            manager.persist(category);
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
     * 2. Get All Categories (For the "Post Room" Dropdown)
     */
    public List<Category> getAllCategories() {
        try (EntityManager manager = Util.getEntityManager()) {
            TypedQuery<Category> query = manager.createQuery("SELECT c FROM Category c", Category.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 3. Get Category by ID (Used when saving a Listing)
     */
    public Category getCategoryById(int id) {
        try (EntityManager manager = Util.getEntityManager()) {
            return manager.find(Category.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}