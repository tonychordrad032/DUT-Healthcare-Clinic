package com.codesurfers.healthcare;

import static com.codesurfers.healthcare.constants.Constants.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.codesurfers.healthcare.constants.RetrofitClient;
import com.codesurfers.healthcare.model.Appoint;
import com.codesurfers.healthcare.model.Appointment;
import com.codesurfers.healthcare.model.ResponseResult;
import com.codesurfers.healthcare.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminAppointmentScreen extends AppCompatActivity {

    //RecyclerView appointments;
    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

    ArrayList<Appoint> appointmentList = new ArrayList<>();
    ArrayList<JSONObject> appointments = new ArrayList<org.json.JSONObject>();
    ListView listView;

    ArrayAdapter<String> adapterItems;
    AutoCompleteTextView autoCompleteTextView;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_appointment_screen);
        setTitle("Appointments");

        listView = findViewById(R.id.listView);



        autoCompleteTextView = findViewById(R.id.auto_complete_txt_admin);


        adapterItems = new ArrayAdapter<String>(this,R.layout.time_list, days);
        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                //timeSlotId = timeSlots.get(i).getTimeSlotId();
                // position = i;
                System.out.println("MY ITEM =>" + item);
                //System.out.println("POSITION => " + timeSlots.get(i));
                //System.out.println("TIME SLOT ID => " + timeSlotId);
                getAppointmentByDay(item);

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ObjectMapper m = new ObjectMapper();
                String jsonObject = String.valueOf(appointments.get(i));
                JsonParser parser = new JsonParser();
                JsonElement mJson = parser.parse(jsonObject);
                Gson gson = new Gson();
                Appointment appointment = gson.fromJson(mJson, Appointment.class);
                String timeSlot = appointment.getAppointmentTime().getTime();
                User user = appointment.getPatient();
                System.out.println(timeSlot);
                System.out.println(appointment);
                Toast.makeText(getApplicationContext(), "Clicked object" + adapterView.getItemIdAtPosition(i), Toast.LENGTH_SHORT).show();
                Intent fp = new Intent(getApplicationContext(), AdminViewAppointmentScreen.class);
                fp.putExtra("appointment", appointment);
                fp.putExtra("user", user);
                startActivity(fp);
            }
        });

        /*String[] sampleAppointments = {"HIV TEst","TEst", "Scan"};
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
            });*/

    }

    private void getAppointmentByDay(String day) {
        RetrofitClient client = new RetrofitClient(BASE_URL);
        System.out.println("Start");
        Call<ResponseResult> call = client.getClinicAPI().getAppointmentByDay(day);

        System.out.println("AFTER 1st Call");

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
                        System.out.println("My STATUS");
                        System.out.println(object.getJSONObject(i).get("status"));
                        System.out.println(object.getJSONObject(i));
                        if (object.getJSONObject(i).get("status").toString().equals("Open")){
                            System.out.println("INSIDE IF");
                            appointmentList.add(new Appoint(object.getJSONObject(i).getString("realDate"), object.getJSONObject(i).getJSONObject("appointmentTime").getString("time"), (String) object.getJSONObject(i).get("status")));
                            appointments.add(object.getJSONObject(i));
                            System.out.println(appointments);
                            System.out.println(appointmentList);
                            System.out.println(appointments.size());
                            System.out.println(appointmentList.size());

                        }

                        ListAdapter adapter = new ListAdapter(getApplicationContext(), R.layout.activity_appointment_list_view, appointmentList);
                        listView.setAdapter(adapter);



                        //If there are no items in the adapter then show the no appointments text else show the items
                        /**if (adapter==null || appointmentList.isEmpty()){
                            noAppointment.setVisibility(View.VISIBLE);


                        }else{

                            //date.setText("appointmentList.get(i).getDate()");
                            //time.setText(appointmentList.get(i).getTime());
                            //status.setText(appointmentList.get(i).getStatus());
                            //System.out.println(appointmentList.get(i).getDate());
                            //System.out.println(date);
                            //System.out.println(time);
                            //System.out.println(status);
                            //listView.setAdapter(adapter);
                            //noAppointment.setVisibility(View.GONE);
                        }*/
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
    }


}