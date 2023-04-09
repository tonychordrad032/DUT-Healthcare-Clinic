package com.codesurfers.duthealthcareclinic.appointment;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("FROM tbl_appointments b WHERE b.deleted = 0 AND b.studentNumber LIKE %:searchText%")
    Page<Appointment> findAllBySearch(Pageable pageable, String searchText);
}
