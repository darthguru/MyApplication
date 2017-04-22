package com.example.darthguru.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class ScheduleActivity extends AppCompatActivity {

    public int request_code;
    public String[] freq_med;
    public List<Spinner> spinners = new ArrayList<Spinner>() {
    };
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

        freq_med = getIntent().getStringArrayExtra("freqMed");

        request_code = getIntent().getIntExtra("requestCode", -1);

        fillSpinners();
    }

    public void fillSpinners() {
        spinner1 = (Spinner) findViewById(R.id.time_spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.time_array, R.layout.spinner_white);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        int spinnerPosition = adapter1.getPosition(freq_med[0]);
        spinner1.setSelection(spinnerPosition);

        spinner2 = (Spinner) findViewById(R.id.time_spinner_2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.time_array, R.layout.spinner_white);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinnerPosition = adapter2.getPosition(freq_med[1]);
        spinner2.setSelection(spinnerPosition);

        spinner3 = (Spinner) findViewById(R.id.time_spinner_3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.time_array, R.layout.spinner_white);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        spinnerPosition = adapter3.getPosition(freq_med[2]);
        spinner3.setSelection(spinnerPosition);


        spinner4 = (Spinner) findViewById(R.id.time_spinner_4);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.time_array, R.layout.spinner_white);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        spinnerPosition = adapter4.getPosition(freq_med[3]);
        spinner4.setSelection(spinnerPosition);

        spinner5 = (Spinner) findViewById(R.id.time_spinner_5);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
                R.array.time_array, R.layout.spinner_white);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);

        spinnerPosition = adapter5.getPosition(freq_med[4]);
        spinner5.setSelection(spinnerPosition);

        spinner6 = (Spinner) findViewById(R.id.time_spinner_6);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,
                R.array.time_array, R.layout.spinner_white);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter6);

        spinnerPosition = adapter6.getPosition(freq_med[5]);
        spinner6.setSelection(spinnerPosition);

        spinners.add(spinner1);
        spinners.add(spinner2);
        spinners.add(spinner3);
        spinners.add(spinner4);
        spinners.add(spinner5);
        spinners.add(spinner6);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(20000, intent);
        finish();
        super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.config_menu, menu);
        return true;
    }


    public void setValues() {
        int j = 0;
        for (Spinner i : spinners) {
            freq_med[j] = i.getSelectedItem().toString();
            j++;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.confirm_feeder) {
            setValues();
            Intent intent = new Intent();
            intent.putExtra("freqMed", freq_med);
            setResult(100, intent);
            finish();
        } else if (id == R.id.delete_feeder) {
            Intent intent = new Intent();
            setResult(20000, intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
