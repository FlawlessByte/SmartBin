package co.realinventor.smartbin.Common;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CollectionMessage {
    public String unique_id, msg, time, date, bin_id;

    public CollectionMessage() {
    }

    public Task makeCollectionMsg(String msg, String bin_id){
        this.msg = msg;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        this.date = df.format(Calendar.getInstance().getTime());
        df = new SimpleDateFormat("HH:mm:ss");
        this.time = df.format(Calendar.getInstance().getTime());
        this.bin_id = bin_id;

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        unique_id = ref.child("collection_msgs").push().getKey();

        return ref.child("collection_msgs").child(unique_id).setValue(this);
    }

    public String getBin_id() {
        return bin_id;
    }

    public void setBin_id(String bin_id) {
        this.bin_id = bin_id;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
