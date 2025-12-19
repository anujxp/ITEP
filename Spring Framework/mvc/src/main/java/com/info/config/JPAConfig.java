package com.info.config;

import java.sql.DriverManager;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableTransactionManagement
@Configuration
public class JPAConfig {
	@Bean
	public DriverManagerDataSource getDatasource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/mvc");
		datasource.setUsername("root");
		datasource.setPassword("root");
		return datasource;
	}
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean getFactory(DriverManagerDataSource datasource ) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(datasource);
		factory.setPackagesToScan("com.info.entity");
		
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		
		Properties prop = new Properties();
		prop.put("hibernate.hbm2ddl.auto", "update");
		prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		prop.put("hibernate.format", true);
		prop.put("show_sql", true);
		factory.setJpaProperties(prop);
		factory.setJpaVendorAdapter(adapter);
		return factory;
		
	}
	
	@Bean(name = "transactionManager")
	public JpaTransactionManager getTransaction(LocalContainerEntityManagerFactoryBean factory) {
		JpaTransactionManager transaction = new JpaTransactionManager();
		transaction.setEntityManagerFactory(factory.getObject());
		return transaction;
		
	}
}
