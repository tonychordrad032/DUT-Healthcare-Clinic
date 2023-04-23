package com.codesurfers.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TimeSlotsActivity extends AppCompatActivity {

    // Define the clinic hours
    private static final int START_HOUR = 8;
    private static final int END_HOUR = 16;
    private static final int INTERVAL = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_slots);

        // Find the TextView where we will display the time slots
        TextView timeSlotsTextView = findViewById(R.id.time_slots_text_view);

        // Build the time slots string
        StringBuilder timeSlotsBuilder = new StringBuilder();
        for (int hour = START_HOUR; hour < END_HOUR; hour++) {
            for (int minute = 0; minute < 60; minute += INTERVAL) {
                String startTime = String.format("%02d:%02d", hour, minute);
                String endTime = String.format("%02d:%02d", hour, minute + INTERVAL);
                String timeSlot = String.format("%s - %s\n", startTime, endTime);
                timeSlotsBuilder.append(timeSlot);
            }
        }

        // Set the time slots string to the TextView
        timeSlotsTextView.setText(timeSlotsBuilder.toString());
    }
}
