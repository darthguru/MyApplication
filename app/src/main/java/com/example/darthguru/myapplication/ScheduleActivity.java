package com.example.darthguru.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by darthguru on 4/22/17.
 */

class ScheduleActivity extends AppCompatActivity {

    public int request_code;
    public ScheduleParcelable name_med = new ScheduleParcelable();
//    public Spinner spinner;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_config);


        name_med = getIntent().getParcelableExtra("nameMed");

        request_code = getIntent().getIntExtra("requestCode", -1);

//        spinner = (Spinner) findViewById(R.id.area_code);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.planets_array, R.layout.spinner_white);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(20000, intent);
        finish();
        super.onBackPressed();
    }

}
