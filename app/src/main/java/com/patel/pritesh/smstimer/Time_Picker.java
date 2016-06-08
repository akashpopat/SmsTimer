package com.patel.pritesh.smstimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Time_Picker extends AppCompatActivity {
    TimePicker time_picker;
    String tag="pritesh";
    static AlarmManager[] alarmManager=new AlarmManager[5];
    static PendingIntent[] pendingIntent = new PendingIntent[5];
    EditText message;
    EditText phone;
    Intent myIntent;
  //  AlarmManager alarmManager;
    //PendingIntent pendingIntent;
    public static String Phone;
    public static String Message;
    public int i;
    array ob=new array();
    static ArrayList<PendingIntent> pendingarray=new ArrayList<>(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.patel.pritesh.smstimer.R.layout.activity_time_picker);
        Log.i(tag,"time");
        message=(EditText)findViewById(com.patel.pritesh.smstimer.R.id.message);
        phone=(EditText)findViewById(com.patel.pritesh.smstimer.R.id.Phone) ;
        prevention.timer=0;
        time_picker = (TimePicker) findViewById(com.patel.pritesh.smstimer.R.id.timePicker);
        time_picker.setIs24HourView(true);


    }
    public void setAlarm(View view) {

                //      AlarmManager alarmManager[]=new AlarmManager[5];

//          if(prevention.timer==0) {
                prevention.timer = 1;
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
                    int a = (Calendar.getInstance().get(Calendar.SECOND) * 1000);
                    i=array.select;
                    myIntent = new Intent(this, MyReceiver.class);
                    myIntent.putExtra("pos",array.select);
                    //Pending Intent for sending the intent afterwards
                    pendingIntent[array.select] = PendingIntent.getBroadcast(this.getApplicationContext(), array.select, myIntent, 0);
                    alarmManager[array.select] = (AlarmManager) (this.getSystemService(Context.ALARM_SERVICE));
                    alarmManager[array.select].set(AlarmManager.RTC, cal.getTimeInMillis() - a, pendingIntent[array.select]);

                    pendingarray.add(pendingIntent[array.select]);
                    sms_list.Phone[array.select]=Phone;
                    Intent back = new Intent(this, sms_list.class);
                    back.putExtra("PHONE", Phone);
                    back.putExtra("Flag",2);
                    back.putExtra("MSG", Message);
                    back.putExtra("HOUR", (int) cal.get(Calendar.HOUR_OF_DAY));
                    back.putExtra("MIN", (int) cal.get(Calendar.MINUTE));

                    startActivity(back);

                    //int b;

                }
                else
                {
                    Toast.makeText(this.getApplicationContext(), "Please Fill Up All The Details", Toast.LENGTH_SHORT).show();
                    array.a[array.select]=false;
                    ob.sort();
                }


    }
}
