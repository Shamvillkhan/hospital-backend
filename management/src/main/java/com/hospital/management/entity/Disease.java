package com.hospital.management.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "disease")
public class Disease {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "disease_id")
	private long id;

	@Column(name = "disease_name")
	@NotBlank(message = "Disease name cannot be blank")
	private String diseaseName;

	@Column(name = "cure_precaution")
	@NotBlank(message = "Precaution cannot be blank")
	@Size(max = 500, message = "Cure&Precaution must not exceed 500 characters")
	private String curePrecaution;

	@Column(name = "created_at", updatable = false, insertable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", insertable = false)
	private LocalDateTime updatedAt;

	@Column(name = "image")
	private String image;

	@Column(name = "image_path")
	private String imagePath;

	public Disease() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Disease(@NotBlank(message = "Disease name cannot be blank") String diseaseName,
			@NotBlank(message = "Precaution cannot be blank") @Size(max = 500, message = "Cure&Precaution must not exceed 500 characters") String curePrecaution,
			LocalDateTime createdAt, LocalDateTime updatedAt, String image, String imagePath) {
		super();
		this.diseaseName = diseaseName;
		this.curePrecaution = curePrecaution;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.image = image;
		this.imagePath = imagePath;
	}

	public Disease(long id, @NotBlank(message = "Disease name cannot be blank") String diseaseName,
			@NotBlank(message = "Precaution cannot be blank") @Size(max = 500, message = "Cure&Precaution must not exceed 500 characters") String curePrecaution,
			LocalDateTime createdAt, LocalDateTime updatedAt, String image, String imagePath) {
		super();
		this.id = id;
		this.diseaseName = diseaseName;
		this.curePrecaution = curePrecaution;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.image = image;
		this.imagePath = imagePath;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public String getCurePrecaution() {
		return curePrecaution;
	}

	public void setCurePrecaution(String curePrecaution) {
		this.curePrecaution = curePrecaution;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public String toString() {
		return "Disease [id=" + id + ", diseaseName=" + diseaseName + ", curePrecaution=" + curePrecaution
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", image=" + image + ", imagePath="
				+ imagePath + "]";
	}

}