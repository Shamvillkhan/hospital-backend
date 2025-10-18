package com.hospital.management.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.management.entity.Blogs;
import com.hospital.management.repository.BlogRepository;
import com.hospital.management.service.BlogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/blogs")
public class BlogController {

	private BlogService blogService;
	private BlogRepository blogRepo;

	public BlogController(BlogService blogService,BlogRepository blogRepo) {
		this.blogService = blogService;
		this.blogRepo=blogRepo;
	}
	
	
	@GetMapping("/count")
    public long countBlogs() {
    	
        return blogRepo.count();
    }
	
	
	@GetMapping("/gett/{id}")
	public ResponseEntity<?> getBlogById(@PathVariable Long id) {
	    try {
	        Blogs blog = blogService.getById(id); 
	        return ResponseEntity.ok(blog);
	    } catch (RuntimeException e) {
	        
	        return ResponseEntity.status(404).body("Blog not found with id: " + id);
	    }
	}


	@GetMapping("/getAll")
	public ResponseEntity<List<Blogs>> getAllBlogs() {

		return ResponseEntity.ok(blogService.getAllBlogs());
	}

	@PostMapping("/add")
	public ResponseEntity<?> createBlog(@Valid @RequestPart("blog") String blogJson,
			@RequestPart(value = "image", required = false) MultipartFile image) {

		try {

			ObjectMapper objectMapper = new ObjectMapper();
			Blogs blog = objectMapper.readValue(blogJson, Blogs.class);

			Blogs savedBlog = blogService.addBlog(image, blog);

			return ResponseEntity.status(HttpStatus.CREATED).body(savedBlog);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
	}
	@PutMapping("/update")
	public ResponseEntity<Blogs> updateBlog(@RequestBody Blogs blog) {
	    Blogs updatedBlog = blogService.updateBlog(blog);
	    return ResponseEntity.ok(updatedBlog);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteBlog(@PathVariable Long id) {
		blogService.deleteBlog(id);
		return ResponseEntity.ok("Blog deleted successfully with id: " + id);
	}

	@GetMapping("/get/{name}")
	public ResponseEntity<List<Blogs>> getBlogByAuthor(@PathVariable String name) {
		List<Blogs> blogs = blogService.getBlogsByAuthor(name);

		if (blogs.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(blogs);
	}

}
