package com.codesurfers.healthcare;

import androidx.recyclerview.widget.GridLayoutManager;

public class ClinicModel {
    String clinicId;
    String clinicName;
    String clinicDescription;
    String latitude;
    String longitude;

    public ClinicModel(String clinicId, String clinicName, String clinicDescription, String latitude, String longitude) {
        this.clinicId = clinicId;
        this.clinicName = clinicName;
        this.clinicDescription = clinicDescription;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getClinicDescription() {
        return clinicDescription;
    }

    public void setClinicDescription(String clinicDescription) {
        this.clinicDescription = clinicDescription;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
