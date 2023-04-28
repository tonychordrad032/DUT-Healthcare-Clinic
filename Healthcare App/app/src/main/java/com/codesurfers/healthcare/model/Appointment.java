package com.codesurfers.healthcare.model;

import java.time.LocalDateTime;

public class Appointment {
    private long appointmentId;

    private User patient;

    private Clinic clinic;
    private String status;

    private String notes;

    private int deleted = 0;

    private TimeSlot appointmentTime;

    public Appointment() {
    }

    public Appointment(long appointmentId, User patient, Clinic clinic, String status, String notes, int deleted, TimeSlot appointmentTime) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.clinic = clinic;
        this.status = status;
        this.notes = notes;
        this.deleted = deleted;
        this.appointmentTime = appointmentTime;
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public TimeSlot getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(TimeSlot appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", patient=" + patient +
                ", clinic=" + clinic +
                ", status='" + status + '\'' +
                ", notes='" + notes + '\'' +
                ", deleted=" + deleted +
                ", appointmentTime=" + appointmentTime +
                '}';
    }
}
