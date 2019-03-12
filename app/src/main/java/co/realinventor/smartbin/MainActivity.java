package co.realinventor.smartbin;

import androidx.appcompat.app.AppCompatActivity;
import co.realinventor.smartbin.AdminMod.AdminLoggedActivity;
import co.realinventor.smartbin.AdminMod.AdminLoginActivity;
import co.realinventor.smartbin.UserMod.UserLoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void userModeSelected(View view){
        startActivity(new Intent(this, UserLoginActivity.class));
    }

    public void adminModeSelected(View view){
        startActivity(new Intent(this, AdminLoginActivity.class));
    }
}
