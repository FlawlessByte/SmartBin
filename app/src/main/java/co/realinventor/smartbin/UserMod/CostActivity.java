package co.realinventor.smartbin.UserMod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import co.realinventor.smartbin.Common.Bin;
import co.realinventor.smartbin.R;

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
    private TextView textViewCost;
    private ProgressBar progressBarCost;
    private DatabaseReference statusRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost);

        textViewCost = findViewById(R.id.textViewCost);
        progressBarCost = findViewById(R.id.progressBarCost);

        statusRef = FirebaseDatabase.getInstance().getReference();

        statusRef.child("001").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Bin bin = dataSnapshot.getValue(Bin.class);
                if(bin != null){
                    progressBarCost.setVisibility(View.GONE);
                    textViewCost.setVisibility(View.VISIBLE);
                }
                if(bin.getStat()){
                    textViewCost.setText("Rs. "+bin.getAmount()+"");
                }
                else{
                    textViewCost.setText("Not available!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
