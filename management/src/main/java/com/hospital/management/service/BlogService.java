package com.hospital.management.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hospital.management.entity.Blogs;

public interface BlogService {
	
	List<Blogs> getAllBlogs();
	
	Blogs addBlog(MultipartFile image,Blogs blog);
	
	void deleteBlog(long id);
	
	Blogs getById(long id);
	
	Blogs updateBlog(Blogs blog);
	
	List<Blogs> getBlogsByAuthor(String name);
}
