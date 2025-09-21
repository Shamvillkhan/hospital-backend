package com.hospital.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "doctor_appointments")
public class DoctorAppointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_id")
	private long appointmentId;

	@NotBlank(message = "Name is required")
	@Size(max = 45, message = "Name cannot exceed 45 characters")
	@Column(name = "name", nullable = false, length = 45)
	private String name;

	@NotNull(message = "Appointment date is required")
	@Column(name = "appointment_date", nullable = false)
	private LocalDate appointmentDate; // 👈 sirf date (yyyy-MM-dd)

	@NotNull(message = "Appointment time is required")
	@Column(name = "appointment_time", nullable = false)
	private LocalTime appointmentTime; // 👈 sirf time (HH:mm:ss)

	@NotNull(message = "Status is required")
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 20)
	private AppointmentStatus status;

	@Size(max = 500, message = "Notes cannot exceed 500 characters")
	@Column(name = "notes", columnDefinition = "TEXT")
	private String notes;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	@Size(max = 45, message = "Email cannot exceed 45 characters")
	@Column(name = "email", nullable = false, length = 45)
	private String email;

	@ManyToOne
	@JoinColumn(name = "staff_id")
	private Staff staff;

	@ManyToOne
	@JoinColumn(name = "slot_id")
	private Slots slot;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
	@Column(name = "phone", nullable = false, length = 15)
	private String phone;

	public enum AppointmentStatus {
		Scheduled, Completed, Cancelled
	}

	public DoctorAppointment() {
		super();

	}

	public DoctorAppointment(
			@NotBlank(message = "Name is required") @Size(max = 45, message = "Name cannot exceed 45 characters") String name,
			@NotNull(message = "Appointment date is required") LocalDate appointmentDate,
			@NotNull(message = "Appointment time is required") LocalTime appointmentTime,
			@NotNull(message = "Status is required") AppointmentStatus status,
			@Size(max = 500, message = "Notes cannot exceed 500 characters") String notes,
			@NotBlank(message = "Email is required") @Email(message = "Invalid email format") @Size(max = 45, message = "Email cannot exceed 45 characters") String email,
			Staff staff, Slots slot,
			@NotBlank(message = "Phone number is required") @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits") String phone) {
		super();
		this.name = name;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.status = status;
		this.notes = notes;
		this.email = email;
		this.staff = staff;
		this.slot = slot;
		this.phone = phone;
	}

	public DoctorAppointment(long appointmentId,
			@NotBlank(message = "Name is required") @Size(max = 45, message = "Name cannot exceed 45 characters") String name,
			@NotNull(message = "Appointment date is required") LocalDate appointmentDate,
			@NotNull(message = "Appointment time is required") LocalTime appointmentTime,
			@NotNull(message = "Status is required") AppointmentStatus status,
			@Size(max = 500, message = "Notes cannot exceed 500 characters") String notes,
			@NotBlank(message = "Email is required") @Email(message = "Invalid email format") @Size(max = 45, message = "Email cannot exceed 45 characters") String email,
			Staff staff, Slots slot,
			@NotBlank(message = "Phone number is required") @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits") String phone) {
		super();
		this.appointmentId = appointmentId;
		this.name = name;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.status = status;
		this.notes = notes;
		this.email = email;
		this.staff = staff;
		this.slot = slot;
		this.phone = phone;
	}

	public long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public LocalTime getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(LocalTime appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Slots getSlot() {
		return slot;
	}

	public void setSlot(Slots slot) {
		this.slot = slot;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "DoctorAppointment [appointmentId=" + appointmentId + ", name=" + name + ", appointmentDate="
				+ appointmentDate + ", appointmentTime=" + appointmentTime + ", status=" + status + ", notes=" + notes
				+ ", email=" + email + ", staff=" + staff + ", slot=" + slot + ", phone=" + phone + "]";
	}

}
