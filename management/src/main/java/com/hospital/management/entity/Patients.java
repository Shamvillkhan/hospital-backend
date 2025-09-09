package com.hospital.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
public class Patients {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_id")
	private long patientId;

	@NotBlank(message = "First name is required")
	@Size(max = 50, message = "First name cannot exceed 50 characters")
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@NotBlank(message = "Last name is required")
	@Size(max = 50, message = "Last name cannot exceed 50 characters")
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	@NotNull(message = "Date of birth is required")
	@Column(name = "date_of_birth", nullable = false)
	private LocalDate dateOfBirth;

	@NotNull(message = "Gender is required")
	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false, length = 10)
	private Gender gender;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
	@Column(name = "phone", nullable = false, unique = true, length = 20)
	private String phone;

	@NotBlank(message = "Address is required")
	@Column(name = "address", nullable = false, columnDefinition = "TEXT")
	private String address;

	@Email(message = "Invalid email format")
	@Size(max = 100, message = "Email cannot exceed 100 characters")
	@Column(name = "email", unique = true, length = 100)
	private String email;

	@Size(max = 5, message = "Blood type must be max 5 characters")
	@Column(name = "blood_type", length = 5)
	private String bloodType;

	@Column(name = "registration_date", nullable = false, updatable = false)
	private LocalDateTime registrationDate = LocalDateTime.now();

	public enum Gender {
		Male, Female, Other
	}
	
	

	public Patients() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patients(
			@NotBlank(message = "First name is required") @Size(max = 50, message = "First name cannot exceed 50 characters") String firstName,
			@NotBlank(message = "Last name is required") @Size(max = 50, message = "Last name cannot exceed 50 characters") String lastName,
			@NotNull(message = "Date of birth is required") LocalDate dateOfBirth,
			@NotNull(message = "Gender is required") Gender gender,
			@NotBlank(message = "Phone number is required") @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits") String phone,
			@NotBlank(message = "Address is required") String address,
			@Email(message = "Invalid email format") @Size(max = 100, message = "Email cannot exceed 100 characters") String email,
			@Size(max = 5, message = "Blood type must be max 5 characters") String bloodType,
			LocalDateTime registrationDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.bloodType = bloodType;
		this.registrationDate = registrationDate;
	}

	public Patients(long patientId,
			@NotBlank(message = "First name is required") @Size(max = 50, message = "First name cannot exceed 50 characters") String firstName,
			@NotBlank(message = "Last name is required") @Size(max = 50, message = "Last name cannot exceed 50 characters") String lastName,
			@NotNull(message = "Date of birth is required") LocalDate dateOfBirth,
			@NotNull(message = "Gender is required") Gender gender,
			@NotBlank(message = "Phone number is required") @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits") String phone,
			@NotBlank(message = "Address is required") String address,
			@Email(message = "Invalid email format") @Size(max = 100, message = "Email cannot exceed 100 characters") String email,
			@Size(max = 5, message = "Blood type must be max 5 characters") String bloodType,
			LocalDateTime registrationDate) {
		super();
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.bloodType = bloodType;
		this.registrationDate = registrationDate;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}


	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "Patients [patientId=" + patientId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", phone=" + phone + ", address=" + address
				+ ", email=" + email + ", bloodType=" + bloodType + ", registrationDate=" + registrationDate + "]";
	}

	
}
