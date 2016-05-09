package com.example.pritesh.smstimer;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button send;
    EditText phone;
    EditText message;
    NumberPicker hour;
    NumberPicker min;
    TextView time;
    int h=0,m=0;
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
        hour.setMaxValue(24);
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
                String ph_no =phone.getText().toString();
                String msg=message.getText().toString();
                if(ph_no.length()>0&&msg.length()>0)
                    sendMessage(ph_no,msg);
                else
                {
                    Toast.makeText(getApplicationContext(), "Fill up all the details", Toast.LENGTH_LONG).show();
                }
            }
        });



    }


    private void sendMessage(final String ph_no, final String msg) {

        try {

            Runnable r = new Runnable() {
                @Override
                public void run() {
                    Calendar c = Calendar.getInstance();
                    int sHour=c.get(Calendar.HOUR);
                    int sMin=c.get(Calendar.MINUTE);
                    if(sHour==h&&sMin==m) {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(ph_no, null, msg, null, null);
                        Toast.makeText(getApplicationContext(), "Sms Sent", Toast.LENGTH_LONG).show();

                    }
                }
            }




        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),"Sms send failed",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}