package com.codesurfers.duthealthcareclinic.appointment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "tbl_appointments")
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long appointmentId;

    private String studentNumber;
    private String status;
    private String qualification;
    private int deleted = 0;
    private LocalDateTime appointmentDate;
    private LocalDateTime appointmentTime;

}
