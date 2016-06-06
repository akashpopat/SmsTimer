package com.patel.pritesh.smstimer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Pritesh on 6/6/2016.
 */
public class sms_list_adapter extends ArrayAdapter<String> {
    String[] msg=new String[5];
    public sms_list_adapter(Context context,String[] Phone, String[] msg) {
        super(context,R.layout.activity_sms_list_item,Phone);
        this.msg=msg;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.activity_sms_list_item,parent,false);
        String phone=getItem(position);
        TextView ph=(TextView) view.findViewById(R.id.Number);
        TextView status=(TextView) view.findViewById(R.id.status);
        TextView msgs=(TextView)view.findViewById(R.id.msg);
        ph.setText(phone);
        //String a=msg[position];
        msgs.setText(msg[position]);
        if(phone!=null)
        status.setText("PENDING");

        return view;
    }
}
