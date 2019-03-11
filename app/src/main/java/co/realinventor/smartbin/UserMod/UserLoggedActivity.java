package co.realinventor.smartbin.UserMod;

import androidx.appcompat.app.AppCompatActivity;
import co.realinventor.smartbin.MainActivity;
import co.realinventor.smartbin.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class UserLoggedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_logged);

        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }



    }

    public void statusSelected(View view){
        startActivity(new Intent(this, StatusActivity.class));
    }
    public void costSelected(View view){
        startActivity(new Intent(this, CostActivity.class));
    }
    public void complaintSelected(View view){
        startActivity(new Intent(this, ComplaintActivity.class));
    }
}
