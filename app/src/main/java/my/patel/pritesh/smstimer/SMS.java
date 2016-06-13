package my.patel.pritesh.smstimer;

/**
 * Created by Pritesh on 6/12/2016.
 */
public class SMS {
    private int _id;
    private String _ph_no;
    private String _msg;
    private int _hour;
    private int _min;
    public SMS(){

    }

    public SMS(String _ph_no, String _msg, int _min, int _hour) {
        this._ph_no = _ph_no;
        this._msg = _msg;
        this._min = _min;
        this._hour = _hour;
    }

    public void set_hour(int _hour) {
        this._hour = _hour;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_min(int _min) {
        this._min = _min;
    }

    public void set_msg(String _msg) {
        this._msg = _msg;
    }

    public void set_ph_no(String _ph_no) {
        this._ph_no = _ph_no;
    }

    public  int get_hour() {
        return _hour;
    }

    public int get_id() {
        return _id;
    }

    public  int get_min() {
        return _min;
    }

    public  String get_msg() {
        return _msg;
    }

    public String get_ph_no() {
        return _ph_no;
    }
}
