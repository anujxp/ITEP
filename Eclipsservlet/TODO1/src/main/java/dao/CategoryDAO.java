package dao;

import java.util.List;

import javax.management.RuntimeErrorException;

import com.info.todo.entity.Category;
import com.info.todo.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class CategoryDAO {

	public static List<Category> getList() {
		EntityManagerFactory factory = JPAUtil.getFactory();
		try (EntityManager manager = factory.createEntityManager()) {
			TypedQuery<Category> query = manager.createQuery("from Cateogry", Category.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public static boolean save(Category category) {
		EntityManagerFactory factory = JPAUtil.getFactory();
		EntityTransaction transaction = null;
		try (EntityManager manager = factory.createEntityManager();) {
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(category);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null && transaction.isActive())
				transaction.rollback();
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}
}
