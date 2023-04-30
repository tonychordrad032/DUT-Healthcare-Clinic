package com.codesurfers.healthcare.fragments.tabbar_fragments;

import static com.codesurfers.healthcare.constants.Constants.BASE_URL;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.codesurfers.healthcare.HomeScreenActivity;
import com.codesurfers.healthcare.ListAdapter;
import com.codesurfers.healthcare.MakeAppointment;
import com.codesurfers.healthcare.ViewAppointmentScreen;
import com.codesurfers.healthcare.constants.IClinicAPI;
import com.codesurfers.healthcare.R;
import com.codesurfers.healthcare.constants.RetrofitClient;
import com.codesurfers.healthcare.databinding.ActivityMainBinding;
import com.codesurfers.healthcare.model.Appoint;
import com.codesurfers.healthcare.model.Appointment;
import com.codesurfers.healthcare.model.ResponseResult;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpcomingFragment extends Fragment {

    ActivityMainBinding binding;
    ArrayList<Appoint> appointmentList = new ArrayList<>();
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

        getAppointmentByUserId(1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent fp = new Intent(getContext(), ViewAppointmentScreen.class);
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
                        if (object.getJSONObject(i).get("status").toString().equals("Open")){
                            System.out.println("INSIDE IF");
                            appointmentList.add(new Appoint("01 May 2023, Monday", "09:00", (String) object.getJSONObject(i).get("status")));
                            System.out.println(appointmentList);

                        }

                        ListAdapter adapter = new ListAdapter(getContext(), R.layout.activity_appointment_list_view, appointmentList);
                        listView.setAdapter(adapter);



                        //If there are no items in the adapter then show the no appointments text else show the items
                        if (adapter==null){
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