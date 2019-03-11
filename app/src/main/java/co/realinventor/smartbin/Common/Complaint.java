package co.realinventor.smartbin.Common;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Complaint {
    public String unique_id, msg, date, time, reviewed, senderName;

    public Complaint() {}

    public Complaint(String unique_id, String msg, String date, String time, String reviewed) {
        this.unique_id = unique_id;
        this.msg = msg;
        this.date = date;
        this.time = time;
        this.reviewed = reviewed;
    }

    public Task registerComplaint(String msg, String senderName){
        this.msg = msg;
        this.senderName = senderName;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        this.date = df.format(Calendar.getInstance().getTime());
        df = new SimpleDateFormat("HH:mm:ss");
        this.time = df.format(Calendar.getInstance().getTime());
        reviewed = "no";

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        unique_id = ref.child("complaints").push().getKey();

        return ref.child("complaints").child(unique_id).setValue(this);

    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReviewed() {
        return reviewed;
    }

    public void setReviewed(String reviewed) {
        this.reviewed = reviewed;
    }
}
