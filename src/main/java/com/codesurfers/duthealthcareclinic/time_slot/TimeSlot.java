package com.codesurfers.duthealthcareclinic.time_slot;

import com.codesurfers.duthealthcareclinic.appointment_day.AppointmentDay;
import javax.persistence.*;
import lombok.Data;

@Entity(name = "tbl_time_slots")
@Data
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long timeSlotId;
    private String time;

    @ManyToOne
    private AppointmentDay day;
    private boolean booked = false;

    private int deleted = 0;
}
