package com.patel.pritesh.smstimer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class sms_list extends AppCompatActivity {
    public ListView lv;
    array ob=new array();
String tag="pritesh";
    public static String[] Phone=new String[5];
    public static String[] msg=new String[5];
    public static int[] hour=new int[5];
    public static int[] min=new int[5];
    Button setTimer;
    ListAdapter adapter;
    int f;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        f=0;
        for(int i=0;i<5;i++)
            if(Phone[i]!=null) {
                f = 1;
                break;
            }
        if(f==1)
        setContentView(R.layout.activity_sms_list);
        else
        setContentView(R.layout.no_sms);
        Log.i(tag,"smslist");
        Bundle bundle=getIntent().getExtras();
       if(bundle==null){return;}
        int flag=bundle.getInt("Flag");

        lv=(ListView)findViewById(R.id.listView);
        setTimer=(Button)findViewById(R.id.button);
        if(flag!=1){
        hour[array.select]=bundle.getInt("HOUR");
        min[array.select]=bundle.getInt("MIN");
        Phone[array.select]=bundle.getString("PHONE");
        msg[array.select]=bundle.getString("MSG");}

        adapter=new sms_list_adapter(this,Phone,msg,hour,min);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Phone[position]=Phone[position];
                if(Phone[position]!=null){

                Intent review = new Intent(getApplicationContext(),sms_review.class);
                review.putExtra("HOUR",hour[position]);
                review.putExtra("MIN",min[position]);
                review.putExtra("PHONE",Phone[position]);
                review.putExtra("MSG",msg[position]);
                review.putExtra("POS",position);
                startActivity(review);}

            }
        });

    }
    void setTimer(View view) {
        ob.sort();
        array.select=array.select;
        if (array.select == 5) {
            Toast.makeText(getApplicationContext(), "Sorry cannot create more than 5 timer", Toast.LENGTH_SHORT).show();
        } else{
            Intent intent = new Intent(this, Time_Picker.class);
        startActivity(intent);
         }
    }
}
