package com.example.darthguru.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class ScheduleAdapter extends ArrayAdapter<String> {

    public ScheduleAdapter(@NonNull Context context, String[] objects) {
        super(context, R.layout.schedule_layout, objects);
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View theView = inflater.inflate(R.layout.schedule_layout, parent, false);
        String[] names = parent.getResources().getStringArray(R.array.medicine_array);
//
        String schedule_item = getItem(position);
//
        TextView name_medicine = (TextView) theView.findViewById(R.id.name_medicine);
        name_medicine.setText(names[position]);
//
        TextView freq_medicine = (TextView) theView.findViewById(R.id.freq_medicine);
        freq_medicine.setText(schedule_item);


        return theView;
    }


}
