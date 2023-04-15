package com.codesurfers.duthealthcareclinic.appointment_day;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "tbl_appointment_day")
@Data
public class AppointmentDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long appointmentDayId;
    private  String appointmentDayName;
    private boolean booked = false;

    private int deleted = 0;

}
