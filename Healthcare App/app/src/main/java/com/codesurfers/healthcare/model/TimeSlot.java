package com.codesurfers.healthcare.model;

public class TimeSlot {

    private long timeSlotId;
    private String time;
    private AppointmentDay day;
    private boolean booked = false;
    private int deleted = 0;

    public TimeSlot() {
    }

    public TimeSlot(long timeSlotId, String time, AppointmentDay day, boolean booked, int deleted) {
        this.timeSlotId = timeSlotId;
        this.time = time;
        this.day = day;
        this.booked = booked;
        this.deleted = deleted;
    }

    public long getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(long timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public AppointmentDay getDay() {
        return day;
    }

    public void setDay(AppointmentDay day) {
        this.day = day;
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
        return "TimeSlot{" +
                "timeSlotId=" + timeSlotId +
                ", time='" + time + '\'' +
                ", day=" + day +
                ", booked=" + booked +
                ", deleted=" + deleted +
                '}';
    }
}
