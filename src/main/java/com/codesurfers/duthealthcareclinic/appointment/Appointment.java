package com.codesurfers.duthealthcareclinic.appointment;

import com.codesurfers.duthealthcareclinic.clinic.Clinic;
import com.codesurfers.duthealthcareclinic.time_slot.TimeSlot;
import com.codesurfers.duthealthcareclinic.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "tbl_appointments")
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long appointmentId;
    @ManyToOne
    private User patient;
    @OneToOne
    private Clinic clinic;
    private String status;

    private String notes;

    private int deleted = 0;
    @OneToOne
    private TimeSlot appointmentTime;


}
