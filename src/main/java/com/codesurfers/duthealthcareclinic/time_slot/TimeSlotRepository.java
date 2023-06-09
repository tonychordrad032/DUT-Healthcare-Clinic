package com.codesurfers.duthealthcareclinic.time_slot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

   // @Query("FROM tbl_time_slots t WHERE t.deleted = 0 AND t.time = :time AND ")
    List<TimeSlot> findByTime(String time);

    //@Query("FROM tbl_time_slots t WHERE t.deleted = 0 AND t.day = :day")
    TimeSlot findTimeSlotByDay(String day);
}
