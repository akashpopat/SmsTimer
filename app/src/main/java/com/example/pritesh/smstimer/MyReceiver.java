package com.example.pritesh.smstimer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    String SENT="sent";
    String DELIVERED="delivered";
    public final String tag="com.example.pritesh.smstimer";
    public MyReceiver() {

    }

    //@Override
    public void onReceive(Context context, Intent intent) {

            Log.i(tag,"Sending");
            final NotificationManager notificationManager=  (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);

            Intent intent1=new Intent(context,Time_Picker.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent1,0);
        //Notification for sent msg
        final Notification.Builder notification_sent=new Notification.Builder(context)
                    .setContentTitle("SMS SENT")
                    .setContentText("Your SMS has Been Sent")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.favicon);
        //Notification for msg not sent
        final Notification.Builder notification_notsent=new Notification.Builder(context)
                .setContentTitle("SMS SENT")
                .setContentText("Your SMS was not sent due to no network service")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.favicon);
        //Intent for sending confirmation

        Intent sent=new Intent();
        sent.setFlags(1);
        sent.setAction("notification");
        //PendingIntent for sending confirmation

        PendingIntent sentIntent=PendingIntent.getBroadcast(context,0,sent,PendingIntent.FLAG_UPDATE_CURRENT);
        //Reciever for sent confirmation

        Intent deliveryIntent = new Intent();
        deliveryIntent.setFlags(2);
        deliveryIntent.setAction("notification");
        PendingIntent deliverPI = PendingIntent.getBroadcast(
                context, 0, deliveryIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

//        context.registerReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                String result = "";
//
//                switch (getResultCode()) {
//
//                    case Activity.RESULT_OK:
//                        result = "Transmission successful";
//                        notificationManager.notify(0,notification_sent.build());
//                        break;
//                    case SmsManager.RESULT_ERROR_NO_SERVICE:
//                        result = "No service";
//                        notificationManager.notify(0,notification_notsent.build());
//                        break;
//                }
//            }
//        },new IntentFilter(SENT));

//        context.registerReceiver(new BroadcastReceiver() {
//
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                Toast.makeText(context, "Deliverd",
//                        Toast.LENGTH_LONG).show();
//            }
//
//        }, new IntentFilter(DELIVERED));


            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(Time_Picker.Phone,null, Time_Picker.Message, sentIntent
                    ,deliverPI);

            Toast.makeText(context, "Sms Sent", Toast.LENGTH_LONG).show();

    }

}
