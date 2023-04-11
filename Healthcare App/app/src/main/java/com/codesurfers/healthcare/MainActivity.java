package com.codesurfers.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username = (EditText) findViewById(R.id.emailAddressField);
        TextView signUp = (TextView) findViewById(R.id.signUpText);
        TextView resetPass = (TextView) findViewById(R.id.resetPasswordText);



        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username1 = username.getText().toString();
                Toast.makeText(MainActivity.this, "Your Username is " + username1, Toast.LENGTH_SHORT).show();
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
}