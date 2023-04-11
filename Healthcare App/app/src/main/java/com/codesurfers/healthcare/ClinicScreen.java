package com.codesurfers.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ClinicScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_screen);
        setTitle("Clinics");
    }
}