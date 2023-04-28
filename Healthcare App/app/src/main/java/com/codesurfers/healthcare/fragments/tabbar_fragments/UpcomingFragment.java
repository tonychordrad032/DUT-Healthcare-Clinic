package com.codesurfers.healthcare.fragments.tabbar_fragments;

import static com.codesurfers.healthcare.constants.Constants.BASE_URL;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.codesurfers.healthcare.IClinicAPI;
import com.codesurfers.healthcare.R;
import com.codesurfers.healthcare.constants.RetrofitClient;
import com.codesurfers.healthcare.model.Appointment;
import com.codesurfers.healthcare.model.ResponseResult;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpcomingFragment extends Fragment {

    private  IClinicAPI ClinicAPI;



    List<String> appointmentList = new ArrayList<>();
    ListView listView;
    TextView noAppointment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        listView = view.findViewById(R.id.appointmentListView);
        noAppointment=view.findViewById(R.id.noAppointment);



        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_btn);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAppointmentByUserId(1);
                //Intent fp=new Intent(getActivity(), MakeAppointment.class);
                //startActivity(fp);
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
                        System.out.println(object.getJSONObject(i).get("status"));
                        appointmentList.add((String) object.getJSONObject(i).get("status"));
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,appointmentList);

                        //If there are no items in the adapter then show the no appointments text else show the items
                        if (adapter==null){
                            noAppointment.setVisibility(View.VISIBLE);


                        }else{
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