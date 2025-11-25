package com.ecom.Ecomerce;

import java.util.Scanner;

import dao.CartDAO;
import dao.CategoryDAO;
import dao.ProductDAO;
import dao.UserDAO;
import entity.Category;
import entity.Product;
import entity.User;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Press 1 for user registration..");
			System.out.println("Press 2 for user login");
			System.out.println("Press 3 for saving product");
			System.out.println("Press 4 for saving category");
			System.out.println("Press 5 for add product into cart");
			System.out.println("Press 0 for exit");
			System.out.println("Enter your choice");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				User user = new User();

				System.out.println("Enter user name ");
				user.setName(sc.next());
				System.out.println("Enter email id ");
				user.setEmail(sc.next());
				System.out.println("Enter Password ");
				user.setPassword(sc.next());
				System.out.println("Enter Adhar no. ");
				user.setAdharNo(sc.next());

				boolean status = UserDAO.save(user);
				if (status)
					System.out.println(" User Registration Successfully....");
				else
					System.out.println("Somthing went wrong");
				break;

			case 2:
				System.out.println("Enter email id");
				String userEmail = sc.next();
				System.out.println("Enter password");
				String userPassword = sc.next();
				User u = new User();
				u.setEmail(userEmail);
				u.setPassword(userPassword);
				if (UserDAO.authenticate(u))
					System.out.println("Login success...");
				else
					System.out.println("Oops! something went wrong...");
				break;

			case 3:
				System.out.println("Enter product title");
				String title = sc.next();
				System.out.println("Enter product price");
				int price = sc.nextInt();
				System.out.println("Enter category Id");
				int categoryId = sc.nextInt();
				Category category = CategoryDAO.findById(categoryId);
				if (category != null) {
					Product p = new Product();
					p.setTitle(title);
					p.setCategory(category);
					p.setPrice(price);
					if (ProductDAO.save(p))
						System.out.println("Product saved...");
					else
						System.out.println("something went wrong.....");
				} else
					System.out.println("Category not Found...");
				break;
			case 4:
				System.out.println("Enter Category name...");
				Category c = new Category();
				c.setCategoryName(sc.next());
				if (CategoryDAO.save(c))
					System.out.println("Category saved....");
				else
					System.out.println("Something went wrong");
				break;
			case 5:
				System.out.println("Enter user id");
				int userId = sc.nextInt();
				System.out.println("Enter product id");
				int productId = sc.nextInt();

				String message = CartDAO.addToCart(userId, productId);
				System.out.println(message);
				break;
			case 0:
				System.exit(0);

			}
		}
	}
}
