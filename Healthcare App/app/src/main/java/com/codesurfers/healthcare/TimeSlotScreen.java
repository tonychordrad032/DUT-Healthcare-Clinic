package com.codesurfers.healthcare;

import static com.codesurfers.healthcare.constants.Constants.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.codesurfers.healthcare.R;
import com.codesurfers.healthcare.constants.RetrofitClient;
import com.codesurfers.healthcare.model.Appointment;
import com.codesurfers.healthcare.model.ResponseResult;

import org.json.JSONArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeSlotScreen extends AppCompatActivity {
     CardView refreshTimeSlot, setupTimeSlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        refreshTimeSlot = findViewById(R.id.refreshTimeSlots);
        setupTimeSlot = findViewById(R.id.setupTimeSlot);


        //OnClick of refreshTimeSlot refresh time slots
        refreshTimeSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshTimeSlots();
            }
        });

        //OnClick of setupTime slot go to setSup time slot page

        setupTimeSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fp = new Intent(TimeSlotScreen.this, adminSetupTimeSlot.class );
                startActivity(fp);
            }
        });

    }


    private void refreshTimeSlots (){

        RetrofitClient client = new RetrofitClient(BASE_URL);
        Call<ResponseResult> call = client.getClinicAPI().refreshTimeSlots();

        call.enqueue(new Callback<ResponseResult>() {
            @Override
            public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {

                System.out.println(response.code());
                if(!response.isSuccessful()){
                    Toast.makeText(TimeSlotScreen.this, "Time slots not refreshed", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(response.code()==200){

                    Toast.makeText(TimeSlotScreen.this, "Timeslots refreshed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseResult> call, Throwable t) {
                Toast.makeText(TimeSlotScreen.this, "An error has occured", Toast.LENGTH_SHORT).show();
            }
        });


    }
}