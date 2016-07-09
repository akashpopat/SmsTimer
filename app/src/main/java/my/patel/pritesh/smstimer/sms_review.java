package my.patel.pritesh.smstimer;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class sms_review extends AppCompatActivity {
    TextView clock1,ph,msg,status;
    int pos;
    int a,b;
    PendingIntent pend;
    array ob=new array();
    String tag="sd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_review);
        Log.i(tag,"review" );
        Button cncl=(Button)findViewById(R.id.cancel);

        Bundle bundle=getIntent().getExtras();

         status=(TextView)findViewById(R.id.status);
         clock1=(TextView)findViewById(R.id.clock1);
         ph=(TextView) findViewById(R.id.Number);
      //   status=(TextView) findViewById(R.id.status);
        msg=(TextView)findViewById(R.id.msg);

        clock1.setText(bundle.getInt("HOUR")+":"+bundle.getInt("MIN"));
        ph.setText(bundle.getString("PHONE"));
        msg.setText(bundle.getString("MSG"));
     //   status.setText("PENDING");
        pos=bundle.getInt("POS");
      //  status.setText("CANCELLED");
//

        if (cncl != null) {
            cncl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    //array.a[pos]=false;
                    if(array.select>pos)
                        array.select=pos;
                    ob.arrange(pos);
                    sms_list.min[4]=0;
                    sms_list.hour[4]=0;
                    sms_list.Phone[4]=null;
                    sms_list.msg[4]=null;
                    array.a[4]=false;
                   // pend=  Time_Picker.pendingarray.get(pos);
                    Time_Picker.alarmManager[pos].cancel(Time_Picker.pendingIntent[pos]);
                    //Time_Picker.pendingarray.remove(pos);
                    Intent in = new Intent(getApplicationContext(),sms_list.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    in.putExtra("Flag",1);
                    startActivity(in);

                }
            });
        }




    }


}
