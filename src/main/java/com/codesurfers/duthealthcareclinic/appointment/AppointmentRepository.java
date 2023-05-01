package com.codesurfers.duthealthcareclinic.appointment;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    /**@Query("FROM tbl_appointments b WHERE b.deleted = 0 AND b.studentNumber LIKE %:searchText%")
    Page<Appointment> findAllBySearch(Pageable pageable, String searchText);*/

    /**@Query("FROM tbl_appointments a WHERE a.deleted = 0 AND a.appointment_day = :userId")
    List<Appointment> findAppointmentByUserId(long userId);*/


    @Query("FROM tbl_appointments a WHERE a.deleted = 0 AND a.patient.userId= :userId AND status = 'Open'")
    List<Appointment> findOpenBookingsByUserId(long userId);
}
