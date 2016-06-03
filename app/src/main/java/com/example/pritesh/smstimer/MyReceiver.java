package com.example.pritesh.smstimer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    String SENT="sent";
    public final String tag="com.example.pritesh.smstimer";
    public MyReceiver() {

    }

    //@Override
    public void onReceive(Context context, Intent intent) {

            Log.i(tag,"Sending");
    //        NotificationManager notificationManager=  (NotificationManager)
    //                context.getSystemService(Context.NOTIFICATION_SERVICE);
    //        Intent intent1=new Intent(context,Time_Picker.class);
    //        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent1,0);
    //        Notification notification=new Notification.Builder(context)
    //                .setContentTitle("SMS SENT")
    //                .setContentText("Your SMS has Been Sent")
    //                .setContentIntent(pendingIntent)
    //                .build();


            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(Time_Picker.Phone,null, Time_Picker.Message, null, null);
         //   notificationManager.notify(0,notification);
            Toast.makeText(context, "Sms Sent", Toast.LENGTH_LONG).show();
            Intent notificationIntent = new Intent(context,noti.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(notificationIntent);
    }
}
