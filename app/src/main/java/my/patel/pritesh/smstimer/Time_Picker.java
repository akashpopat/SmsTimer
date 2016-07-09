package my.patel .pritesh.smstimer;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class Time_Picker extends Activity {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1234;
    TimePicker time_picker;
    String tag="pritesh";
    public static AlarmManager[] alarmManager=new AlarmManager[5];
    public static PendingIntent[] pendingIntent = new PendingIntent[5];
    EditText message;
    public static String  name = null;
    EditText phone;
    Intent myIntent;
    public static String Phone;
    private static final int RESULT_PICK_CONTACT = 85500;
    public static String Message;
    public int i;
    long h,m;

   // final private int REQUEST_CODE_ASK_PERMISSIONS = 1234;

    array ob=new array();
   // static ArrayList<PendingIntent> pendingarray=new ArrayList<>(5);
    InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.patel.pritesh.smstimer.R.layout.activity_time_picker);
        mInterstitialAd = new InterstitialAd(this);
//        if (ContextCompat.checkSelfPermission(getApplicationContext(),
//                Manifest.permission.INTERNET)
//                != PackageManager.PERMISSION_GRANTED)
//        {
//            Log.i(tag,"inside if");
//            ActivityCompat.requestPermissions((Activity) getApplicationContext(),
//                    new String[]{Manifest.permission.INTERNET},REQUEST_CODE_ASK_PERMISSIONS);
//        }
        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("2217D213C9103D43B6112EB151986803")
                .build();

        mInterstitialAd.loadAd(adRequest);
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
                         // No explanation needed, we can request the permission.
                Log.i(tag,"insede time");
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.

        }

        Log.i(tag,"time");
        message=(EditText)findViewById(my.patel.pritesh.smstimer.R.id.message);
        phone=(EditText)findViewById(my.patel.pritesh.smstimer.R.id.Phone) ;
        time_picker = (TimePicker) findViewById(my.patel.pritesh.smstimer.R.id.timePicker);
        time_picker.setIs24HourView(true);


    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getApplicationContext(),"got sms permission",Toast.LENGTH_SHORT);
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    public void setAlarm(View view) {
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });
         Phone = phone.getText().toString();
         Message = message.getText().toString();
         phone.setText("");
         message.setText("");
         //Validating if any field empty
         if (Phone.length() >= 10 && Message.length() > 0)
         {
             Intent service=new Intent(this,alarmService.class);
             service.putExtra("Phone",Phone);
             service.putExtra("Message",Message);
             int currentApiVersion = android.os.Build.VERSION.SDK_INT;
             if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                 service.putExtra("hour", time_picker.getHour());
                 service.putExtra("min", time_picker.getMinute());
             }
             else
             {

                 service.putExtra("hour",time_picker.getCurrentHour());
                 service.putExtra("min",time_picker.getCurrentMinute());
             }
             startService(service);
//             array.sort();
//
//             Toast.makeText(this.getApplicationContext(), "Your sms will be sent soon", Toast.LENGTH_SHORT).show();
//             //Getting Calender Reference
//             Calendar cal = Calendar.getInstance();
//             int currentApiVersion = android.os.Build.VERSION.SDK_INT;
//             if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
//                 cal.set(Calendar.MINUTE, time_picker.getMinute());
//                 cal.set(Calendar.HOUR_OF_DAY, time_picker.getHour());
//             } else {
//                 //Setting the date and time from the time picker
//                 cal.set(Calendar.MINUTE, time_picker.getCurrentMinute());
//                 cal.set(Calendar.HOUR_OF_DAY, time_picker.getCurrentHour());
//             }
//             //System clock time
//             Calendar c=Calendar.getInstance();
//             Long a ;//=(long) (Calendar.getInstance().get(Calendar.SECOND) * 1000);
//             if(cal.get(Calendar.HOUR_OF_DAY)<c.get(Calendar.HOUR_OF_DAY))
//                 h=(cal.get(Calendar.HOUR_OF_DAY)+24-c.get(Calendar.HOUR_OF_DAY))*60;
//             else
//                h=(cal.get(Calendar.HOUR_OF_DAY)-c.get(Calendar.HOUR_OF_DAY))*60;
//             m=(cal.get(Calendar.MINUTE)-c.get(Calendar.MINUTE));
//             a=(m+h)*60-c.get(Calendar.SECOND);
//             myIntent = new Intent(this, MyReceiver.class);
//             myIntent.putExtra("pos",array.select);
//            myIntent.setAction(MY_ACTION);
//             //Pending Intent for sending the intent afterwards
//             pendingIntent[array.select] = PendingIntent.getBroadcast(this.getApplicationContext(), array.select, myIntent,0);
//             alarmManager[array.select] = (AlarmManager) (this.getSystemService(Context.ALARM_SERVICE));
//
//             alarmManager[array.select].set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()+a*1000, pendingIntent[array.select]);

            // pendingarray.add(pendingIntent[array.select]);

//             sms_list.Phone[array.select]=Phone;
//             Intent back = new Intent(this, sms_list.class);
//             back.putExtra("PHONE", Phone);
//             back.putExtra("Flag",2);
//             back.putExtra("MSG", Message);
//             back.putExtra("HOUR", (int) cal.get(Calendar.HOUR_OF_DAY));
//             back.putExtra("MIN", (int) cal.get(Calendar.MINUTE));
//             back.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//             startActivity(back);
         }
         else
         {
               Toast.makeText(this.getApplicationContext(), "Please Fill Up All The Details", Toast.LENGTH_SHORT).show();
               array.a[array.select]=false;
               ob.sort();
         }

    }
}
