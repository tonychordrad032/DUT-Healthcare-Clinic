package com.codesurfers.healthcare.fragments.tabbar_fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.codesurfers.healthcare.R;
import com.codesurfers.healthcare.model.Appoint;

import java.util.ArrayList;
import java.util.List;

public class Custom extends BaseAdapter {

    private List<Appoint> appointArrayList;
    private Context context;
    private int layout;

    public Custom(ArrayList<Appoint> appointArrayList, Context context, int layout) {
        this.appointArrayList = appointArrayList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return appointArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return appointArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        TextView date, time, status;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(layout, null);

        viewHolder.date = view.findViewById(R.id.dateTxt);
        viewHolder.time = view.findViewById(R.id.timeTextView);
        viewHolder.status = view.findViewById(R.id.statusTextView);

        Appoint appoint = appointArrayList.get(i);
        viewHolder.date.setText(appoint.getDate());
        viewHolder.time.setText(appoint.getTime());
        viewHolder.status.setText(appoint.getStatus());
        return view;
    }
}
