package com.codesurfers.healthcare.model;

public class AppointmentDay {
    private  long appointmentDayId;
    private  String appointmentDayName;
    private boolean booked = false;

    private int deleted = 0;

    public AppointmentDay() {
    }

    public AppointmentDay(long appointmentDayId, String appointmentDayName, boolean booked, int deleted) {
        this.appointmentDayId = appointmentDayId;
        this.appointmentDayName = appointmentDayName;
        this.booked = booked;
        this.deleted = deleted;
    }

    public long getAppointmentDayId() {
        return appointmentDayId;
    }

    public void setAppointmentDayId(long appointmentDayId) {
        this.appointmentDayId = appointmentDayId;
    }

    public String getAppointmentDayName() {
        return appointmentDayName;
    }

    public void setAppointmentDayName(String appointmentDayName) {
        this.appointmentDayName = appointmentDayName;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "AppointmentDay{" +
                "appointmentDayId=" + appointmentDayId +
                ", appointmentDayName='" + appointmentDayName + '\'' +
                ", booked=" + booked +
                ", deleted=" + deleted +
                '}';
    }
}
