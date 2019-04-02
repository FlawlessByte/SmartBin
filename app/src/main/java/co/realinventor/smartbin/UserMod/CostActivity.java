package co.realinventor.smartbin.UserMod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import co.realinventor.smartbin.Common.Bin;
import co.realinventor.smartbin.R;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CostActivity extends AppCompatActivity {
    private TextView textViewCost, textView;
    private ProgressBar progressBarCost;
    private DatabaseReference statusRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost);

        String bin_id = getIntent().getStringExtra("bin_id");

        textViewCost = findViewById(R.id.textViewCost);
        textView = findViewById(R.id.textView);
        progressBarCost = findViewById(R.id.progressBarCost);

        statusRef = FirebaseDatabase.getInstance().getReference();

        statusRef.child(bin_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Bin bin = dataSnapshot.getValue(Bin.class);
                if(bin != null){
                    progressBarCost.setVisibility(View.GONE);
                    textViewCost.setVisibility(View.VISIBLE);

                    if(bin.getStat()){
                        textViewCost.setText("Rs. "+bin.getAmount()+"");
                    }
                    else{
                        textViewCost.setText("Not available!");
                    }
                }
                else{
                    textViewCost.setVisibility(View.VISIBLE);
                    textView.setText("Oops!");
                    textView.setTextSize(22);
                    textViewCost.setText("You have no bin associated with this account! Please contact admin.");
                    textViewCost.setTextColor(Color.GRAY);
                    textViewCost.setTextSize(18);
                    progressBarCost.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
