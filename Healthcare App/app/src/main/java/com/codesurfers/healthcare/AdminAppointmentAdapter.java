package com.codesurfers.healthcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codesurfers.healthcare.model.Appointment;
import com.codesurfers.healthcare.model.ResponseResult;

import java.util.List;

public class AdminAppointmentAdapter extends RecyclerView.Adapter<AdminAppointmentVIewHolder> {
    Context context;
    List<Appointment> items;

    public AdminAppointmentAdapter(Context context, List<Appointment> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public AdminAppointmentVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdminAppointmentVIewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_appointment_recycler_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminAppointmentVIewHolder holder, int position) {
            Appointment item = items.get(position);
            holder.appointTitle.setText(item.getPatient().getStudentNumber());
            holder.appointTime.setText(item.getAppointmentTime().getTime());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
