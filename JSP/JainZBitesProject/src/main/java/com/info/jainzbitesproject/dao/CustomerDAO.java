package com.info.jainzbitesproject.dao;



import java.util.List;

import com.info.jainzbitesproject.entity.Customer;
import com.info.jainzbitesproject.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class CustomerDAO {

	public static Customer authenticate(Customer customer) {
		EntityManagerFactory factory=JPAUtil.getFactory();
		try (EntityManager manager=factory.createEntityManager();){
			TypedQuery<Customer> query=manager.createQuery("from Customer where email=:email and password=:password", Customer.class);
			query.setParameter("email",customer.getEmail());
			query.setParameter("password",customer.getPassword());
			List<Customer> list=query.getResultList();
			if(list.size()==0)
			return null;
			customer=list.get(0);
			return customer;
				
			
		} catch (Exception e) {
           e.printStackTrace();
           throw new RuntimeException(e.getMessage());
		}
	}
	
	
	
	 public Customer getCustomerByEmail(String email) {

	        EntityManagerFactory factory = JPAUtil.getFactory();

	        try (EntityManager manager = factory.createEntityManager()) {

	            TypedQuery<Customer> query= manager.createQuery("from Customer where email=:email", Customer.class);

	            query.setParameter("email", email);

	            List<Customer> list = query.getResultList();

	            if (list.isEmpty())
	                return null;

	            return list.get(0);

	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        }
	    }

	
	 public boolean register(Customer customer) {

	        EntityManagerFactory factory = JPAUtil.getFactory();

	        try (EntityManager manager = factory.createEntityManager()) {

	            manager.getTransaction().begin();
	            manager.persist(customer);
	            manager.getTransaction().commit();
	            return true;

	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        }
	    }
	 
	 public static List<Customer> getAll() {
	        EntityManagerFactory factory = JPAUtil.getFactory();

	        try (EntityManager manager = factory.createEntityManager()) {

	            TypedQuery<Customer> query =
	                    manager.createQuery("FROM Customer", Customer.class);

	            return query.getResultList();

	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e.getMessage());
	        }
	    }

	 
	 public static boolean isEmailExists(String email) {

		    EntityManagerFactory factory = JPAUtil.getFactory();

		    try (EntityManager manager = factory.createEntityManager()) {

		        TypedQuery<Long> query =
		            manager.createQuery("select count(c) from Customer c where email=:email", Long.class);

		        query.setParameter("email", email);

		        Long count = query.getSingleResult();

		        return count > 0;

		    } catch (Exception e) {
		        e.printStackTrace();
		        throw new RuntimeException(e.getMessage());
		    }
		}



}

