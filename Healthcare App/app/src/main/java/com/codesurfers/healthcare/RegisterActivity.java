package com.codesurfers.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codesurfers.healthcare.constants.IClinicAPI;
import com.codesurfers.healthcare.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private IClinicAPI ClinicAPI;
    TextView username, password, studentNumber, firstName, lastName;
    EditText qualification;
    String URL = "http://localhost:7070/dut_healthcare_clinic/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /*
         * You need to set the baseUrl to your API
         * */

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.8.119:7070/dut_healthcare_clinic/api/")  // Set the baseUrl to your API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ClinicAPI = retrofit.create(IClinicAPI.class);

        // DECLARATION


        username = findViewById(R.id.emailAddressField);
        password = findViewById(R.id.passwordField);
        studentNumber = findViewById(R.id.studentNumber);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        qualification = findViewById(R.id.qualificationReg);

        Button createAccountButton = findViewById(R.id.createAccountBtn);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Clicked");
                Toast.makeText(RegisterActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                String studentNo = studentNumber.getText().toString();
                String firstNam = firstName.getText().toString();
                String lastNam = lastName.getText().toString();
                String usernam = username.getText().toString();
                String password1 = password.getText().toString();

                User user = new User(firstNam, lastNam, usernam,studentNo, password1);
                addUser(user);
            }
        });


        TextView longTxt = (TextView) findViewById(R.id.loginText);

        longTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fp=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(fp);
            }
        });


    }

    private void addUser(User user) {
        Call<User> call = ClinicAPI.addUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.code() == 201) {
                    System.out.println(response.code());
                    Toast.makeText(RegisterActivity.this, "Registered successfully" , Toast.LENGTH_SHORT).show();
                    Intent fp = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(fp);

                }else {
                    System.out.println(response.body());
                    Toast.makeText(RegisterActivity.this, "Failed to register user" , Toast.LENGTH_SHORT).show();

                }
                // Do something else if response successful
                // response.body() has the returned response data
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t.getMessage());
                System.out.println("FAILED TO REGISTER");
                Toast.makeText(RegisterActivity.this, "Failed to register user", Toast.LENGTH_SHORT).show();

            }
        });
    }




}