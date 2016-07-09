package my.patel.pritesh.smstimer;

/**
 * Created by Pritesh on 6/6/2016.
 */
public class array {
    public static boolean a[]=new boolean[5];
    public static int select;
    public static void sort()
    {
        int i,flag=0;
        for(i=0;i<5;i++)
        {
                if(a[i]==false)
                {
                    flag=1;
                    break;
                }
        }
        if(flag==1)
        {
            a[i]=true;
            select=i;

        }
        else
            select=5;

    }
    static void arrange(int pos)
    {
        int i;
        for(i=pos;i<4;i++)
        {
            a[i]=a[i+1];
            sms_list.msg[i]=sms_list.msg[i+1];
            sms_list.Phone[i]=sms_list.Phone[i+1];
            sms_list.min[i]=sms_list.min[i+1];
            sms_list.hour[i]=sms_list.hour[i+1];
            Time_Picker.pendingIntent[i]=Time_Picker.pendingIntent[i+1];
        }
    }

}
