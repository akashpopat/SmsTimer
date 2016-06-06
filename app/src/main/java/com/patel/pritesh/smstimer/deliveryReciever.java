package com.patel.pritesh.smstimer;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class deliveryReciever extends BroadcastReceiver {
    public deliveryReciever() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final NotificationManager notificationManager=  (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1=new Intent(context,Time_Picker.class);
        PendingIntent pendingDelivered = PendingIntent.getActivity(context,0,intent1,0);

        final Notification.Builder notification_Delivered = new Notification.Builder(context)
                .setContentTitle("SMS DELIVERED")
                .setContentText("Your Sms was delivered")
                .setContentIntent(pendingDelivered)
                .setAutoCancel(true)
                .setSmallIcon(com.patel.pritesh.smstimer.R.drawable.sms_timer);

        switch (getResultCode()) {
            case Activity.RESULT_OK:
                notificationManager.notify(0, notification_Delivered.build());
                break;
        }
    }
}
