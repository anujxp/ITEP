package com.needconnect.dao;

import java.util.List;
import com.needconnect.entity.Booking;
import com.needconnect.util.Util;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class BookingDAO {

	/**
	 * 1. Create a new Booking
	 */
	public boolean saveBooking(Booking booking) {
		EntityTransaction transaction = null;
		try (EntityManager manager = Util.getEntityManager()) {
			transaction = manager.getTransaction();
			transaction.begin();

			manager.persist(booking);

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
	 * 2. Get all bookings for a specific Student (User) Used for "My Bookings"
	 * page.
	 */
	public List<Booking> getBookingsByUser(int userId) {
		try (EntityManager manager = Util.getEntityManager()) {
			// Fetch bookings where b.user.id matches the logged-in user
			String jpql = "SELECT b FROM Booking b WHERE b.user.id = :uid ORDER BY b.id DESC";
			TypedQuery<Booking> query = manager.createQuery(jpql, Booking.class);
			query.setParameter("uid", userId);

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 3. Get all bookings for a specific Partner Used for the Partner Dashboard to
	 * see incoming orders.
	 */
	public List<Booking> getBookingsByPartner(int partnerId) {
		try (EntityManager manager = Util.getEntityManager()) {
			// Notice the path: Booking -> Listing -> Partner
			String jpql = "SELECT b FROM Booking b WHERE b.listing.partner.id = :pid ORDER BY b.id DESC";
			TypedQuery<Booking> query = manager.createQuery(jpql, Booking.class);
			query.setParameter("pid", partnerId);

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}