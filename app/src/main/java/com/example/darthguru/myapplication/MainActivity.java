package com.example.darthguru.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    String TAG = "this";
    ArrayList<ScheduleParcelable> schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        Log.i(TAG, "onCreate: created main activity");
        getSchedule();
        Log.i(TAG, "onCreate: got schedule");

        Button create_config = (Button) findViewById(R.id.create_config);
        create_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScheduleParcelable new_feeder = new ScheduleParcelable();
                Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
                intent.putExtra("feederData", new_feeder);
                startActivityForResult(intent, 1);
            }
        });

    }


    public void getSchedule() {
        ListAdapter schedule_adapter = new ScheduleAdapter(this, schedule);
        ListView schedule_list = (ListView) findViewById(R.id.schedule_list);
        schedule_list.setClickable(false);
        schedule_list.setAdapter(schedule_adapter);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.BLUETOOTH}, 5);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.BLUETOOTH_ADMIN}, 5);
        }
    }
}
