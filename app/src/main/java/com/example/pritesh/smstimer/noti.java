package com.example.pritesh.smstimer;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Pritesh on 6/3/2016.
 */
public class noti extends Activity{
    String tag="Pritesh";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(tag,"in notification class");
        NotificationManager notificationManager=  (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1=new Intent(getApplicationContext(),Time_Picker.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,intent1,0);
        Notification notification=new Notification.Builder(getApplicationContext())
                .setContentTitle("SMS SENT")
                .setContentText("Your SMS has Been Sent")
                .setContentIntent(pendingIntent)
                .build();
        notificationManager.notify(0,notification);

    }
}
