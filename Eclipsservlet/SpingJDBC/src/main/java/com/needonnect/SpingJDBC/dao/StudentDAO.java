package com.needonnect.SpingJDBC.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.needonnect.SpingJDBC.entity.Student;

public class StudentDAO {
	@Autowired
	  JdbcTemplate template;
	  
	  public boolean save(Student s) {
		  String sql = "insert into Students(name,course) values(?,?)";
		  return template.update(sql,s.getName(),s.getCourse()) > 0;
	  }
	  
	  public boolean update(Student s) {
		  String sql = "update Students set name=? , course = ? where id = ?";
		  return template.update(sql,s.getName(),s.getCourse(),s.getId()) > 0;
	  }
	  public boolean delete(int id) {
		  String sql = "delete from Students where id = ?";
	      return template.update(sql,id) > 0;
	  }
}
