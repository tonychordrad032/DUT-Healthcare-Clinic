package com.codesurfers.healthcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.codesurfers.healthcare.model.Appoint;
import com.codesurfers.healthcare.model.Appointment;
import com.codesurfers.healthcare.model.ResponseResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class ListAdapter extends ArrayAdapter<Appoint> {
    private Context mContext;
    private  int mResource;

    public ListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Appoint> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource,parent, false);

        /**if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_appointment_list_view, parent, false);

        }*/

        TextView appointmentDate = convertView.findViewById(R.id.dateTxt);
        TextView appointmentTime = convertView.findViewById(R.id.timeTextView);
        TextView status = convertView.findViewById(R.id.statusTextView);

        appointmentDate.setText(getItem(position).getDate());
        appointmentTime.setText(getItem(position).getTime());
        status.setText(getItem(position).getStatus());
        return convertView;
    }
}
