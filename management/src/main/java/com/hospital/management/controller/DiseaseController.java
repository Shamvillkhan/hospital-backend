package com.hospital.management.controller;

import java.util.List;
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
import com.hospital.management.entity.Disease;
import com.hospital.management.service.DiseaseServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/disease")
public class DiseaseController {

	private DiseaseServiceImpl diseaseServiceImpl;

	public DiseaseController(DiseaseServiceImpl diseaseServiceImpl) {
		this.diseaseServiceImpl = diseaseServiceImpl;
	}

	@GetMapping("/gett/{id}")
	public ResponseEntity<?> getDiseaseById(@PathVariable Long id) {
		try {
			Disease disease = diseaseServiceImpl.getDiseaseById(id);
			return ResponseEntity.ok(disease);
		} catch (RuntimeException e) {

			return ResponseEntity.status(404).body("Disease not found with id: " + id);
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Disease>> getAllDisease() {

		return ResponseEntity.ok(diseaseServiceImpl.getAllDisease());
	}

	@PostMapping("/add")
	public ResponseEntity<?> createDisease(@Valid
	        @RequestPart("disease") Disease disease,
	        @RequestPart(value = "image", required = false) MultipartFile image) {

	    try {
	        // No need for ObjectMapper! Spring handles it.
	        Disease savedDisease = diseaseServiceImpl.addDisease(image, disease);
	        return ResponseEntity.status(HttpStatus.CREATED).body(savedDisease);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
	    }
	}
	   @PutMapping("/update/{id}")
	    public ResponseEntity<Disease> updateDisease(@Valid
	            @PathVariable Long id,
	            @RequestPart("disease") Disease disease,
	            @RequestPart(value = "image", required = false) MultipartFile image) {

	        disease.setId(id); // ensure ID is set
	        Disease updatedDisease = diseaseServiceImpl.updateDisease(image, disease);
	        return ResponseEntity.ok(updatedDisease);
	    }


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteDisease(@PathVariable Long id) {
		diseaseServiceImpl.deleteDisease(id);
		return ResponseEntity.ok("Disease deleted successfully with id: " + id);
	}

}
