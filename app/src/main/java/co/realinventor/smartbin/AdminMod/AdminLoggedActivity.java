package co.realinventor.smartbin.AdminMod;

import androidx.appcompat.app.AppCompatActivity;
import co.realinventor.smartbin.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminLoggedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_logged);
    }

    public void complaintsSelected(View view){
        startActivity(new Intent(this, ViewComplaintActivity.class));
    }

    public void statusSelected(View view){
        startActivity(new Intent(this, ViewStatusActivity.class));
    }
}
