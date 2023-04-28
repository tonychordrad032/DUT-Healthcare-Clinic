package com.codesurfers.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.codesurfers.healthcare.R;

public class TimeSlotScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_slot_screen);
        setTitle("Time Slot");
    }
}