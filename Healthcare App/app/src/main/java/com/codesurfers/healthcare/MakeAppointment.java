package com.codesurfers.healthcare;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.res.Resources;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Locale;
import android.widget.DatePicker;

public class MakeAppointment extends AppCompatActivity {

    /**String[] clinics = {"Steve Biko Clinic", "Isolempilo Clinic", "Chiropractic Clinic", "M.L Sultan Clinic"};

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterClinics;*/
    TextView editTextDate ;
    ImageButton dateSelector;
    DatePickerDialog datePickerDialog;
    Spinner appointmentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment);
        setTitle("Schedule Appointment");

        dateSelector = findViewById(R.id.dateSelector);
        dateSelector.setOnClickListener(dateClickListener);
        editTextDate = findViewById(R.id.appointmentDate);
        appointmentTime= findViewById(R.id.appointmentTime);
        editTextDate.setFocusable(false);
        editTextDate.setClickable(false);

        //This is a list of options that will be used to feed the timeslots
        String[] options = {"TimeSlot 1", "TimeSlot 2", "Timeslot 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_gallery_item, options);
        appointmentTime.setAdapter(adapter);



        /**autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        adapterClinics = new ArrayAdapter<String>(this, R.layout.list_clinics, clinics);

        autoCompleteTextView.setAdapter(adapterClinics);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String clinic = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(MakeAppointment.this, "Selected item is" + clinic, Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    private DatePickerDialog.OnDateSetListener dateSetListener = (view, year, monthOfYear, dayOfMonth) -> {
        // Set the selected date to the text field
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        editTextDate.setText(sdf.format(calendar.getTime()));
    };

    private View.OnClickListener dateClickListener = v -> {
        // Show the date picker dialog
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    };

}




