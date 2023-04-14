package com.codesurfers.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

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