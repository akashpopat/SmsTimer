package com.example.pritesh.smstimer;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

//import android.telephony.SmsManager;
//import android.util.Log;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String tag="com.example.pritesh.smstimer";
    Button send;
    public static String ph_no="";
    public static String msg="";
    EditText phone;
    EditText message;
    NumberPicker hour;
    NumberPicker min;
    TextView time;
    int h=0,m=0;
    BroadcastReceiver br;
    PendingIntent pi;
    AlarmManager am;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send=(Button)findViewById(R.id.send);
        phone=(EditText)findViewById(R.id.ph_no);
        message=(EditText)findViewById(R.id.message);
        hour=(NumberPicker)findViewById(R.id.Hour);
        min=(NumberPicker)findViewById(R.id.min);
        time=(TextView)findViewById(R.id.time);
        hour.setMinValue(0);
        hour.setMaxValue(23);
        min.setMinValue(0);
        min.setMaxValue(59);
        hour.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                h=newVal;
                time.setText(h+":"+m);
            }
        });
        min.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                m=newVal;
                time.setText(h+":"+m);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ph_no =phone.getText().toString();
                msg=message.getText().toString();
                if(ph_no.length()>0&&msg.length()>0) {

                    Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();

                    sendMessage(ph_no, msg);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Fill up all the details", Toast.LENGTH_LONG).show();
                }
            }
        });



    }


    private void sendMessage(final String ph_no, final String msg) {
        hour=(NumberPicker)findViewById(R.id.Hour);
        min=(NumberPicker)findViewById(R.id.min);
        time=(TextView)findViewById(R.id.time);

        //try {

          /*  Runnable r = new Runnable() {
                @Override
                public void run() {

                    Calendar c = Calendar.getInstance();
                    while(true) {

                        //  SimpleDateFormat sdf=new SimpleDateFormat("kk:mm");
                        int sHour = c.get(Calendar.HOUR_OF_DAY);
                        int sMin = c.get(Calendar.MINUTE);
                        Log.i(tag, "hi");
                        if (sHour == h && sMin == m) {
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(ph_no, null, msg, null, null);
                            Toast.makeText(getApplicationContext(), "Sms Sent", Toast.LENGTH_LONG).show();
                            break;
                        }

                    }
                }
            };
            Thread thread = new Thread(r);

            thread.run();*/

            String ho= Integer.toString(h);
            String mi=Integer.toString(m);
//            Intent i=new Intent(this,MyService.class);
//            i.putExtra("hours",h);
//            i.putExtra("mins",m);
//            i.putExtra("phone",ph_no);
//            i.putExtra("msg",msg);
//            startService(i);
        Calendar c = Calendar.getInstance();
        int sHour = c.get(Calendar.HOUR_OF_DAY);
        int sMin = c.get(Calendar.MINUTE);
        int t=(h-sHour)*60*60+(m-sMin)*60;
        String ti= String.valueOf(SystemClock.elapsedRealtime());
        String time = String.valueOf((SystemClock.elapsedRealtime()+(1000)+t));
        Log.i(tag,time);

//            br=new BroadcastReceiver() {
//                @Override
//                public void onReceive(Context context, Intent intent) {
//                    Log.i(tag,"sending");
//                    SmsManager smsManager = SmsManager.getDefault();
//                    smsManager.sendTextMessage(ph_no, null, msg, null, null);
//                    Toast.makeText(getApplicationContext(), "Sms Sent", Toast.LENGTH_LONG).show();
//                }
//            };
        Intent my_Intent=new Intent(this,MyReceiver.class);
            pi = PendingIntent.getBroadcast( this, 0, my_Intent,
                    0 );


            am = (AlarmManager)(this.getSystemService( Context.ALARM_SERVICE ));
        am.set( AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() +
                (1000*t), pi );
        //}




//
//        }
//        catch(Exception e)
//        {
//            Toast.makeText(getApplicationContext(),"Sms send failed",Toast.LENGTH_LONG).show();
//            e.printStackTrace();
//        }
    }
}