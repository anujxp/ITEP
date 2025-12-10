package com.info.springjdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.info.springjdbc.dao.StudentDAO;

@Configuration
public class JavaConfig {
	@Bean
	public DriverManagerDataSource getDataSource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/itep_springjdbc");
		datasource.setUsername("root");
		datasource.setPassword("root");
		return datasource;
		
	}
	
	@Bean
	public JdbcTemplate getTemplate() {
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(getDataSource());
		return template;
		
		
	}
	@Bean
	public StudentDAO getStudentDAO() {
		return new StudentDAO();
	}
}
