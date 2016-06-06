package com.patel.pritesh.smstimer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class sms_list extends AppCompatActivity {
    public ListView lv;
    public static String[] Phone=new String[5];;
    public static String[] msg=new String[5];;
    Button setTimer;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_list);
        Bundle bundle=getIntent().getExtras();
        if(bundle==null){return;}
        lv=(ListView)findViewById(R.id.listView);
        setTimer=(Button)findViewById(R.id.button);


        Phone[Time_Picker.i-1]=bundle.getString("PHONE");
        msg[Time_Picker.i-1]=bundle.getString("MSG");
        ListAdapter adapter=new sms_list_adapter(this,Phone,msg);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

    }
    void setTimer(View view)
    {
        Intent intent=new Intent(this,Time_Picker.class);
        startActivity(intent);
    }
}
