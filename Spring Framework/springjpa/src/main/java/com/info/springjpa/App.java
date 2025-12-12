package com.info.springjpa;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.info.springjpa.config.JavaConfig;
import com.info.springjpa.entity.User;
import com.info.springjpas.service.UserService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {	Scanner sc = new Scanner(System.in);
    	ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        UserService service =  context.getBean(UserService.class);
//        while(true) {
//        	System.out.println("press 1 for insert");
//        	System.out.println("press 2 for update");
//        }
        User user = new User();
        user.setEmail("abc@gmail.com");
        user.setPassword("1234");
        user.setName("abc");
        service.saveUser(user);
    	
    }
}
