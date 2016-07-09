package my.patel.pritesh.smstimer;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {


    public final String tag="com.example.pritesh.smstimer";
    public MyReceiver() {

    }

    array ob=new array();
    int c=0;
    PendingIntent pend;

    //@Override
    public void onReceive(Context context, Intent intent) {
            int pos;


            Log.i(tag,"Sending");

        //Intent for sending confirmation
        pos=intent.getIntExtra("pos",9999);
        Intent sent=new Intent();
        sent.setAction("notification");
        //PendingIntent for sending confirmation

//        final NotificationManager notificationManager=  (NotificationManager)
//                context.getSystemService(Context.NOTIFICATION_SERVICE);
//        Intent intent1=new Intent(context,sms_list.class);
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent1,0);
//        final Notification.Builder notification_sent=new Notification.Builder(context)
//                .setContentTitle("Problem Solved")
//                .setContentText("HIIIIIIIIIIIIIIII")
//                .setContentIntent(pendingIntent)
//                .setAutoCancel(true)
//                .setSmallIcon(my.patel.pritesh.smstimer.R.drawable.sms_timer);
//        notificationManager.notify(0,notification_sent.build());

        PendingIntent sentIntent=PendingIntent.getBroadcast(context,0,sent,PendingIntent.FLAG_UPDATE_CURRENT);
        //Reciever for sent confirmation

        Intent deliveryIntent = new Intent();
        deliveryIntent.setAction("notification_delivered");
        PendingIntent deliverPI = PendingIntent.getBroadcast(
                context, 0, deliveryIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        if(array.select>=pos)
            array.select=pos;
        ob.arrange(pos);
        sms_list.min[4]=0;
        sms_list.hour[4]=0;
        sms_list.Phone[4]=null;
        sms_list.msg[4]=null;
        array.a[4]=false;

        //c=pos;
      //  pend=  Time_Picker.pendingarray.get(pos);
        Time_Picker.alarmManager[pos].cancel(Time_Picker.pendingIntent[pos]);
        //Time_Picker.pendingarray.remove(pos);

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(Time_Picker.Phone,null, Time_Picker.Message, sentIntent
                    ,deliverPI);

        Intent in = new Intent(context,sms_list.class);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        in.putExtra("Flag",1);
        c=array.select;
        context.startActivity(in);

       // prevention.timer=0;

            Toast.makeText(context, "Sms Sent", Toast.LENGTH_LONG).show();

    }

}
