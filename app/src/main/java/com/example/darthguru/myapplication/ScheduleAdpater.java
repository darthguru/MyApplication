package com.example.darthguru.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

/**
 * Created by darthguru on 4/22/17.
 */

public class ScheduleAdpater extends ArrayAdapter<String> {

    public ScheduleAdpater(@NonNull Context context, String[] objects) {
        super(context, R.layout.schedule_layout, objects);
    }
}
