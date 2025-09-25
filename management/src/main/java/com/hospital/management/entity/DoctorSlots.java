package com.hospital.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "doctor_slots")
public class DoctorSlots {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doctor_slot_id")
	private long doctorSlotId;

	@NotNull(message = "Staff is required")
    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
	private Staff staffId;

	@NotNull(message = "Slot is required")
    @ManyToOne
    @JoinColumn(name = "slot_id", nullable = false)
	private Slots slotId;

	@NotNull(message = "Day of week is required")
	@Enumerated(EnumType.STRING)
	@Column(name = "day_of_week", nullable = false)
	private DayOfWeek dayOfWeek;

	@NotNull
	@Column(name = "is_active", nullable = false)
	private Boolean isActive = true;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", updatable = false)
	private LocalDateTime updatedAt;
	
	public enum DayOfWeek {
		Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
	}

	public DoctorSlots() {
		super();
	
	}
	
	

	public DoctorSlots(@NotNull(message = "Staff is required") Staff staffId,
			@NotNull(message = "Slot is required") Slots slotId,
			@NotNull(message = "Day of week is required") DayOfWeek dayOfWeek, @NotNull Boolean isActive,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.staffId = staffId;
		this.slotId = slotId;
		this.dayOfWeek = dayOfWeek;
		this.isActive = isActive;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}



	public DoctorSlots(long doctorSlotId, @NotNull(message = "Staff is required") Staff staffId,
			@NotNull(message = "Slot is required") Slots slotId,
			@NotNull(message = "Day of week is required") DayOfWeek dayOfWeek, @NotNull Boolean isActive,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.doctorSlotId = doctorSlotId;
		this.staffId = staffId;
		this.slotId = slotId;
		this.dayOfWeek = dayOfWeek;
		this.isActive = isActive;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}



	public long getDoctorSlotId() {
		return doctorSlotId;
	}



	public void setDoctorSlotId(long doctorSlotId) {
		this.doctorSlotId = doctorSlotId;
	}



	public Staff getStaffId() {
		return staffId;
	}



	public void setStaffId(Staff staffId) {
		this.staffId = staffId;
	}



	public Slots getSlotId() {
		return slotId;
	}



	public void setSlotId(Slots slotId) {
		this.slotId = slotId;
	}



	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}



	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}



	public Boolean getIsActive() {
		return isActive;
	}



	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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



	@Override
	public String toString() {
		return "DoctorSlots [doctorSlotId=" + doctorSlotId + ", staffId=" + staffId + ", slotId=" + slotId
				+ ", dayOfWeek=" + dayOfWeek + ", isActive=" + isActive + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}

	

}
