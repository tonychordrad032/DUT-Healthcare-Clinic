package com.codesurfers.duthealthcareclinic.appointment_day;

import javax.persistence.*;
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
