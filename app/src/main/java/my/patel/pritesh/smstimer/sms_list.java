package my.patel.pritesh.smstimer;
/*

            android:taskAffinity=""
            android:alwaysRetainTaskState="true"*/
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;

public class sms_list extends AppCompatActivity {
    public ListView lv;
    SMS sms[]=new SMS[5];
    private AdView mAdView;
    String tag = "pritesh";
    public static String[] Phone = new String[5];
    public static String[] msg = new String[5];
    public static int[] hour = new int[5];
    public static int[] min = new int[5];
    Button setTimer;
    ListAdapter adapter;
    private FirebaseAnalytics mFirebaseAnalytics;

    int f;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        f = 0;
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        for (int i = 0; i < 5; i++)
            if (Phone[i] != null) {
                f = 1;
                break;
            }
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED)
        {
            Log.i(tag,"inside smstimer");
            ActivityCompat.requestPermissions((Activity) getApplicationContext(),
                    new String[]{Manifest.permission.INTERNET},REQUEST_CODE_ASK_PERMISSIONS);
        }

        if (f == 1)
        {
            setContentView(R.layout.activity_sms_list);
            Bundle bundle = getIntent().getExtras();
            if (bundle == null) {
                return;
            }
            int flag = bundle.getInt("Flag");

            lv = (ListView) findViewById(R.id.listView);
            if (flag != 1) {
                f=array.select;
                hour[array.select] = bundle.getInt("HOUR");
                min[array.select] = bundle.getInt("MIN");
                Phone[array.select] = bundle.getString("PHONE");
                msg[array.select] = bundle.getString("MSG");
            }
            adapter = new sms_list_adapter(this, Phone, msg, hour, min);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Phone[position] = Phone[position];
                    if (Phone[position] != null) {

                        Intent review = new Intent(getApplicationContext(), sms_review.class);
                        review.putExtra("HOUR", hour[position]);
                        review.putExtra("MIN", min[position]);
                        review.putExtra("PHONE", Phone[position]);
                        review.putExtra("MSG", msg[position]);
                        review.putExtra("POS", position);
                        startActivity(review);
                    }

                }
            });
        }
        else
            setContentView(R.layout.no_sms);
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("2217D213C9103D43B6112EB151986803")
                .build();
        mAdView.loadAd(adRequest);
        Log.i(tag, "smslist");
    }
    public void setTimer(View view)
    {



        if (array.select == 5) {
            Toast.makeText(getApplicationContext(), "Sorry cannot create more than 5 timer", Toast.LENGTH_SHORT).show();
        } else{
            Intent intent = new Intent(this, Time_Picker.class);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted

                    setTimer(getCurrentFocus());

                } else {
                    // Permission Denied
                    Toast.makeText(sms_list.this, "Send SMS Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
