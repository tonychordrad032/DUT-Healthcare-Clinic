package com.codesurfers.healthcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    ArrayList<ClinicModel> clinicModelArrayList;
    Context context;
    RecyclerViewClick listener;

    public Adapter(Context context, ArrayList<ClinicModel> clinicModelArrayList, RecyclerViewClick listener) {
        this.context = context;
        this.clinicModelArrayList = clinicModelArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clinic_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.clinicName.setText(clinicModelArrayList.get(position).getClinicName());

    }

    @Override
    public int getItemCount() {
        return clinicModelArrayList.size();
    }

    public interface RecyclerViewClick{
        void click(View v, int position);
    }

    public class  ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView clinicName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clinicName = itemView.findViewById(R.id.clinicName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.click(view,getAdapterPosition());

        }
    }
}
