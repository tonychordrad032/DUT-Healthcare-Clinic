package com.codesurfers.healthcare.fragments.tabbar_fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codesurfers.healthcare.MakeAppointment;
import com.codesurfers.healthcare.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UpcomingFragment extends Fragment {

    RecyclerView rv_1;

    String appointmentList [] = {"Sanele", "Nceba", "Muzi", "Phiwa"};
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        rv_1 = view.findViewById(R.id.rv_1);

        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_btn);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fp=new Intent(getActivity(), MakeAppointment.class);
                startActivity(fp);
            }
        });
        return view;
    }
}