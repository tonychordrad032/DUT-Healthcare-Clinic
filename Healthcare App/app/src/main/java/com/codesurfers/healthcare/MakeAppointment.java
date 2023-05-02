package com.codesurfers.healthcare;



import static com.codesurfers.healthcare.constants.Constants.BASE_URL;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
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

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import android.widget.DatePicker;

import com.codesurfers.healthcare.constants.RetrofitClient;
import com.codesurfers.healthcare.model.Appoint;
import com.codesurfers.healthcare.model.Appointment;
import com.codesurfers.healthcare.model.Clinic;
import com.codesurfers.healthcare.model.ResponseResult;
import com.codesurfers.healthcare.model.TimeSlot;
import com.codesurfers.healthcare.model.User;

import org.json.JSONArray;
import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakeAppointment extends AppCompatActivity {

    /**String[] clinics = {"Steve Biko Clinic", "Isolempilo Clinic", "Chiropractic Clinic", "M.L Sultan Clinic"};

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterClinics;*/
    SharedPreferences sp;
    TextView editTextDate, studentNo, firstName, lastName, qualification;
    TextView reason, appointmentDate,time, note;
    ImageButton dateSelector;
    DatePickerDialog datePickerDialog;
    Spinner appointmentTime;
    List<TimeSlot> timeSlots = new ArrayList<>();
    List<String> times = new ArrayList<>();
    String dayOfTheWeek;

    long userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment);
        setTitle("Schedule Appointment");

        studentNo = findViewById(R.id.studentNumberTxt);
        firstName = findViewById(R.id.firstNameTxt);
        lastName = findViewById(R.id.lastNameTxt);
        qualification = findViewById(R.id.qualificationTxt);
        reason = findViewById(R.id.reason);
        appointmentDate = findViewById(R.id.appointmentDate);
        //time = findViewById(R.id.timeTextView);
        note = findViewById(R.id.notes);




        sp = getApplicationContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String studentNumber = sp.getString("studentNumber", "");
        String name = sp.getString("firstName", "");
        String surname = sp.getString("lastName", "");
        String qual = sp.getString("qualification", "");
        Integer id = sp.getInt("userId", 0);

        userId = id.longValue();
        studentNo.setText(studentNumber);
        firstName.setText(name);
        lastName.setText(surname);
        qualification.setText(qual);
        System.out.println("MY USER ID => " +userId);



        dateSelector = findViewById(R.id.dateSelector);
        dateSelector.setOnClickListener(dateClickListener);
        editTextDate = findViewById(R.id.appointmentDate);
        appointmentTime= findViewById(R.id.appointmentTime);
        editTextDate.setFocusable(false);
        editTextDate.setClickable(false);


        //This is a list of options that will be used to feed the timeslots

        //ArrayAdapter<TimeSlot> adapter = new ArrayAdapter<TimeSlot>(this, android.R.layout.simple_gallery_item, timeSlots);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_gallery_item, times);
        appointmentTime.setAdapter(adapter);
        /**editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });*/



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
        appointmentTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private DatePickerDialog.OnDateSetListener dateSetListener = (view, year, monthOfYear, dayOfMonth) -> {
        // Set the selected date to the text field
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        System.out.println("MY DAY OF THE WEEK");
        if (calendar.get(Calendar.DAY_OF_WEEK) == 1){
            dayOfTheWeek = "Sunday";
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == 2) {
            dayOfTheWeek = "Monday";
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == 3) {
            dayOfTheWeek = "Tuesday";
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == 4) {
            dayOfTheWeek = "Wednesday";
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
            dayOfTheWeek = "Thursday";
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == 6) {
            dayOfTheWeek = "Friday";
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == 7) {
            dayOfTheWeek = "Saturday";
        }
        System.out.println(dayOfTheWeek);
        editTextDate.setText(sdf.format(calendar.getTime()) + ", "+dayOfTheWeek);
        getTimeSlotByDay(dayOfTheWeek);

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

    private void getTimeSlotByDay(String day) {
        RetrofitClient client = new RetrofitClient(BASE_URL);
        System.out.println("Start");
        Call<ResponseResult> call = client.getClinicAPI().getTimeSlotByDay(day);

        call.enqueue(new Callback<ResponseResult>() {
            @Override
            public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {
                System.out.println("BEGIN");
                if (!response.isSuccessful()) {
                    System.out.println("NOT SUCCESS");
                    System.out.println(response.code());
                    System.out.println(response);
                    // Do something
                    return;
                }

                List<TimeSlot> timeSlotList = (List<TimeSlot>) response.body().getResults();
                System.out.println(response.body().getResponseMessage());
                JSONArray object = new JSONArray(timeSlotList);
                times.clear();
                for (int i = 0; i < object.length(); i++) {
                    try {
                        System.out.println("My STATUS");
                        System.out.println(object.getJSONObject(i).get("time"));
                        times.add((String) object.getJSONObject(i).get("time"));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
                System.out.println("METHPDDDDDD");

            }

            @Override
            public void onFailure(Call<ResponseResult> call, Throwable t) {
                System.out.println("FAILURE");

            }
        });
    }

    private void makeAppointment(Appointment appointment) {
        RetrofitClient client = new RetrofitClient(BASE_URL);
        User user = new User();
        Clinic clinic = new Clinic();
        TimeSlot timeSlot = new TimeSlot();
        user.setUserId(userId);
        clinic.setClinicId(1);
        timeSlot.setTimeSlotId(1);
        Call<Appointment> call = client.getClinicAPI().makeAppointment(appointment);

        call.enqueue(new Callback<Appointment>() {
            @Override
            public void onResponse(Call<Appointment> call, Response<Appointment> response) {

                if (!response.isSuccessful()) {
                    // Do something
                    return;
                }

                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<Appointment> call, Throwable t) {

            }
        });
    }

}




