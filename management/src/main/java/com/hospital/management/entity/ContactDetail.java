package com.hospital.management.entity;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "contact_detail")
public class ContactDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "detail_id")
	private long id;

	@NotBlank(message = "Phone number cannot be empty")
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone must be a valid 10-digit number")
	@Column(name = "phone", nullable = false, length = 15)
	private String phone;

	@NotBlank(message = "Email cannot be empty")
	@Email(message = "Invalid email format")
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@NotBlank(message = "Address cannot be empty")
	@Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
	@Column(name = "address", nullable = false, length = 255)
	private String address;

	@URL(message = "Invalid Facebook URL")
	@Column(name = "facebook")
	private String facebook;

	@URL(message = "Invalid Twitter URL")
	@Column(name = "twitter")
	private String twitter;

	@URL(message = "Invalid Instagram URL")
	@Column(name = "instagram")
	private String instagram;

	@Column(name = "created_at", updatable = false, insertable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", insertable = false)
	private LocalDateTime updatedAt;

	@Column(name = "isActive", nullable = false)
	private boolean active; 

	public ContactDetail() {
		super();
	}

	public ContactDetail(
			@NotBlank(message = "Phone number cannot be empty") @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be a valid 10-digit number") String phone,
			@NotBlank(message = "Email cannot be empty") @Email(message = "Invalid email format") String email,
			@NotBlank(message = "Address cannot be empty") @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters") String address,
			@URL(message = "Invalid Facebook URL") String facebook,
			@URL(message = "Invalid Twitter URL") String twitter,
			@URL(message = "Invalid Instagram URL") String instagram, LocalDateTime createdAt, LocalDateTime updatedAt,
			boolean isActive) {
		super();
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.facebook = facebook;
		this.twitter = twitter;
		this.instagram = instagram;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.active = isActive;
	}

	public ContactDetail(long id,
			@NotBlank(message = "Phone number cannot be empty") @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be a valid 10-digit number") String phone,
			@NotBlank(message = "Email cannot be empty") @Email(message = "Invalid email format") String email,
			@NotBlank(message = "Address cannot be empty") @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters") String address,
			@URL(message = "Invalid Facebook URL") String facebook,
			@URL(message = "Invalid Twitter URL") String twitter,
			@URL(message = "Invalid Instagram URL") String instagram, LocalDateTime createdAt, LocalDateTime updatedAt,
			boolean isActive) {
		super();
		this.id = id;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.facebook = facebook;
		this.twitter = twitter;
		this.instagram = instagram;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.active = isActive;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
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

	public boolean isActive() {
	    return active;
	}

	public void setActive(boolean active) {
	    this.active = active;
	}

	@Override
	public String toString() {
		return "ContactDetail [id=" + id + ", phone=" + phone + ", email=" + email + ", address=" + address
				+ ", facebook=" + facebook + ", twitter=" + twitter + ", instagram=" + instagram + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", isActive=" + active + "]";
	}

}
