package com.example.darthguru.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by darthguru on 4/22/17.
 */

public class ScheduleActivity extends AppCompatActivity {

    public int request_code;
    public ScheduleParcelable name_med = new ScheduleParcelable();
    public Spinner spinner1;
    public Spinner spinner2;
    public Spinner spinner3;
    public Spinner spinner4;
    public Spinner spinner5;
    public Spinner spinner6;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_config);


//        name_med = getIntent().getParcelableExtra("nameMed");
//
//        request_code = getIntent().getIntExtra("requestCode", -1);

        populateSpinners();



    }

    public void populateSpinners() {
        spinner1 = (Spinner) findViewById(R.id.time_spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.time_array, R.layout.spinner_white);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner2 = (Spinner) findViewById(R.id.time_spinner_2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.time_array, R.layout.spinner_white);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner3 = (Spinner) findViewById(R.id.time_spinner_3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.time_array, R.layout.spinner_white);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        spinner4 = (Spinner) findViewById(R.id.time_spinner_4);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.time_array, R.layout.spinner_white);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        spinner5 = (Spinner) findViewById(R.id.time_spinner_5);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
                R.array.time_array, R.layout.spinner_white);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);

        spinner6 = (Spinner) findViewById(R.id.time_spinner_6);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,
                R.array.time_array, R.layout.spinner_white);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter6);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(20000, intent);
        finish();
        super.onBackPressed();
    }

}
