package com.blog.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.dto.BlogRequestDTO;
import com.blog.dto.UserDTO;
import com.blog.entity.Blog;
import com.blog.exception.ResourceNotFoundException;
import com.blog.externalservice.UserClient;
import com.blog.repo.BlogRepository;

@Service
public class BlogService {

	private final BlogRepository blogRepo;
	private final UserClient userClient;
	
	
	public BlogService(BlogRepository blogRepo,UserClient userClient) {
	this.blogRepo  = blogRepo;
	this.userClient = userClient;
	}
	
	
	public Blog write(Blog blog) {
		UserDTO user = userClient.getUserById(blog.getUserId());
		if(user== null) {
			throw new ResourceNotFoundException("user does not exists");
		}
		System.out.println("user feched "+ user.getUsername());
		return blogRepo.save(blog);
	}
	
	public Blog update(BlogRequestDTO blog) {
		Blog existingblog = blogRepo.findById(blog.getId()).orElseThrow(() -> new ResourceNotFoundException("blog is not Existed  you cant update"));
		existingblog.setBlog(blog.getBlog());
		return blogRepo.save(existingblog);
	}
	public Blog getBlogById(int id) {
		Blog blog = blogRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("blog is not Existed  you cant update"));
		return blog;
	}
	
	public List<Blog> getAllBlogs() {
	   return blogRepo.findAll();
	   
	  
	}
	
}
