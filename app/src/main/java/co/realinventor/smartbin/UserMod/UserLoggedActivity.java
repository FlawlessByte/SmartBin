package co.realinventor.smartbin.UserMod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import co.realinventor.smartbin.MainActivity;
import co.realinventor.smartbin.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserLoggedActivity extends AppCompatActivity {
    private DatabaseReference ref;
    private String uid;
    private String bin_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_logged);

        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        ref = FirebaseDatabase.getInstance().getReference();
        ref.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                bin_id = user.getBin_id();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }



    }

    public void statusSelected(View view){
        startActivity(new Intent(this, StatusActivity.class).putExtra("bin_id",bin_id));
    }
    public void costSelected(View view){
        startActivity(new Intent(this, CostActivity.class).putExtra("bin_id",bin_id));
    }
    public void complaintSelected(View view){
        startActivity(new Intent(this, ComplaintActivity.class).putExtra("bin_id",bin_id));
    }
}
