package com.codesurfers.healthcare;

import static com.codesurfers.healthcare.constants.Constants.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.codesurfers.healthcare.constants.RetrofitClient;
import com.codesurfers.healthcare.model.Appointment;
import com.codesurfers.healthcare.model.ResponseResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminAppointmentScreen extends AppCompatActivity {

    RecyclerView appointments;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_appointment_screen);
        setTitle("Appointments");

        String[] sampleAppointments = {"HIV TEst","TEst", "Scan"};
        ArrayAdapter sampleAppointmentArray = new ArrayAdapter(this, android.R.layout.simple_list_item_1,sampleAppointments);

        String[] spinnerValues = {"Monday, Tuesday, Wednesday, Thursday, Friday"};
       Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerValues);
        mySpinner.setAdapter(spinnerAdapter);


        appointments = findViewById(R.id.recyclerView);

        RetrofitClient client = new RetrofitClient(BASE_URL);

            Call<ResponseResult> call = client.getClinicAPI().getAppointmentByDay("Monday");
            call.enqueue(new Callback<ResponseResult>() {
                @Override
                public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {

                    if(response.isSuccessful()){

                        List<Appointment> appointmentList = (List<Appointment>) response.body().getResults();


                        //Set the adapter on the recycler view
                        System.out.println("In between");
                        appointments.setAdapter(new AdminAppointmentAdapter(getApplicationContext(),appointmentList));

                        System.out.println("Sucess");
                    }else{
                        Toast.makeText(AdminAppointmentScreen.this, "An error occured" , Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<ResponseResult> call, Throwable t) {

                }
            });



    }


}