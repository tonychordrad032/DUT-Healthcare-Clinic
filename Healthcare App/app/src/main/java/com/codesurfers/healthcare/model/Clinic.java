package com.codesurfers.healthcare.model;

public class Clinic {
    private long clinicId;
    private  String clinicName;
    private  String clinicDescription;
    private  String clinicCampus;
    private String latitude;
    private String longitude;

    public Clinic(String clinicName, String clinicDescription, String clinicCampus, String latitude, String longitude) {
        this.clinicName = clinicName;
        this.clinicDescription = clinicDescription;
        this.clinicCampus = clinicCampus;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public void setClinicDescription(String clinicDescription) {
        this.clinicDescription = clinicDescription;
    }

    public void setClinicCampus(String clinicCampus) {
        this.clinicCampus = clinicCampus;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public long getClinicId() {
        return clinicId;
    }

    public String getClinicName() {
        return clinicName;
    }

    public String getClinicDescription() {
        return clinicDescription;
    }

    public String getClinicCampus() {
        return clinicCampus;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}