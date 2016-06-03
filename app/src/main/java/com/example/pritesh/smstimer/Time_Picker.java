package com.example.pritesh.smstimer;

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
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    public static String Phone;
    public static String Message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        message=(EditText)findViewById(R.id.message);
        phone=(EditText)findViewById(R.id.Phone) ;

        time_picker = (TimePicker) findViewById(R.id.timePicker);
        time_picker.setIs24HourView(true);


    }
    public void setAlarm(View view) {
        Phone=phone.getText().toString();
        Message=message.getText().toString();
        phone.setText("");
        message.setText("");
        //Validating if any field empty
        if(Phone.length()==10&&Message.length()>0) {
            Toast.makeText(this.getApplicationContext(),"Your sms will be sent soon",Toast.LENGTH_SHORT).show();
            //Getting Calender Reference
            Calendar cal = Calendar.getInstance();
            int currentApiVersion = android.os.Build.VERSION.SDK_INT;
            if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1){
                cal.set(Calendar.MINUTE, time_picker.getMinute());
                cal.set(Calendar.HOUR_OF_DAY,time_picker.getHour());
            }
            else {
                //Setting the date and time from the time picker
                cal.set(Calendar.MINUTE, time_picker.getCurrentMinute());
                cal.set(Calendar.HOUR_OF_DAY,time_picker.getCurrentHour());
            }

            int a=(Calendar.getInstance().get(Calendar.SECOND)*1000);
           // int b= (int) ((cal.getTimeInMillis()/1000)%60);
            //Intent to call the broadcast Reciever


            myIntent = new Intent(this, MyReceiver.class);
            //Pending Intent for sending the intent afterwards
            pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(),0, myIntent, 0);
            alarmManager=(AlarmManager)(this.getSystemService(Context.ALARM_SERVICE));
            alarmManager.set(AlarmManager.RTC,cal.getTimeInMillis()-a

                    , pendingIntent);
        }
        else
        {
            Toast.makeText(this.getApplicationContext(),"Please Fill Up All The Details",Toast.LENGTH_SHORT).show();
        }
    }
}
