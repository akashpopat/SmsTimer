package com.example.pritesh.smstimer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static java.security.AccessController.getContext;

public class MyService extends Service {
    public NumberPicker hour;
    public NumberPicker min;
   public EditText phone;
    public EditText message;
 //   int h=0,m=0;
    TextView time;
    private static final String TAG = "com.example.pritesh.smstimer";

    public MyService() {
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
       /* hour.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                h=newVal;
                time.setText(h+":"+m);
            }
        });
        min.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                m=newVal;
                time.setText(h+":"+m);
            }
        });*/
      //  phone=(EditText)findViewById(R.id.ph_no);
      //  message=(EditText)findViewById(R.id.message);
        Log.i(TAG, "started");
        Runnable r = new Runnable() {
            @Override
            public void run() {
                // Intent i = new Intent();
                Bundle bundle = intent.getExtras();
                int h = bundle.getInt("hours");
                int m = bundle.getInt("mins");
                String ph_no = bundle.getString("phone");
                String msg = bundle.getString("msg");

                while (true) {
                    Calendar c = Calendar.getInstance();
                    int sHour = c.get(Calendar.HOUR_OF_DAY);
                    int sMin = c.get(Calendar.MINUTE);
                    Log.i(TAG, Integer.toString(sHour) + ":" + Integer.toString(sMin));
                    if (sHour == h && sMin == m) {
                        Log.i(TAG, "SENT");
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(ph_no, null, msg, null, null);
                        Toast.makeText(getApplicationContext(), "Sms Sent", Toast.LENGTH_LONG).show();
                        stopSelf();
                        break;
                    }
                    synchronized (this) {
                        try {
                            wait(30000);
                        } catch (Exception e) {
                        }
                    }

                }
            }
            };

        Thread thread = new Thread(r);

        thread.start();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"Destroyed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
