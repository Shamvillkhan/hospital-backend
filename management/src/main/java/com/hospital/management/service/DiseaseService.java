package com.hospital.management.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hospital.management.entity.Disease;

public interface DiseaseService {
	
	List<Disease> getAllDisease();
	
	Disease addDisease(MultipartFile image,Disease disease);
	
	void deleteDisease(long id);
	
	Disease getDiseaseById(long id);
	
	Disease updateDisease(MultipartFile image,Disease disease);
}
