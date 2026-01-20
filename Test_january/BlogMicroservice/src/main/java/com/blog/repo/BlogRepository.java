package com.blog.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>{
	List<Blog> findByUserId(int id);
}
