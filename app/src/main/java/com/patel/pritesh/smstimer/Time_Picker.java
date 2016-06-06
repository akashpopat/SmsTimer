package com.patel.pritesh.smstimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Time_Picker extends AppCompatActivity {
    TimePicker time_picker;
    EditText message;
    EditText phone;
    Intent myIntent;
  //  AlarmManager alarmManager;
    //PendingIntent pendingIntent;
    public static String Phone;
    public static String Message;
    public static int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.patel.pritesh.smstimer.R.layout.activity_time_picker);

        message=(EditText)findViewById(com.patel.pritesh.smstimer.R.id.message);
        phone=(EditText)findViewById(com.patel.pritesh.smstimer.R.id.Phone) ;
        prevention.timer=0;
        time_picker = (TimePicker) findViewById(com.patel.pritesh.smstimer.R.id.timePicker);
        time_picker.setIs24HourView(true);


    }
    public void setAlarm(View view) {
        AlarmManager alarmManager[]=new AlarmManager[5];
        PendingIntent[] pendingIntent=new PendingIntent[5];
//          if(prevention.timer==0) {
            prevention.timer=1;
            Phone = phone.getText().toString();
            Message = message.getText().toString();
            phone.setText("");
            message.setText("");
            //Validating if any field empty
            if (Phone.length() >= 10 && Message.length() > 0) {
                Toast.makeText(this.getApplicationContext(), "Your sms will be sent soon", Toast.LENGTH_SHORT).show();
                //Getting Calender Reference
                Calendar cal = Calendar.getInstance();
                int currentApiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                    cal.set(Calendar.MINUTE, time_picker.getMinute());
                    cal.set(Calendar.HOUR_OF_DAY, time_picker.getHour());
                } else {
                    //Setting the date and time from the time picker
                    cal.set(Calendar.MINUTE, time_picker.getCurrentMinute());
                    cal.set(Calendar.HOUR_OF_DAY, time_picker.getCurrentHour());
                }
               // ArrayList<PendingIntent>pendingarray=new ArrayList<>(5);
                int a = (Calendar.getInstance().get(Calendar.SECOND) * 1000);

                myIntent = new Intent(this, MyReceiver.class);
                //Pending Intent for sending the intent afterwards
                pendingIntent[i] = PendingIntent.getBroadcast(this.getApplicationContext(),i, myIntent, 0);
                alarmManager[i] = (AlarmManager) (this.getSystemService(Context.ALARM_SERVICE));
                alarmManager[i].set(AlarmManager.RTC, cal.getTimeInMillis() - a, pendingIntent[i]);
                i++;
                Intent back=new Intent(this,sms_list.class);
                back.putExtra("PHONE",Phone);
                back.putExtra("MSG",Message);
                startActivity(back);
                //int b;
               // pendingarray.add(pendingIntent);
            } else {
                Toast.makeText(this.getApplicationContext(), "Please Fill Up All The Details", Toast.LENGTH_SHORT).show();
            }
//        }
//        else
//            Toast.makeText(this.getApplicationContext(), "Sorry,You can set one timer at a time", Toast.LENGTH_SHORT).show();

    }
}
