package com.codesurfers.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codesurfers.healthcare.R;

public class AdminHomeActivity extends AppCompatActivity {

    CardView appointments;
    CardView timeslot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        appointments = (CardView) findViewById(R.id.admin_AppointmentTile);
        timeslot = (CardView) findViewById(R.id.admin_TimeSlotTile);

        appointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fp = new Intent(getApplicationContext(), AdminAppointmentScreen.class);
                startActivity(fp);
            }
        });

        timeslot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fp = new Intent(getApplicationContext(), TimeSlotScreen.class);
                startActivity(fp);
            }
        });


    }
}