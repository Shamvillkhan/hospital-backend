package com.hospital.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "departments")
public class Department {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "department_id")
		private long departmentId;
	
		@NotBlank(message = "Department name is required")
		@Size(max = 100, message = "Department name cannot exceed 100 characters")
		@Column(name = "name", nullable = false, length = 100)
		private String name;
	
		@Size(max = 1000, message = "Description cannot exceed 1000 characters")
		@Column(name = "description", columnDefinition = "TEXT")
		private String description;
	
		// ✅ Staff reference (head_of_department)
		@Column(name = "head_of_department")
		private Integer headOfDepartment;
		// (Later tu isko Staff entity ke sath FK relation bhi bana sakta hai)
	
		@Pattern(regexp = "^[0-9]{10,15}$", message = "Contact number must be 10 to 15 digits")
		@Column(name = "contact_number", length = 15)
		private String contactNumber;
	
		@Size(max = 100, message = "Location cannot exceed 100 characters")
		@Column(name = "location", length = 100)
		private String location;
	
		@Column(name = "created_at", nullable = false, updatable = false)
		private LocalDateTime createdAt = LocalDateTime.now();
		
	

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department(long departmentId,
			@NotBlank(message = "Department name is required") @Size(max = 100, message = "Department name cannot exceed 100 characters") String name,
			@Size(max = 1000, message = "Description cannot exceed 1000 characters") String description,
			Integer headOfDepartment,
			@Pattern(regexp = "^[0-9]{10,15}$", message = "Contact number must be 10 to 15 digits") String contactNumber,
			@Size(max = 100, message = "Location cannot exceed 100 characters") String location,
			LocalDateTime createdAt) {
		super();
		this.departmentId = departmentId;
		this.name = name;
		this.description = description;
		this.headOfDepartment = headOfDepartment;
		this.contactNumber = contactNumber;
		this.location = location;
		this.createdAt = createdAt;
	}

	public Department(
			@NotBlank(message = "Department name is required") @Size(max = 100, message = "Department name cannot exceed 100 characters") String name,
			@Size(max = 1000, message = "Description cannot exceed 1000 characters") String description,
			Integer headOfDepartment,
			@Pattern(regexp = "^[0-9]{10,15}$", message = "Contact number must be 10 to 15 digits") String contactNumber,
			@Size(max = 100, message = "Location cannot exceed 100 characters") String location,
			LocalDateTime createdAt) {
		super();
		this.name = name;
		this.description = description;
		this.headOfDepartment = headOfDepartment;
		this.contactNumber = contactNumber;
		this.location = location;
		this.createdAt = createdAt;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getHeadOfDepartment() {
		return headOfDepartment;
	}

	public void setHeadOfDepartment(Integer headOfDepartment) {
		this.headOfDepartment = headOfDepartment;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", name=" + name + ", description=" + description
				+ ", headOfDepartment=" + headOfDepartment + ", contactNumber=" + contactNumber + ", location="
				+ location + ", createdAt=" + createdAt + "]";
	}

}
