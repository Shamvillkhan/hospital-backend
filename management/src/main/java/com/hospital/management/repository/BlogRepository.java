package com.hospital.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.management.entity.Blogs;

@Repository
public interface BlogRepository extends JpaRepository<Blogs, Long>  {
	
	  List<Blogs> findByAuthorContainingIgnoreCase(String author);
}
