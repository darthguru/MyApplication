package com.example.darthguru.myapplication;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    String TAG = "this";
    String[] frequency = new String[]{"Morning", "Morning", "Morning", "Morning", "Morning", "Morning"};
    
    BluetoothAdapter bluetoothAdapter;
    BluetoothService bluetoothService;
    CoordinatorLayout coordinatorLayout;
    Snackbar snackTurnOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();

        Log.i(TAG, "onCreate: created main activity");
//        getSchedule();
        Log.i(TAG, "onCreate: got schedule");
        myHandler handler = new myHandler(MainActivity.this);


        Button create_config = (Button) findViewById(R.id.create_config);
        create_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
                intent.putExtra("freqMed", frequency);
                startActivityForResult(intent, 1);
            }
        });
    }

    public void getSchedule() {
        ListAdapter schedule_adapter = new ScheduleAdapter(this, frequency);
        ListView schedule_list = (ListView) findViewById(R.id.schedule_list);
        schedule_list.setClickable(false);
        schedule_list.setAdapter(schedule_adapter);
    }

    public void bluetooth_init(){

        // enable bluetooth
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBtIntent, Constants.REQUEST_ENABLE_BT);

        //

    }

    public void enableBluetooth(){
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBtIntent, Constants.REQUEST_ENABLE_BT);
    }

    // Create a BroadcastReceiver for ACTION_FOUND
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                device.fetchUuidsWithSdp();

            } else if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        Log.i(TAG, "onReceive: Bluetooth off");
                        Toast.makeText(context, "Bluetooth  turned off ", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    };

    private void sendMessage(String message) {
        // Check that we're actually connected before trying anything
        if (bluetoothService.getState() != Constants.STATE_CONNECTED) {
            Snackbar.make(coordinatorLayout, "You are not connected", Snackbar.LENGTH_LONG)
                    .setAction("Connect", new View.OnClickListener() {
                        @Override public void onClick(View v) {
                            reconnect();
                        }
                    }).show();
        } else {
            byte[] send = message.getBytes();
            bluetoothService.write(send);
        }
    }

    private void reconnect() {
        bluetoothService.stop();
        bluetoothService.connect();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100) {
            String message = null;
            frequency = data.getStringArrayExtra("freqMed");
            for(String i : frequency) {
                Log.i(TAG, "onActivityResult: " + i);
                message.concat("8,"+ i + ">");
            }
            sendMessage("1>");
            sendMessage(message);
        }

    }

    private static class myHandler extends Handler {
        private final WeakReference<MainActivity> mActivity;

        public myHandler(MainActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {

            final MainActivity activity = mActivity.get();

            switch (msg.what) {
                case Constants.MESSAGE_STATE_CHANGE:
                    switch (msg.arg1) {
                        case Constants.STATE_CONNECTED:
                            break;
                        case Constants.STATE_CONNECTING:
                            break;
                        case Constants.STATE_NONE:
                            break;
                        case Constants.STATE_ERROR:
                            break;
                    }
                    break;
                case Constants.MESSAGE_WRITE:
                    Toast.makeText(activity,"talking to bluetooth",Toast.LENGTH_SHORT).show();
                    break;
                case Constants.MESSAGE_READ:
                    String readMessage = (String) msg.obj;
                    Log.i(activity.TAG, "handleMessage: reading message from bluetooth"+ readMessage.trim());
                    break;

                case Constants.MESSAGE_SNACKBAR:
                    Snackbar.make(activity.coordinatorLayout, msg.getData().getString(Constants.SNACKBAR), Snackbar.LENGTH_LONG)
                            .setAction("Connect", new View.OnClickListener() {
                                @Override public void onClick(View v) {
                                    activity.reconnect();
                                }
                            }).show();

                    break;
            }
        }


    }
}
