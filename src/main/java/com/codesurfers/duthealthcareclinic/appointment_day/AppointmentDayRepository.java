package com.codesurfers.duthealthcareclinic.appointment_day;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentDayRepository extends JpaRepository<AppointmentDay, Long> {
    AppointmentDay findByAppointmentDayName(String appointmentDayName);
}
