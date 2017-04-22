package com.example.darthguru.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ScheduleAdapter extends ArrayAdapter<ScheduleParcelable> {

    public ScheduleAdapter(@NonNull Context context, ArrayList<ScheduleParcelable> objects) {
        super(context, R.layout.schedule_layout, objects);
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View theView = inflater.inflate(R.layout.schedule_layout, parent, false);

        ScheduleParcelable schedule_item = getItem(position);

        TextView name_medicine = (TextView) theView.findViewById(R.id.name_medicine);
        name_medicine.setText(schedule_item.name);

        TextView freq_medicine = (TextView) theView.findViewById(R.id.freq_medicine);
        freq_medicine.setText(schedule_item.frequency);

        ListView time_list = (ListView) theView.findViewById(R.id.time_of_day);
        ListAdapter time_adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, schedule_item.times);
        time_list.setClickable(false);
        time_list.setAdapter(time_adapter);


        return theView;
    }


}
