package dao;

import java.util.List;

import com.info.todo.entity.Admin;
import com.info.todo.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class AdminDAO {
	public static Admin authenticate(Admin admin) {
		EntityManagerFactory factory = JPAUtil.getFactory();
		try (EntityManager manager = factory.createEntityManager();) {
			TypedQuery<Admin> query = manager.createQuery("from Admin where email =:email and password =:password",
					Admin.class);
			query.setParameter("email", admin.getEmail());
			query.setParameter("password", admin.getPassword());
			List<Admin> list = query.getResultList();
			System.out.println("called admin dao...");
			if (list.size() == 0) {
				return null;

			}
			return list.get(0);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occured");
			throw new RuntimeException(e.getMessage());
		}
	}
}
