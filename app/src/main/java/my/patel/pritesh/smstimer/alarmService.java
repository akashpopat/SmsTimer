package my.patel.pritesh.smstimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.Toast;

import java.util.Calendar;

public class alarmService extends Service {
    Intent myIntent;
    long h,m;
    private static final String MY_ACTION="SEND";

    public static AlarmManager[] alarmManager=new AlarmManager[5];
    public static PendingIntent[] pendingIntent = new PendingIntent[5];
    public alarmService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        array.sort();
        int hour,min;
        hour = intent.getIntExtra("hour",0);
        min=intent.getIntExtra("min",0);
        String Phone=intent.getStringExtra("Phone");
        String Message=intent.getStringExtra("Message");
        Toast.makeText(this.getApplicationContext(), "Your sms will be sent soon", Toast.LENGTH_SHORT).show();
        //Getting Calender Reference
        Calendar cal = Calendar.getInstance();

            cal.set(Calendar.MINUTE, min);
            cal.set(Calendar.HOUR_OF_DAY, hour);

        //System clock time
        Calendar c=Calendar.getInstance();
        Long a ;//=(long) (Calendar.getInstance().get(Calendar.SECOND) * 1000);
        if(cal.get(Calendar.HOUR_OF_DAY)<c.get(Calendar.HOUR_OF_DAY))
            h=(cal.get(Calendar.HOUR_OF_DAY)+24-c.get(Calendar.HOUR_OF_DAY))*60;
        else
            h=(cal.get(Calendar.HOUR_OF_DAY)-c.get(Calendar.HOUR_OF_DAY))*60;
        m=(cal.get(Calendar.MINUTE)-c.get(Calendar.MINUTE));
        a=(m+h)*60-c.get(Calendar.SECOND);
        myIntent = new Intent(this, MyReceiver.class);
        myIntent.putExtra("pos",array.select);
        myIntent.setAction(MY_ACTION);
        //Pending Intent for sending the intent afterwards
        pendingIntent[array.select] = PendingIntent.getBroadcast(this.getApplicationContext(), array.select, myIntent,0);
        alarmManager[array.select] = (AlarmManager) (this.getSystemService(Context.ALARM_SERVICE));

        alarmManager[array.select].set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()+a*1000, pendingIntent[array.select]);

        sms_list.Phone[array.select]=Phone;
        Intent back = new Intent(this, sms_list.class);
        back.putExtra("PHONE", Phone);
        back.putExtra("Flag",2);
        back.putExtra("MSG", Message);
        back.putExtra("HOUR", (int) cal.get(Calendar.HOUR_OF_DAY));
        back.putExtra("MIN", (int) cal.get(Calendar.MINUTE));
        back.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(back);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
