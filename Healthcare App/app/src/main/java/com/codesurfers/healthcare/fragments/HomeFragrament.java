package com.codesurfers.healthcare.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codesurfers.healthcare.AppointmentScreen;
import com.codesurfers.healthcare.ClinicScreen;
import com.codesurfers.healthcare.FeedBackScreen;
import com.codesurfers.healthcare.MedicalRecordScreen;
import com.codesurfers.healthcare.R;
import com.codesurfers.healthcare.constants.Services;

import java.util.ArrayList;
import java.util.List;


public class HomeFragrament extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_fragrament, container, false);
        CardView cardView = (CardView) view.findViewById(R.id.appointmentTile);
        CardView cardViewClinic = (CardView) view.findViewById(R.id.clinicTile);
        CardView cardViewMedical = (CardView) view.findViewById(R.id.medicalTile);
        CardView cardViewFeedBack = view.findViewById(R.id.feedbackTile);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**List<String> appointmentList = new ArrayList<>();
                Services.getAppointmentByUserId(1, appointmentList);
                System.out.println("My List => " + appointmentList);
                System.out.println(appointmentList.size());*/
                Intent fp=new Intent(getActivity(), AppointmentScreen.class);
                startActivity(fp);
            }
        });

        cardViewClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fp=new Intent(getActivity(), ClinicScreen.class);
                startActivity(fp);
            }
        });

        cardViewMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fp=new Intent(getActivity(), MedicalRecordScreen.class);
                startActivity(fp);
            }
        });
        cardViewFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fp=new Intent(getActivity(), FeedBackScreen.class);
                startActivity(fp);
            }
        });
        return view;



    }
}