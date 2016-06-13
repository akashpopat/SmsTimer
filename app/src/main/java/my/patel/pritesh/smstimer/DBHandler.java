package my.patel.pritesh.smstimer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pritesh on 6/12/2016.
 */
public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABSE_VERSION=1;
    private static final String DATABASE_NAME="SMS.db";
    private static final String TABLE_NAME="SMS";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_PHONE="Phone";
    private static final String COLUMN_MSG="Msg";
    private static final String COLUMN_HOUR="Hour";
    private static final String COLUMN_MIN="Min";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="Create table "+TABLE_NAME+"("+
                COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT "+
                COLUMN_PHONE + " TEXT " +
                COLUMN_MSG + " TEXT " +
                COLUMN_HOUR + " INTEGER " +
                COLUMN_MIN + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_NAME);
        onCreate(db);
    }

    public void addSMS(SMS sms){
        ContentValues value = new ContentValues();
        value.put(COLUMN_PHONE,sms.get_ph_no());
        value.put(COLUMN_MSG,sms.get_msg());
        value.put(COLUMN_HOUR,sms.get_hour());
        value.put(COLUMN_MIN,sms.get_min());
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_NAME,null,value);
        db.close();
    }
    public void deleteSMS(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE " + COLUMN_ID+" =\""+id+"\";");
    }
    public String printsms(){
        String  dbString="";
        SQLiteDatabase db=getReadableDatabase();
        String query="SELECT * FROM "+TABLE_NAME+" WHERE 1";
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("Phone"))!=null){
                dbString+=c.getString((c.getColumnIndex("_id")));
            }
        }
        db.close();
        return dbString;
    }
}
