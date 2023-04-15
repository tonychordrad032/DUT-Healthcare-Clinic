package com.codesurfers.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.codesurfers.healthcare.model.Appointment;
import com.codesurfers.healthcare.model.Clinic;
import com.codesurfers.healthcare.model.Feedback;
import com.codesurfers.healthcare.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    private IClinicAPI ClinicAPI;
    TextView username, password;


    ArrayList<Clinic> arrayList = new ArrayList<>();
    Adapter.RecyclerViewClick listener;
    RecyclerView rv_1;
    String URL = "http://192.168.8.119:9092/dut_healthcare_clinic/api/clinic";
    Button fetchClinicBtn;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
         * You need to set the baseUrl to your API
         * */

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.8.119:9092/dut_healthcare_clinic/api/")  // Set the baseUrl to your API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ClinicAPI = retrofit.create(IClinicAPI.class);

        // DECLARATION

        TextView signUp = findViewById(R.id.signUpText);
        TextView resetPass = findViewById(R.id.resetPasswordText);
        username = findViewById(R.id.emailAddressField);
        password = findViewById(R.id.passwordField);


        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username1 = username.getText().toString();
                String password1 = password.getText().toString();
                loginUser(username1,password1);
            }
        });







        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fp = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(fp);
            }
        });

        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fp = new Intent(getApplicationContext(), ResetPasswordActivity.class);
                startActivity(fp);
            }
        });
    }
    
    // API functions

    /*
     * "HashMap<String, String> fields"
     *
     * Will be used to set "String" type attribute depending on the Class
     * The HashMap is set as <Key, Value>
     * Key = the attribute
     * Value = the value you want to set to that attribute
     *
     * Create the HashMap like this;
     * HashMap<String, String> fields = new HashMap<String, String>();
     *
     * */

    // User
    private void getUser(long userId) {
        Call<User> call = ClinicAPI.getUser(userId);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (!response.isSuccessful()) {
                    // Do something
                    return;
                }

                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void getUsers() {
        Call<List<User>> call = ClinicAPI.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if (!response.isSuccessful()) {
                    // Do something
                    return;
                }

                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    private void addUser(HashMap<String,String> fields) {
        User user = new User(fields.get("firstName"),fields.get("lastName"),
                fields.get("dateOfBirth"),fields.get("username"),
                fields.get("studentNumber"),fields.get("password"),
                fields.get("mobile"),fields.get("qualification"));
        Call<User> call = ClinicAPI.addUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (!response.isSuccessful()) {
                    // Do something
                    return;
                }

                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void loginUser(String username, String password) {

        User user = new User(username, password);
        Call<User> call = ClinicAPI.loginUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 404) {
                    System.out.println(response.code());
                    Toast.makeText(MainActivity.this, "Incorrect username or password" , Toast.LENGTH_SHORT).show();
                }else {
                    System.out.println(response.body());
                    Toast.makeText(MainActivity.this, "Login Successfully" , Toast.LENGTH_SHORT).show();
                    Intent fp = new Intent(getApplicationContext(), HomeScreenActivity.class);
                    startActivity(fp);
                }



                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("FAILED");
                t.getMessage();
                Toast.makeText(MainActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUser(long userId, HashMap<String,String> fields){
        User user = new User(fields.get("firstName"),fields.get("lastName"),
                fields.get("dateOfBirth"),fields.get("username"),
                fields.get("studentNumber"),fields.get("password"),
                fields.get("mobile"),fields.get("qualification"));
        Call<User> call = ClinicAPI.updateUser(userId,user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (!response.isSuccessful()) {
                    // Do something
                    return;
                }

                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void deleteUser(long userId){
        Call<Void> call = ClinicAPI.deleteUser(userId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (!response.isSuccessful()) {
                    // Do something
                    return;
                }

                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    // Clinic
    private void getClinic(long clinicId) {
        Call<Clinic> call = ClinicAPI.getClinic(clinicId);

        call.enqueue(new Callback<Clinic>() {
            @Override
            public void onResponse(Call<Clinic> call, Response<Clinic> response) {

                if (!response.isSuccessful()) {
                    // Do something
                    return;
                }

                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<Clinic> call, Throwable t) {

            }
        });
    }

    private void getClinics() {

        Call<List<Clinic>> call = ClinicAPI.getClinics();

        call.enqueue(new Callback<List<Clinic>>() {
            @Override
            public void onResponse(Call<List<Clinic>> call, Response<List<Clinic>> response) {

                if (response.code() == 200) {
                    List<Clinic> tempList = response.body();
                    for (int i = 0; i < tempList.size(); i++) {
                        arrayList.add(new Clinic(tempList.get(1).getClinicName(), tempList.get(i).getClinicDescription(), tempList.get(i).getClinicCampus(), tempList.get(i).getLatitude(), tempList.get(i).getLongitude()));
                    }

                    System.out.println("SUCCESSFULLY");
                    System.out.println(arrayList.size());

                }else {
                    System.out.println("Error Occurred");
                }

            }
            @Override
            public void onFailure(Call<List<Clinic>> call, Throwable t) {
                t.printStackTrace();

            }
        });

    }

    private void addClinic(HashMap<String,String> fields) {
        Clinic clinic = new Clinic(fields.get("clinicName"),fields.get("clinicDescription"),
                fields.get("clinicCampus"),fields.get("latitude"),
                fields.get("longitude"));
        Call<Clinic> call = ClinicAPI.addClinic(clinic);

        call.enqueue(new Callback<Clinic>() {
            @Override
            public void onResponse(Call<Clinic> call, Response<Clinic> response) {

                if (!response.isSuccessful()) {
                    // Do something
                    return;
                }

                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<Clinic> call, Throwable t) {

            }
        });
    }

    private void updateClinic(long clinicId, HashMap<String,String> fields){
        Clinic clinic = new Clinic(fields.get("clinicName"),fields.get("clinicDescription"),
                fields.get("clinicCampus"),fields.get("latitude"),
                fields.get("longitude"));
        Call<Clinic> call = ClinicAPI.updateClinic(clinicId,clinic);

        call.enqueue(new Callback<Clinic>() {
            @Override
            public void onResponse(Call<Clinic> call, Response<Clinic> response) {

                if (!response.isSuccessful()) {
                    // Do something
                    return;
                }

                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<Clinic> call, Throwable t) {

            }
        });
    }

    private void deleteClinic(long clinicId){
        Call<Void> call = ClinicAPI.deleteClinic(clinicId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (!response.isSuccessful()) {
                    // Do something
                    return;
                }

                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    // Feedback
    private void getFeedback(long feedbackId) {
        Call<Feedback> call = ClinicAPI.getFeedback(feedbackId);

        call.enqueue(new Callback<Feedback>() {
            @Override
            public void onResponse(Call<Feedback> call, Response<Feedback> response) {

                if (!response.isSuccessful()) {
                    // Do something
                    return;
                }

                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<Feedback> call, Throwable t) {

            }
        });
    }

    private void getFeedbacks() {
        Call<List<Feedback>> call = ClinicAPI.getFeedbacks();

        call.enqueue(new Callback<List<Feedback>>() {
            @Override
            public void onResponse(Call<List<Feedback>> call, Response<List<Feedback>> response) {

                if (!response.isSuccessful()) {
                    // Do something
                    return;
                }

                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<List<Feedback>> call, Throwable t) {

            }
        });
    }

    private void addFeedback(HashMap<String,String> fields) {
        Feedback feedback = new Feedback(fields.get("name"),fields.get("surname"),
                fields.get("feedBackType"),fields.get("message"));
        Call<Feedback> call = ClinicAPI.addFeedback(feedback);

        call.enqueue(new Callback<Feedback>() {
            @Override
            public void onResponse(Call<Feedback> call, Response<Feedback> response) {

                if (!response.isSuccessful()) {
                    // Do something
                    return;
                }

                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<Feedback> call, Throwable t) {

            }
        });
    }

    private void updateFeedback(long feedbackId, HashMap<String,String> fields){
        Feedback feedback = new Feedback(fields.get("name"),fields.get("surname"),
                fields.get("feedBackType"),fields.get("message"));
        Call<Feedback> call = ClinicAPI.addFeedback(feedback);

        call.enqueue(new Callback<Feedback>() {
            @Override
            public void onResponse(Call<Feedback> call, Response<Feedback> response) {

                if (!response.isSuccessful()) {
                    // Do something
                    return;
                }

                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<Feedback> call, Throwable t) {

            }
        });
    }

    private void deleteFeedback(long feedbackId){
        Call<Void> call = ClinicAPI.deleteFeedback(feedbackId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (!response.isSuccessful()) {
                    // Do something
                    return;
                }

                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    // Appointment
    private void getAppointment(long appointmentId) {
        Call<Appointment> call = ClinicAPI.getAppointment(appointmentId);

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

    private void getAppointments() {
        Call<List<Appointment>> call = ClinicAPI.getAppointments();

        call.enqueue(new Callback<List<Appointment>>() {
            @Override
            public void onResponse(Call<List<Appointment>> call, Response<List<Appointment>> response) {

                if (!response.isSuccessful()) {
                    // Do something
                    return;
                }

                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<List<Appointment>> call, Throwable t) {

            }
        });
    }

    private void makeAppointment(HashMap<String,String> fields, LocalDateTime appointmentDate, LocalDateTime appointmentTime) {
        Appointment appointment = new Appointment(fields.get("studentNumber"),fields.get("status"),
                fields.get("qualification"),appointmentDate,
                appointmentTime);
        Call<Appointment> call = ClinicAPI.makeAppointment(appointment);

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

    private void updateAppointment(long appointmentId, HashMap<String,String> fields, LocalDateTime appointmentDate, LocalDateTime appointmentTime){
        Appointment appointment = new Appointment(fields.get("studentNumber"),fields.get("status"),
                fields.get("qualification"),appointmentDate,
                appointmentTime);
        Call<Appointment> call = ClinicAPI.updateAppointment(appointmentId,appointment);

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

    private void deleteAppointment(long appointmentId){
        Call<Void> call = ClinicAPI.deleteAppointment(appointmentId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (!response.isSuccessful()) {
                    // Do something
                    return;
                }

                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


}