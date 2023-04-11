package com.codesurfers.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class MakeAppointment extends AppCompatActivity {

    /**String[] clinics = {"Steve Biko Clinic", "Isolempilo Clinic", "Chiropractic Clinic", "M.L Sultan Clinic"};

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterClinics;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment);
        setTitle("Schedule Appointment");

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
}