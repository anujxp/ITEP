package com.info.spring.welcomespring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.info.welcomespring.entity.Employee;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
       Employee emp1 = (Employee)context.getBean("emp");
       System.out.println(emp1.getDepartment());
    }
}
