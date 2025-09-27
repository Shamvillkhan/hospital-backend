package com.hospital.management.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor

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


	@OneToMany(cascade = CascadeType.ALL)
	private List<Billing> billing;

	public enum Gender {
		Male, Female, Other
	}
	
	

}
