package com.patel.pritesh.smstimer;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

public class NotificationReciever extends BroadcastReceiver {
    public NotificationReciever() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        final NotificationManager notificationManager=  (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1=new Intent(context,Time_Picker.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent1,0);
        final Notification.Builder notification_sent=new Notification.Builder(context)
                .setContentTitle("SMS SENT")
                .setContentText("Your SMS has Been Sent")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(com.patel.pritesh.smstimer.R.drawable.sms_timer);
        final Notification.Builder notification_notsent = new Notification.Builder(context)
                .setContentTitle("SMS NOT SENT")
                .setContentText("Your SMS was not sent due to no network service")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(com.patel.pritesh.smstimer.R.drawable.sms_timer);
            switch (getResultCode()) {

                case Activity.RESULT_OK:
                    notificationManager.notify(0, notification_sent.build());
                    break;
                case SmsManager.RESULT_ERROR_NO_SERVICE:
                    notificationManager.notify(0, notification_notsent.build());
                    break;
            }


    }
}
