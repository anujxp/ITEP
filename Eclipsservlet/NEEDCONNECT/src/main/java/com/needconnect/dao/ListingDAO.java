package com.needconnect.dao;

import java.util.List;
import com.needconnect.entity.Listing;
import com.needconnect.util.Util;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class ListingDAO {

    /**
     * 1. Add a new Listing
     */
    public boolean saveListing(Listing listing) {
        EntityTransaction transaction = null;
        try (EntityManager manager = Util.getEntityManager()) {
            transaction = manager.getTransaction();
            transaction.begin();
            
            // Ensure adminStatus is set if not already
            if(listing.getAdminStatus() == null) {
                listing.setAdminStatus("Pending");
            }
            
            manager.persist(listing);
            
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
     * 2. Get All APPROVED Listings (For Student/Home View)
     */
    public List<Listing> getAllListings() {
        try (EntityManager manager = Util.getEntityManager()) {
            // ONLY show 'Approved' listings
            String jpql = "SELECT l FROM Listing l WHERE l.adminStatus = 'Approved' ORDER BY l.id DESC";
            TypedQuery<Listing> query = manager.createQuery(jpql, Listing.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 3. Get Listings by Category (For Filtering)
     */
    public List<Listing> getListingsByCategory(int categoryId) {
        try (EntityManager manager = Util.getEntityManager()) {
            String jpql = "SELECT l FROM Listing l WHERE l.category.id = :catId AND l.adminStatus = 'Approved'";
            TypedQuery<Listing> query = manager.createQuery(jpql, Listing.class);
            query.setParameter("catId", categoryId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 4. Get Listing by ID (For View Details page)
     */
    public Listing getListingById(int id) {
        try (EntityManager manager = Util.getEntityManager()) {
            return manager.find(Listing.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 5. SEARCH Listings (City + Category + Approved Status)
     */
    public List<Listing> searchListings(String city, String categoryId) {
        try (EntityManager manager = Util.getEntityManager()) {
            
            // Start with Approved condition
            String jpql = "SELECT l FROM Listing l WHERE l.adminStatus = 'Approved'";
            
            // Append other conditions
            if (city != null && !city.trim().isEmpty()) {
                jpql += " AND lower(l.city) LIKE lower(:city)";
            }
            if (categoryId != null && !categoryId.trim().isEmpty() && !categoryId.equals("all")) {
                jpql += " AND l.category.id = :catId";
            }
            
            jpql += " ORDER BY l.id DESC";

            TypedQuery<Listing> query = manager.createQuery(jpql, Listing.class);

            // Set Parameters
            if (city != null && !city.trim().isEmpty()) {
                query.setParameter("city", "%" + city + "%");
            }
            if (categoryId != null && !categoryId.trim().isEmpty() && !categoryId.equals("all")) {
                query.setParameter("catId", Integer.parseInt(categoryId));
            }

            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 6. Get Listings by Partner ID (For Partner Dashboard)
     */
    public List<Listing> getListingsByPartner(int partnerId) {
        try (EntityManager manager = Util.getEntityManager()) {
            String jpql = "SELECT l FROM Listing l WHERE l.partner.id = :pid ORDER BY l.id DESC";
            TypedQuery<Listing> query = manager.createQuery(jpql, Listing.class);
            query.setParameter("pid", partnerId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 7. Delete a Listing
     */
    public boolean deleteListing(int id) {
        EntityTransaction transaction = null;
        try (EntityManager manager = Util.getEntityManager()) {
            transaction = manager.getTransaction();
            transaction.begin();
            
            Listing listing = manager.find(Listing.class, id);
            if (listing != null) {
                manager.remove(listing);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 8. Get Listings by Admin Status (For Admin Dashboard)
     * status = "Pending" (To Approve) or "Approved" (To View)
     */
    public List<Listing> getListingsByAdminStatus(String status) {
        try (EntityManager manager = Util.getEntityManager()) {
            String jpql = "SELECT l FROM Listing l WHERE l.adminStatus = :status ORDER BY l.id DESC";
            TypedQuery<Listing> query = manager.createQuery(jpql, Listing.class);
            query.setParameter("status", status);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<String> getAllCities() {
        try (EntityManager manager = Util.getEntityManager()) {
            // Select distinct city names from Approved listings only
            String jpql = "SELECT DISTINCT l.city FROM Listing l WHERE l.adminStatus = 'Approved' ORDER BY l.city";
            TypedQuery<String> query = manager.createQuery(jpql, String.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
}