package com.example.pritesh.smstimer;

import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    private static final String TAG = "com.example.pritesh.smstimer";
    String SENT="sent";
    public MyService() {
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        Log.i(TAG,"Sending");
        Intent sentIntent=new Intent((SENT));
        PendingIntent sent=PendingIntent.getBroadcast(getApplicationContext(),0,sentIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(Time_Picker.Phone,null, Time_Picker.Message, null, null);
        Toast.makeText(getApplicationContext(), "Sms Sent", Toast.LENGTH_LONG).show();
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
