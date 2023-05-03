package com.codesurfers.healthcare;

import static com.codesurfers.healthcare.constants.Constants.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.codesurfers.healthcare.constants.IClinicAPI;
import com.codesurfers.healthcare.constants.RetrofitClient;
import com.codesurfers.healthcare.model.Appoint;
import com.codesurfers.healthcare.model.Appointment;
import com.codesurfers.healthcare.model.Clinic;
import com.codesurfers.healthcare.model.Feedback;
import com.codesurfers.healthcare.model.ResponseResult;
import com.codesurfers.healthcare.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    private IClinicAPI ClinicAPI;
    EditText username, password;


    ArrayList<Clinic> arrayList = new ArrayList<>();
    RecyclerView rv_1;
    String URL = "http://192.168.8.119:7070/dut_healthcare_clinic/api/";
    Button fetchClinicBtn;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;







    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
         * You need to set the baseUrl to your API
         * */




        // DECLARATION
        preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        editor = preferences.edit();

        TextView signUp = findViewById(R.id.signUpText);

        TextView resetPass = findViewById(R.id.resetPasswordText);
        username = findViewById(R.id.emailAddressField);
        password = findViewById(R.id.passwordField);
        TextView studentNumber = findViewById(R.id.studentNumber);
        TextView firstName = findViewById(R.id.firstName);
        TextView lastName = findViewById(R.id.lastName);



        Button loginBtn = (Button) findViewById(R.id.loginBtn);

        if (preferences.contains("userId")){
            Intent fp = new Intent(getApplicationContext(), AdminHomeActivity.class);
            startActivity(fp);
        }else {
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    User user = new User();
                    user.setUsername(username.getText().toString());
                    user.setPassword(password.getText().toString());
                    Intent adminHome = new Intent(getApplicationContext(), AdminHomeActivity.class);
                    startActivity(adminHome);
                    //startActivity(fp);
                    //loginUser(user);
                    //getUsers();
                    //System.out.println("BEFORE");
                    //getAppointmentByUserId(1);
                    //System.out.println("AFTER");
                }
            });

        }









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
                List<User> users = response.body();

                for (User u: users) {
                    Log.d("firstName", u.getFirstName());
                    Log.d("lastName", u.getLastName());
                    Log.d("userType", u.getUserType());
                }
                System.out.println(users.get(0).getFirstName());

                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

    /**private void addUser(HashMap<String,String> fields) {
        User user = new User(fields.get("firstName"),fields.get("lastName"),
                fields.get("dateOfBirth"),fields.get("username"),
                fields.get("studentNumber"),fields.get("password"),
                fields.get("mobile"),fields.get("qualification"));
        Call<User> call = ClinicAPI.addUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.code() == 201) {
                    System.out.println(response.code());
                    Toast.makeText(MainActivity.this, "Registered successfully" , Toast.LENGTH_SHORT).show();
                }else {
                    System.out.println(response.body());
                    Toast.makeText(MainActivity.this, "Failed to register user" , Toast.LENGTH_SHORT).show();
                    //Intent fp = new Intent(getApplicationContext(), HomeScreenActivity.class);
                    //startActivity(fp);
                }
                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }*/

    private void loginUser(User user) {
        RetrofitClient client = new RetrofitClient(BASE_URL);
        Call<ResponseResult> call = client.getClinicAPI().loginUser(user);
        call.enqueue(new Callback<ResponseResult>() {
            @Override
            public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {
                try {
                    if (response.code() == 404) {
                        System.out.println(response.code());
                        Toast.makeText(MainActivity.this, "Incorrect username or password" , Toast.LENGTH_SHORT).show();
                    }else {

                        JSONObject object = new JSONObject((Map) response.body().getResults());
                        String userType = (String) object.get("userType");
                        Double id = Double.valueOf(object.getDouble("userId"));
                        double id2 = id.doubleValue();
                        int int1 = (int) id2;
                        Integer userId = Integer.valueOf(int1);

                        editor.putInt("userId", userId);
                        editor.putString("firstName", (String) object.get("firstName"));
                        editor.putString("lastName", (String) object.get("lastName"));
                        editor.putString("studentNumber", (String) object.get("studentNumber"));
                        editor.putString("mobile", (String) object.get("mobile"));
                        editor.putString("qualification", (String) object.get("qualification"));
                        editor.commit();
                        System.out.println(response);
                        System.out.println(object.get("userId"));
                        System.out.println(object.get("firstName"));
                        System.out.println(object.get("lastName"));
                        System.out.println(object.get("studentNumber"));
                        System.out.println(object.get("mobile"));
                        System.out.println(object.get("qualification"));
                        System.out.println("MY USERTYPE");
                        System.out.println(response.code());
                        System.out.println(response.body());
                        System.out.println(userType);
                        if (userType.equals("student")){
                            System.out.println("TRUE");
                            Toast.makeText(MainActivity.this, "Login Successfully" , Toast.LENGTH_SHORT).show();
                            //Intent fp = new Intent(getApplicationContext(), HomeScreenActivity.class);
                            Intent fp = new Intent(getApplicationContext(), AdminHomeActivity.class);
                            startActivity(fp);
                        }else if (userType.equals("admin")){
                            System.out.println("FALSE");
                            Toast.makeText(MainActivity.this, "Login Successfully" , Toast.LENGTH_SHORT).show();
                            Intent fp = new Intent(getApplicationContext(), AdminHomeActivity.class);
                            startActivity(fp);
                        }

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }




                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<ResponseResult> call, Throwable t) {
                System.out.println("FAILED");
                System.out.println(t.getMessage());
                t.getMessage();
                Toast.makeText(MainActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**private void updateUser(long userId, HashMap<String,String> fields){
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
    }*/

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

    /**private void makeAppointment(HashMap<String,String> fields, LocalDateTime appointmentDate, LocalDateTime appointmentTime) {
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
    }*/

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

    /**private void getAppointmentByUserId(long userId) {
        Call<ResponseResult> call = ClinicAPI.getAppointmentByUserId(userId, myList);

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

                List<Appointment> myAppointmentList = (List<Appointment>) response.body().getResults();
                System.out.println(response.body().getResponseMessage());
                JSONArray object = new JSONArray(myAppointmentList);


                for (int i = 0; i < object.length(); i++) {
                    try {
                        System.out.println(object.getJSONObject(i).get("status"));
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
    }*/

    private void getAppointmentByDay(String day) {
        RetrofitClient client = new RetrofitClient(BASE_URL);
        Call<ResponseResult> call = client.getClinicAPI().getAppointmentByDay(day);

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

                List<Appointment> myAppointmentList = (List<Appointment>) response.body().getResults();
                System.out.println(response.body().getResponseMessage());
                JSONArray object = new JSONArray(myAppointmentList);


            }

            @Override
            public void onFailure(Call<ResponseResult> call, Throwable t) {
                System.out.println("FAILURE");

            }
        });
    }



}