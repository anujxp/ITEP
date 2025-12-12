package com.info.springjpa;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.info.springjpa.config.JavaConfig;
import com.info.springjpa.entity.User;
import com.info.springjpa.service.UserService;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);

		UserService service = context.getBean(UserService.class);
		while (true) {
			System.out.println("press 1 for insert");
			System.out.println("press 2 for update");
			System.out.println("press 3 for delete ");
			System.out.println("press 4 for retrive");
			int choice = sc.nextInt();
			if (choice == 1) {
				service.saveUser();
			}
			else if(choice == 2 )
				service.update();
			else if(choice == 3)
				service.delete();
			else if (choice == 4)
				service.retrieve();
			else if (choice ==0) {
			}
				
		}

	}
}
