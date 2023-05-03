package com.codesurfers.healthcare;

import static com.codesurfers.healthcare.constants.Constants.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codesurfers.healthcare.constants.RetrofitClient;
import com.codesurfers.healthcare.model.Appoint;
import com.codesurfers.healthcare.model.Appointment;
import com.codesurfers.healthcare.model.ResponseResult;
import com.codesurfers.healthcare.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminViewAppointmentScreen extends AppCompatActivity {

    TextView studentNumber, qualification, firstName, lastName, date, time, reason;
    EditText comment;

    Button closeBooking;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_appointment_screen);
        setTitle("View Appointment");

        studentNumber = findViewById(R.id.studentNumberTxtAdmin);
        qualification = findViewById(R.id.qualificationTxtAdmin);
        firstName = findViewById(R.id.firstNameTxtAdmin);
        lastName = findViewById(R.id.lastNameTxtAdmin);
        date = findViewById(R.id.date_text_view_admin);
        time = findViewById(R.id.time_text_view_admin);
        reason = findViewById(R.id.reason_text_view_admin);
        comment = findViewById(R.id.commentAdmin);
        closeBooking = findViewById(R.id.close_appointment_btn);

        Appointment appointment = getIntent().getParcelableExtra("appointment");
        User user = getIntent().getParcelableExtra("user");
        studentNumber.setText(user.getStudentNumber());
        qualification.setText(user.getQualification());
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        date.setText(appointment.getRealDate());
        //time.setText(appointment.getAppointmentTime().getTime());
        reason.setText(appointment.getReason());

        closeBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointment.setStatus("Closed");
                appointment.setNotes(String.valueOf(comment.getText()));
                updateAppointment(appointment);
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
                    Toast.makeText(AdminViewAppointmentScreen.this, "Error while saving appointment", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (response.code() == 200){
                    Toast.makeText(AdminViewAppointmentScreen.this, "Appointment was successfully closed", Toast.LENGTH_SHORT).show();
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