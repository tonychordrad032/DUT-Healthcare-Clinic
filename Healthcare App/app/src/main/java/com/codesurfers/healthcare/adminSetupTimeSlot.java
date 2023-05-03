package com.codesurfers.healthcare;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class adminSetupTimeSlot extends AppCompatActivity {

    Spinner daySpinner;
    TextInputEditText time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_setup_time_slots);

        daySpinner = findViewById(R.id.selectDaySpinner);
        time = findViewById(R.id.timeInput);




    }
}
