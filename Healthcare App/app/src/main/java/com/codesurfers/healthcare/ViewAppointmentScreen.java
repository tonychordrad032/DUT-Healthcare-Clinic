package com.codesurfers.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.codesurfers.healthcare.model.Appointment;
import com.codesurfers.healthcare.model.TimeSlot;

public class ViewAppointmentScreen extends AppCompatActivity {

    TextView date , time, reason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment_screen);
        setTitle("View Appointment");

        date = findViewById(R.id.date_text_view);
        time = findViewById(R.id.time_text_view);
        reason = findViewById(R.id.reason_text_view);

        Appointment appointment = getIntent().getParcelableExtra("appointment");
        date.setText(appointment.getRealDate());
        TimeSlot timeSlot = appointment.getAppointmentTime();
        System.out.println(timeSlot);
        //time.setText(timeSlot.getTime());
        reason.setText(appointment.getReason());
    }
}