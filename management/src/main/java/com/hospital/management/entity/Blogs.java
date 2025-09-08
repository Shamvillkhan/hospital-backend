package com.hospital.management.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "blog")
public class Blogs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "blog_id")
	private long id;

	@Column(name = "title")
	@NotBlank(message = "Title cannot be blank")
	@Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
	private String title;

	@Column(name = "content")
	@NotBlank(message = "Content cannot be blank")
	@Size(min = 10, message = "Content must be at least 10 characters long")
	private String content;

	@Column(name = "author")
	@NotBlank(message = "Author cannot be blank")
	private String author;

	@Column(name = "created_at", updatable = false, insertable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", insertable = false)
	private LocalDateTime updatedAt;

	@Column(name = "image")
	private String image;

	@Column(name = "image_path")
	private String imagePath;

	public Blogs() {
		super();
	}

	public Blogs(
			@NotBlank(message = "Title cannot be blank") @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters") String title,
			@NotBlank(message = "Content cannot be blank") @Size(min = 10, message = "Content must be at least 10 characters long") String content,
			@NotBlank(message = "Author cannot be blank") String author, LocalDateTime createdAt,
			LocalDateTime updatedAt, String image, String imagePath) {
		super();
		this.title = title;
		this.content = content;
		this.author = author;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.image = image;
		this.imagePath = imagePath;
	}

	public Blogs(long id,
			@NotBlank(message = "Title cannot be blank") @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters") String title,
			@NotBlank(message = "Content cannot be blank") @Size(min = 10, message = "Content must be at least 10 characters long") String content,
			@NotBlank(message = "Author cannot be blank") String author, LocalDateTime createdAt,
			LocalDateTime updatedAt, String image, String imagePath) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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
		return "Blogs [id=" + id + ", title=" + title + ", content=" + content + ", author=" + author + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", image=" + image + ", imagePath=" + imagePath + "]";
	}
	
	

}
