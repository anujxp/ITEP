package com.info.javaconfig.javaconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.info.javaconfig.javaconfig.entity.Employee;


public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        
        Employee employee = (Employee)context.getBean("getEmployee");
        System.out.println(employee.getId());
        System.out.println(employee.getName());
        System.out.println(employee.getDepartment());
        
        
    }
}
