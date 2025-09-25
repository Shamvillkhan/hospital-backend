package com.hospital.management.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hospital.management.entity.Blogs;
import com.hospital.management.fileutility.FileStorageService;
import com.hospital.management.repository.BlogRepository;

@Service
public class BlogServiceImpl implements BlogService {
	
	private BlogRepository blogRepository;
	
	
	public BlogServiceImpl(BlogRepository blogRepository) {
		this.blogRepository=blogRepository;
	}

	@Override
	public List<Blogs> getAllBlogs() {
		return blogRepository.findAll();
	}


	@Override
	public List<Blogs> getBlogsByAuthor(String authorName) {
	    return blogRepository.findByAuthorContainingIgnoreCase(authorName);
	}


	 @Override
	    public Blogs addBlog(MultipartFile image, Blogs blog) {
	        if (blog == null) {
	            throw new IllegalArgumentException("Blog object cannot be null");
	        }
	        if (blog.getTitle() == null || blog.getTitle().trim().isEmpty()) {
	            throw new IllegalArgumentException("Blog title cannot be empty");
	        }
	        if (blog.getContent() == null || blog.getContent().trim().isEmpty()) {
	            throw new IllegalArgumentException("Blog content cannot be empty");
	        }
	        if (blog.getAuthor() == null || blog.getAuthor().trim().isEmpty()) {
	            throw new IllegalArgumentException("Author cannot be empty");
	        }

	       
	        if (image != null && !image.isEmpty()) {
	            String fileName = FileStorageService.storeFile(image);
	            blog.setImage(fileName); 
	            blog.setImagePath(FileStorageService.getUploadDir()+"/"+fileName);
	        }

	        return blogRepository.save(blog);
	    }

	@Override
	public void deleteBlog(long id) {
	    if (!blogRepository.existsById(id)) {
	        throw new RuntimeException("Blog not found with id: " + id);
	    }
	    blogRepository.deleteById(id);
	}


	@Override
	public Blogs updateBlog(Blogs blog) {
		blog.setUpdatedAt(LocalDateTime.now());
	    return blogRepository.save(blog);
	}


    @Override
    public Blogs getById(long id) {
        Optional<Blogs> blog = blogRepository.findById(id);
        if (blog.isPresent()) {
            return blog.get();
        } else {
            throw new RuntimeException("Blog not found with id: " + id);
        }
    }

}
