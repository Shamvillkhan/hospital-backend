package com.hospital.management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.management.entity.Blogs;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	@GetMapping("/test")
	public String test() {
		return "hello its rest Api project checking";
	}
	
	
}
