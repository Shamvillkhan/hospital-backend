package com.hospital.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "staff")
public class Staff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staff_id")
	private long staffId;

	@NotBlank(message = "First name is required")
	@Size(max = 50, message = "First name cannot exceed 50 characters")
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@NotBlank(message = "Last name is required")
	@Size(max = 50, message = "Last name cannot exceed 50 characters")
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	@NotNull(message = "Role is required")
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false, length = 20)
	private StaffRole role;

	@Size(max = 100, message = "Specialization cannot exceed 100 characters")
	@Column(name = "specialization", length = 100)
	private String specialization;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[0-9]{10,20}$", message = "Phone number must be between 10-20 digits")
	@Column(name = "phone", nullable = false, length = 20)
	private String phone;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	@Size(max = 100, message = "Email cannot exceed 100 characters")
	@Column(name = "email", nullable = false, unique = true, length = 100)
	private String email;

	@NotNull(message = "Hire date is required")
	@Column(name = "hire_date", nullable = false)
	private LocalDate hireDate;

	// 🔗 Foreign Key: Department
	@ManyToOne
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;

	@Size(max = 255, message = "Image name cannot exceed 255 characters")
	@Column(name = "image", length = 255)
	private String image;

	@Size(max = 255, message = "Image path cannot exceed 255 characters")
	@Column(name = "image_path", length = 255)
	private String imagePath;

	@Size(max = 255, message = "Address cannot exceed 255 characters")
	@Column(name = "address", length = 255)
	private String address;

	@Size(max = 255, message = "Working days cannot exceed 255 characters")
	@Column(name = "working_days", length = 255)
	private String workingDays;

	// --- Enum for Role ---
	public enum StaffRole {
		Doctor, Nurse, Admin, Receptionist
	}

	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Staff(long staffId,
			@NotBlank(message = "First name is required") @Size(max = 50, message = "First name cannot exceed 50 characters") String firstName,
			@NotBlank(message = "Last name is required") @Size(max = 50, message = "Last name cannot exceed 50 characters") String lastName,
			@NotNull(message = "Role is required") StaffRole role,
			@Size(max = 100, message = "Specialization cannot exceed 100 characters") String specialization,
			@NotBlank(message = "Phone number is required") @Pattern(regexp = "^[0-9]{10,20}$", message = "Phone number must be between 10-20 digits") String phone,
			@NotBlank(message = "Email is required") @Email(message = "Invalid email format") @Size(max = 100, message = "Email cannot exceed 100 characters") String email,
			@NotNull(message = "Hire date is required") LocalDate hireDate, Department department,
			@Size(max = 255, message = "Image name cannot exceed 255 characters") String image,
			@Size(max = 255, message = "Image path cannot exceed 255 characters") String imagePath,
			@Size(max = 255, message = "Address cannot exceed 255 characters") String address,
			@Size(max = 255, message = "Working days cannot exceed 255 characters") String workingDays) {
		super();
		this.staffId = staffId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.specialization = specialization;
		this.phone = phone;
		this.email = email;
		this.hireDate = hireDate;
		this.department = department;
		this.image = image;
		this.imagePath = imagePath;
		this.address = address;
		this.workingDays = workingDays;
	}

	public Staff(
			@NotBlank(message = "First name is required") @Size(max = 50, message = "First name cannot exceed 50 characters") String firstName,
			@NotBlank(message = "Last name is required") @Size(max = 50, message = "Last name cannot exceed 50 characters") String lastName,
			@NotNull(message = "Role is required") StaffRole role,
			@Size(max = 100, message = "Specialization cannot exceed 100 characters") String specialization,
			@NotBlank(message = "Phone number is required") @Pattern(regexp = "^[0-9]{10,20}$", message = "Phone number must be between 10-20 digits") String phone,
			@NotBlank(message = "Email is required") @Email(message = "Invalid email format") @Size(max = 100, message = "Email cannot exceed 100 characters") String email,
			@NotNull(message = "Hire date is required") LocalDate hireDate, Department department,
			@Size(max = 255, message = "Image name cannot exceed 255 characters") String image,
			@Size(max = 255, message = "Image path cannot exceed 255 characters") String imagePath,
			@Size(max = 255, message = "Address cannot exceed 255 characters") String address,
			@Size(max = 255, message = "Working days cannot exceed 255 characters") String workingDays) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.specialization = specialization;
		this.phone = phone;
		this.email = email;
		this.hireDate = hireDate;
		this.department = department;
		this.image = image;
		this.imagePath = imagePath;
		this.address = address;
		this.workingDays = workingDays;
	}

	public long getStaffId() {
		return staffId;
	}

	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public StaffRole getRole() {
		return role;
	}

	public void setRole(StaffRole role) {
		this.role = role;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
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

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(String workingDays) {
		this.workingDays = workingDays;
	}

	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role
				+ ", specialization=" + specialization + ", phone=" + phone + ", email=" + email + ", hireDate="
				+ hireDate + ", department=" + department + ", image=" + image + ", imagePath=" + imagePath
				+ ", address=" + address + ", workingDays=" + workingDays + "]";
	}

}
