package com.hospital.management.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "contact_us")
public class ContactUs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contact_id")
	private long id;

	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 50, message = "Name must be between 2 to 50 characters")
	@Column(name = "name", nullable = false)
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	@Size(max = 100, message = "Email must not exceed 100 characters")
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@NotBlank(message = "Subject is required")
	@Size(min = 3, max = 50, message = "Subject must be between 3 to 50 characters")
	@Column(name = "subject", nullable = false)
	private String subject;

	@NotBlank(message = "Message is required")
	@Size(min = 10, max = 500, message = "Message must be between 10 to 500 characters")
	@Column(name = "message", nullable = false, length = 1000)
	private String message;

	@PastOrPresent(message = "Created date cannot be in the future")
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	public ContactUs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContactUs(
			@NotBlank(message = "Name is required") @Size(min = 2, max = 50, message = "Name must be between 2 to 50 characters") String name,
			@NotBlank(message = "Email is required") @Email(message = "Email should be valid") @Size(max = 100, message = "Email must not exceed 100 characters") String email,
			@NotBlank(message = "Subject is required") @Size(min = 3, max = 50, message = "Subject must be between 3 to 50 characters") String subject,
			@NotBlank(message = "Message is required") @Size(min = 10, max = 500, message = "Message must be between 10 to 500 characters") String message,
			@PastOrPresent(message = "Created date cannot be in the future") LocalDateTime createdAt) {
		super();
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.message = message;
		this.createdAt = createdAt;
	}

	public ContactUs(long id,
			@NotBlank(message = "Name is required") @Size(min = 2, max = 50, message = "Name must be between 2 to 50 characters") String name,
			@NotBlank(message = "Email is required") @Email(message = "Email should be valid") @Size(max = 100, message = "Email must not exceed 100 characters") String email,
			@NotBlank(message = "Subject is required") @Size(min = 3, max = 50, message = "Subject must be between 3 to 50 characters") String subject,
			@NotBlank(message = "Message is required") @Size(min = 10, max = 500, message = "Message must be between 10 to 500 characters") String message,
			@PastOrPresent(message = "Created date cannot be in the future") LocalDateTime createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.message = message;
		this.createdAt = createdAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "ContactUs [id=" + id + ", name=" + name + ", email=" + email + ", subject=" + subject + ", message="
				+ message + ", createdAt=" + createdAt + "]";
	}

}
