package com.codesurfers.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MedicalRecordScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record_screen);
        setTitle("Medical Records");
    }
}