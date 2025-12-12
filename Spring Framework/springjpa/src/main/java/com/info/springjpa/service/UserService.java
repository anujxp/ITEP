package com.info.springjpa.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.springjpa.entity.User;
import com.info.springjpa.repo.UserRepositry;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	UserRepositry userRepo;

	@Transactional
	public User saveUser() {
		Scanner se = new Scanner(System.in);
		User user = new User();
		System.out.println("Enter user name ");
		user.setName(se.next());
		System.out.println("Enter user email");
		user.setEmail(se.next());
		System.out.println("Enter user password ");
		user.setPassword(se.next());
		return userRepo.save(user);
	}

	public java.util.List<User> getUserList() {
		return userRepo.findAll();
	}

	public boolean update() {
		System.out.println("Enter UserId");
		Scanner se = new Scanner(System.in);
		int id = se.nextInt();
		Optional<User> user = userRepo.findById(id);
		User user1 = user.get();
		System.out.println("Enter name ");
		user1.setName(se.next());
		System.out.println("enter emai...");
		user1.setEmail(se.next());
		System.out.println("Enter pssword.....");

		user1.setPassword(se.next());
		User u = userRepo.save(user1);
		if (u != null)
			return true;
		return false;
	}

	public void retrieve() {
		List<User> list = userRepo.findAll();
		for (User user : list) {
			System.out.println(user.getId() + " " + user.getName() + " " + user.getEmail());
		}
	}

	public void delete() {
		Scanner se = new Scanner(System.in);
		System.out.println("enter user id ");
		int id = se.nextInt();
		if(userRepo.findById(id) != null)
			System.out.println("deleted successfully");
					else
						System.out.println("not deleted .....");
				
		userRepo.deleteById(id);
		if(userRepo.findById(id) != null)
System.out.println("deleted successfully");
		else
			System.out.println("not deleted .....");
	}
}
