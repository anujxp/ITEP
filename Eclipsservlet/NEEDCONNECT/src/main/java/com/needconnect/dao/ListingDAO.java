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

			// JPA will automatically save the foreign keys for Partner and Category
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
	 * 2. Get All Listings (Newest First)
	 */
	public List<Listing> getAllListings() {
		try (EntityManager manager = Util.getEntityManager()) {
			TypedQuery<Listing> query = manager.createQuery("SELECT l FROM Listing l ORDER BY l.id DESC",
					Listing.class);
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
			TypedQuery<Listing> query = manager.createQuery("SELECT l FROM Listing l WHERE l.category.id = :catId",
					Listing.class);
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

	// Add this inside ListingDAO.java

	public List<Listing> searchListings(String city, String categoryId) {
		try (EntityManager manager = Util.getEntityManager()) {

			// Base Query
			String jpql = "SELECT l FROM Listing l WHERE 1=1";

			// Dynamically add conditions
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
}