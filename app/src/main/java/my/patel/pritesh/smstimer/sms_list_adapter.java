package my.patel.pritesh.smstimer;

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
    int h,m;

    public static int[] Hour=new int[5];
    public static int[] Min=new int[5];
//   public static Button cncl;
    public sms_list_adapter(Context context,String[] Phone, String[] msg,int[] hour,int[] min) {
        super(context,R.layout.activity_sms_list_item,Phone);
        this.msg=msg;
        Hour=hour;
        Min=min;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.activity_sms_list_item,parent,false);
        String phone=getItem(position);
        TextView ph=(TextView) view.findViewById(R.id.Number);
       // TextView status=(TextView) view.findViewById(R.id.status);
        TextView msgs=(TextView)view.findViewById(R.id.msg);
        TextView clock=(TextView)view.findViewById(R.id.clock);
        //cncl=(Button)view.findViewById(R.id.cancel);
//        cncl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(),"cancle"+position,Toast.LENGTH_SHORT);
//            }
//        });
        ph.setText(phone);
        //String a=msg[position];
        msgs.setText(msg[position]);
        if(phone!=null) {
       //     status.setText("PENDING");
             h=Hour[position];
             m=Min[position];
            clock.setText(h+ ":" +m);
        }

            return view;
    }
}
