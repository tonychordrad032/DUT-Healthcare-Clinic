package com.codesurfers.healthcare.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class TimeSlot implements Parcelable {

    private long timeSlotId;
    private String time;
    private AppointmentDay day;
    private boolean booked = false;
    private int deleted = 0;

    public TimeSlot() {
    }

    public TimeSlot(long timeSlotId, String time, boolean booked) {
        this.timeSlotId = timeSlotId;
        this.time = time;
        //this.day = day;
        this.booked = booked;
    }

    public TimeSlot(long timeSlotId, String time, AppointmentDay day, boolean booked, int deleted) {
        this.timeSlotId = timeSlotId;
        this.time = time;
        this.day = day;
        this.booked = booked;
        this.deleted = deleted;
    }

    protected TimeSlot(Parcel in) {
        timeSlotId = in.readLong();
        time = in.readString();
        booked = in.readByte() != 0;
        deleted = in.readInt();
    }

    public static final Creator<TimeSlot> CREATOR = new Creator<TimeSlot>() {
        @Override
        public TimeSlot createFromParcel(Parcel in) {
            return new TimeSlot(in);
        }

        @Override
        public TimeSlot[] newArray(int size) {
            return new TimeSlot[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(timeSlotId);
        dest.writeString(time);
        dest.writeByte((byte) (booked ? 1 : 0));
        dest.writeInt(deleted);
    }
}
