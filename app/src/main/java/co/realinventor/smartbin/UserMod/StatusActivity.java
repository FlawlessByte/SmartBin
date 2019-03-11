package co.realinventor.smartbin.UserMod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import co.realinventor.smartbin.Common.Bin;
import co.realinventor.smartbin.R;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StatusActivity extends AppCompatActivity {

    private RelativeLayout msgLayout, binLayout;
    private BottomNavigationView bottomNavigationView;
    private DatabaseReference statusRef;
    private TextView textViewStatus;
    private ImageView imageViewStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        Fragment fragment = new BinFragment();
        loadFragment(fragment);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_msg:
                                msgActionChosen();
                                break;
                            case R.id.action_bin:
                                binActionChosen();
                                break;
                        }
                        return true;
                    }
                });

    }

    private void msgActionChosen(){
        Log.d("bottommenu", "msg");
        Fragment fragment = new MessageFragment();
        loadFragment(fragment);
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void binActionChosen(){
        Log.d("bottommenu", "bin");
        Fragment fragment = new BinFragment();
        loadFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
