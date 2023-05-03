package com.codesurfers.healthcare;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class AdminAppointmentVIewHolder extends RecyclerView.ViewHolder {

    TextView appointTitle, appointTime;

    public AdminAppointmentVIewHolder(@NonNull View itemView) {
        super(itemView);

        appointTitle = itemView.findViewById(R.id.appointmentTitle);
        appointTime = itemView.findViewById(R.id.appointmentTime);


    }
}
