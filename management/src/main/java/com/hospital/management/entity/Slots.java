package com.hospital.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "slots")
public class Slots {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "slot_id")
	private long slotId;

	@NotBlank(message = "Slot name is required")
	@Column(name = "slot_name", nullable = false, length = 50)
	private String slotName;

	@NotNull(message = "Start time is required")
	@Column(name = "start_time", nullable = false)
	private LocalTime startTime;

	@NotNull(message = "End time is required")
	@Column(name = "end_time", nullable = false)
	private LocalTime endTime;

	@Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;

	@PrePersist
	protected void onCreate() {
		if (this.createdAt == null) {
			this.createdAt = LocalDateTime.now();
		}
	}

	public Slots() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Slots(@NotBlank(message = "Slot name is required") String slotName,
			@NotNull(message = "Start time is required") LocalTime startTime,
			@NotNull(message = "End time is required") LocalTime endTime, LocalDateTime createdAt) {
		super();
		this.slotName = slotName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createdAt = createdAt;
	}

	public Slots(long slotId, @NotBlank(message = "Slot name is required") String slotName,
			@NotNull(message = "Start time is required") LocalTime startTime,
			@NotNull(message = "End time is required") LocalTime endTime, LocalDateTime createdAt) {
		super();
		this.slotId = slotId;
		this.slotName = slotName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createdAt = createdAt;
	}

	public long getSlotId() {
		return slotId;
	}

	public void setSlotId(long slotId) {
		this.slotId = slotId;
	}

	public String getSlotName() {
		return slotName;
	}

	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Slots [slotId=" + slotId + ", slotName=" + slotName + ", startTime=" + startTime + ", endTime="
				+ endTime + ", createdAt=" + createdAt + "]";
	}

}
