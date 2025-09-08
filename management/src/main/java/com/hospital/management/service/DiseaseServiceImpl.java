package com.hospital.management.service;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hospital.management.entity.Disease;
import com.hospital.management.fileutility.FileStorageService;
import com.hospital.management.repository.DiseaseRepository;

@Service
public class DiseaseServiceImpl implements DiseaseService {

	private DiseaseRepository diseaseRepository;

	public DiseaseServiceImpl(DiseaseRepository diseaseRepository) {
		this.diseaseRepository = diseaseRepository;
	}

	@Override
	public List<Disease> getAllDisease() {

		return diseaseRepository.findAll();
	}

	@Override
	public Disease addDisease(MultipartFile image, Disease disease) {
		if (disease == null) {
			throw new IllegalArgumentException("Disease object cannot be null");
		}
		if (disease.getDiseaseName() == null || disease.getDiseaseName().trim().isEmpty()) {
			throw new IllegalArgumentException("Disease Disease name cannot be empty");
		}
		if (disease.getCurePrecaution() == null || disease.getCurePrecaution().trim().isEmpty()) {
			throw new IllegalArgumentException("Disease Precaution cannot be empty");
		}

		if (image != null && !image.isEmpty()) {
			String fileName = FileStorageService.storeFile(image);
			disease.setImage(fileName);
			disease.setImagePath(FileStorageService.getUploadDir() + fileName);
		}

		return diseaseRepository.save(disease);
	}

	@Override
	public void deleteDisease(long id) {
		if (!diseaseRepository.existsById(id)) {
			throw new RuntimeException("Disease not found with id: " + id);
		}
		diseaseRepository.deleteById(id);

	}

	@Override
	public Disease getDiseaseById(long id) {
		Optional<Disease> disease = diseaseRepository.findById(id);
		if (disease.isPresent()) {
			return disease.get();
		} else {
			throw new RuntimeException("Disease not found with id: " + id);
		}
	}

	@Override
	public Disease updateDisease(MultipartFile image, Disease disease) {

	    Disease existing = diseaseRepository.findById(disease.getId())
	            .orElseThrow(() -> new RuntimeException("Disease not found with id " + disease.getId()));

	    // update text fields
	    existing.setDiseaseName(disease.getDiseaseName());
	    existing.setCurePrecaution(disease.getCurePrecaution());
	    existing.setUpdatedAt(LocalDateTime.now());

	    // if new image is uploaded
	    if (image != null && !image.isEmpty()) {
	        try {
	            // storeFile should return unique file name (e.g. timestamp + original name)
	            String fileName = FileStorageService.storeFile(image);

	            // save into db
	            existing.setImage(fileName);
	            existing.setImagePath(FileStorageService.getUploadDir() + "/" + fileName);

	        } catch (Exception e) {
	            throw new RuntimeException("Failed to save image: " + e.getMessage(), e);
	        }
	    }

	    return diseaseRepository.save(existing);
	}


}
