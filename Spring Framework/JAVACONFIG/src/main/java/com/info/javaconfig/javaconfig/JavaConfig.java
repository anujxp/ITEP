package com.info.javaconfig.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.info.javaconfig.javaconfig.entity.Employee;

@Configuration
public class JavaConfig {
	@Bean
	public Employee getEmployee() {
		Employee emp = new Employee();
		
		emp.setId(100);
		emp.setDepartment("java Team");
		emp.setName("Laksh");
		return emp;
		
	}
}
