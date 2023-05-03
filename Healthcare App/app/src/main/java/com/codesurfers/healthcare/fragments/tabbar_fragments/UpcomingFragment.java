package com.codesurfers.healthcare.fragments.tabbar_fragments;

import static com.codesurfers.healthcare.constants.Constants.BASE_URL;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.codesurfers.healthcare.ListAdapter;
import com.codesurfers.healthcare.MakeAppointment;
import com.codesurfers.healthcare.ViewAppointmentScreen;
import com.codesurfers.healthcare.R;
import com.codesurfers.healthcare.constants.RetrofitClient;
import com.codesurfers.healthcare.model.Appoint;
import com.codesurfers.healthcare.model.Appointment;
import com.codesurfers.healthcare.model.ResponseResult;
import com.codesurfers.healthcare.model.TimeSlot;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpcomingFragment extends Fragment {

    //ActivityMainBinding binding;
    ArrayList<Appoint> appointmentList = new ArrayList<>();
    ArrayList<JSONObject> appointments = new ArrayList<org.json.JSONObject>();
    ListView listView;
    TextView noAppointment, date, time, status;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        listView = view.findViewById(R.id.listView);
        noAppointment=view.findViewById(R.id.noAppointment);
        date = (TextView) view.findViewById(R.id.dateTxt);
        time = view.findViewById(R.id.timeTextView);
        status = view.findViewById(R.id.statusTextView);
        System.out.println(date);

        ArrayList<Appoint> appointArrayList = new ArrayList<>();

        appointArrayList.add(new Appoint("01 May 2023, Monday", "08:00", "Open"));
        appointArrayList.add(new Appoint("02 May 2023, Monday", "10:00", "Open"));

        //ListAdapter adapter = new ListAdapter(this.requireContext(), R.layout.activity_appointment_list_view, appointArrayList);
        //listView.setAdapter(adapter);
        SharedPreferences sp = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        long userId = sp.getInt("userId", 1);
        System.out.println("MY USER ID IS => " + userId);

        getAppointmentByUserId(userId);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ObjectMapper m = new ObjectMapper();
                String jsonObject = String.valueOf(appointments.get(i));
                JsonParser parser = new JsonParser();
                JsonElement mJson = parser.parse(jsonObject);
                Gson gson = new Gson();
                Appointment appointment = gson.fromJson(mJson, Appointment.class);
                TimeSlot timeSlot = appointment.getAppointmentTime();
                System.out.println(timeSlot);
                System.out.println(appointment);
                Toast.makeText(getContext(), "Clicked object" + adapterView.getItemIdAtPosition(i), Toast.LENGTH_SHORT).show();
                Intent fp = new Intent(getContext(), ViewAppointmentScreen.class);
                fp.putExtra("appointment", appointment);
                fp.putExtra("timeSlot", timeSlot);
                startActivity(fp);
            }
        });



        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_btn);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getAppointmentByUserId(1);
                Intent fp=new Intent(getActivity(), MakeAppointment.class);
                startActivity(fp);
            }
        });

        return view;

    }
    private void getAppointmentByUserId(long userId) {
        RetrofitClient client = new RetrofitClient(BASE_URL);
        System.out.println("Start");
        Call<ResponseResult> call = client.getClinicAPI().getAppointmentByUserId(userId);

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

                        ListAdapter adapter = new ListAdapter(getContext(), R.layout.activity_appointment_list_view, appointmentList);
                        listView.setAdapter(adapter);



                        //If there are no items in the adapter then show the no appointments text else show the items
                        if (adapter==null || appointmentList.isEmpty()){
                            noAppointment.setVisibility(View.VISIBLE);


                        }else{

                            //date.setText("appointmentList.get(i).getDate()");
                            //time.setText(appointmentList.get(i).getTime());
                            //status.setText(appointmentList.get(i).getStatus());
                            //System.out.println(appointmentList.get(i).getDate());
                            System.out.println(date);
                            System.out.println(time);
                            System.out.println(status);
                            listView.setAdapter(adapter);
                            noAppointment.setVisibility(View.GONE);
                        }
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