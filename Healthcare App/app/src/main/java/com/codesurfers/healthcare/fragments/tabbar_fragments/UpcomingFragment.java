package com.codesurfers.healthcare.fragments.tabbar_fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.codesurfers.healthcare.MakeAppointment;
import com.codesurfers.healthcare.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class UpcomingFragment extends Fragment {


    String[] appointmentList ={"Hello", "World","Good"} ;
    ListView listView;
    TextView noAppointment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        listView = view.findViewById(R.id.appointmentListView);
        noAppointment=view.findViewById(R.id.noAppointment);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,appointmentList);

        //If there are no items in the adapter then show the no appointments text else show the items
        if (adapter==null){
            noAppointment.setVisibility(View.VISIBLE);


        }else{
            listView.setAdapter(adapter);
            noAppointment.setVisibility(View.GONE);
        }

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