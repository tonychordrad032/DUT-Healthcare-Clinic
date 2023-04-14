package com.codesurfers.healthcare.model;

import java.time.LocalDateTime;

public class Appointment {
    private long appointmentId;
    private String studentNumber;
    private String status;
    private String qualification;
    private LocalDateTime appointmentDate;
    private LocalDateTime appointmentTime;

    public Appointment(String studentNumber, String status, String qualification, LocalDateTime appointmentDate, LocalDateTime appointmentTime) {
        this.studentNumber = studentNumber;
        this.status = status;
        this.qualification = qualification;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}
