package com.hospital.management.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.management.entity.DoctorSlots;

@Repository
public interface DoctorSlotRepository extends JpaRepository<DoctorSlots, Long> {

    // 1️⃣ Find slots by doctor id
    List<DoctorSlots> findByStaffId_StaffId(long staffId);

    // 2️⃣ Find all slots of a doctor for today or upcoming (isActive = true)
    List<DoctorSlots> findByStaffId_StaffIdAndIsActiveTrue(long staffId);


    List<DoctorSlots> findByStaffId_FirstNameContainingIgnoreCaseOrStaffId_LastNameContainingIgnoreCase(String firstName, String lastName);


    List<DoctorSlots> findByDayOfWeek(DoctorSlots.DayOfWeek dayOfWeek);

    // 5️⃣ Fetch by slot date (assuming you want by appointment date for that slot)
    // Note: DoctorSlots entity does not have date, but if you have date in appointment, you can join with appointment
    // Placeholder method (to implement with @Query if needed)
    // @Query("SELECT ds FROM DoctorSlots ds JOIN ds.appointments a WHERE a.appointmentDate = :date")
    // List<DoctorSlots> findByAppointmentDate(@Param("date") LocalDate date);


    List<DoctorSlots> findBySlotId_StartTime(LocalTime startTime);

   
    List<DoctorSlots> findBySlotId_EndTime(LocalTime endTime);
}
