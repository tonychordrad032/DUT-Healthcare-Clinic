package com.codesurfers.healthcare;

import static com.codesurfers.healthcare.constants.Constants.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.codesurfers.healthcare.constants.RetrofitClient;
import com.codesurfers.healthcare.model.Appointment;
import com.codesurfers.healthcare.model.Clinic;
import com.codesurfers.healthcare.model.Feedback;
import com.codesurfers.healthcare.model.ResponseResult;
import com.codesurfers.healthcare.model.TimeSlot;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAppointmentScreen extends AppCompatActivity {

    TextView date , time, reason;
    Button canselBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment_screen);
        setTitle("View Appointment");

        date = findViewById(R.id.date_text_view);
        time = findViewById(R.id.time_text_view);
        reason = findViewById(R.id.reason_text_view);
        canselBtn =findViewById(R.id.cancel_appointment_btn);

        Appointment appointment = getIntent().getParcelableExtra("appointment");
        TimeSlot timeSlot = getIntent().getParcelableExtra("timeSlot");
        date.setText(appointment.getRealDate());
        System.out.println(timeSlot);
        time.setText(timeSlot.getTime());
        reason.setText(appointment.getReason());

        canselBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timeSlot.setBooked(false);
                updateTimeSlot(timeSlot);

                reason.setText(appointment.getReasonForCancel());
                appointment.setStatus("Cancelled");

                updateAppointment(appointment);
                Intent fp = new Intent(getApplicationContext(), AppointmentScreen.class);
                startActivity(fp);
            }
        });
    }

    private void updateTimeSlot(TimeSlot timeSlot){
        System.out.println("START UPDATING TIME");
        RetrofitClient client = new RetrofitClient(BASE_URL);
        Call<ResponseResult> call = client.getClinicAPI().updateTimeSlot(timeSlot);

        call.enqueue(new Callback<ResponseResult>() {
            @Override
            public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {
                System.out.println("MY STATUS CODE => "+response.code());
                System.out.println("MY STATUS CODE => "+response);
                if (!response.isSuccessful()) {
                    Toast.makeText(ViewAppointmentScreen.this, " ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.code() == 200){
                    System.out.println("Time slot was reserted to false");
                }
            }

            @Override
            public void onFailure(Call<ResponseResult> call, Throwable t) {
                Toast.makeText(ViewAppointmentScreen.this, " ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateAppointment(Appointment appointment){
        RetrofitClient client = new RetrofitClient(BASE_URL);
        Call<ResponseResult> call = client.getClinicAPI().updateAppointment(appointment);

        call.enqueue(new Callback<ResponseResult>() {
            @Override
            public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {

                System.out.println("MY STATUS CODE => "+response.code());
                System.out.println(response);
                if (!response.isSuccessful()) {
                    Toast.makeText(ViewAppointmentScreen.this, "Error while saving appointment", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.code() == 200){
                    Toast.makeText(ViewAppointmentScreen.this, "Appointment was successfully closed", Toast.LENGTH_SHORT).show();
                    Intent fp = new Intent(getApplicationContext(), AdminAppointmentScreen.class);
                    startActivity(fp);
                }


            }

            @Override
            public void onFailure(Call<ResponseResult> call, Throwable t) {

            }
        });
    }
}