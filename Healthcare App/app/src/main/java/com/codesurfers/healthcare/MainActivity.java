package com.codesurfers.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    /**String url = "http://localhost:9092/dut_healthcare_clinic/api/user/login";
    EditText username = (EditText) findViewById(R.id.emailAddressField);
    EditText password = findViewById(R.id.passwordField);
    void loginFunction(){
        String username1 = username.getText().toString();
        String password1 = password.getText().toString();

        JSONObject logObj = new JSONObject();
        try {
            logObj.put("username", username1);
            logObj.put("password", password1);
        }catch (JSONException e){
            e.printStackTrace();
        }
        System.out.println(logObj);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, logObj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("INSIDE");
                Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                Intent fp=new Intent(getApplicationContext(),HomeScreenActivity.class);
                startActivity(fp);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Incorrect password or username", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
        System.out.println("LAST LINE");
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView signUp = (TextView) findViewById(R.id.signUpText);
        TextView resetPass = (TextView) findViewById(R.id.resetPasswordText);



        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //loginFunction();
                Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                Intent fp=new Intent(getApplicationContext(),HomeScreenActivity.class);
                startActivity(fp);
            }
        });



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fp=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(fp);
            }
        });

        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fp=new Intent(getApplicationContext(),ResetPasswordActivity.class);
                startActivity(fp);
            }
        });
    }

    public void login(){

    }
}