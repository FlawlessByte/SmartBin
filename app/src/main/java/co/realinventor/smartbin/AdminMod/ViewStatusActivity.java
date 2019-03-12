package co.realinventor.smartbin.AdminMod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import co.realinventor.smartbin.Common.Bin;
import co.realinventor.smartbin.Common.CollectionMessage;
import co.realinventor.smartbin.R;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewStatusActivity extends AppCompatActivity {
    private TextView textViewHead, statusTextView;
    private DatabaseReference statusRef;
    private MaterialButton button;
    private ProgressBar progressBar;
    private long cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_status);

        statusRef = FirebaseDatabase.getInstance().getReference();

        textViewHead = findViewById(R.id.textViewHead);
        statusTextView = findViewById(R.id.statusTextView);
        button = (MaterialButton) findViewById(R.id.acceptButton);
        progressBar = findViewById(R.id.progBar);

        textViewHead.setText("Please wait!");

        statusRef.child("001").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Bin bin = dataSnapshot.getValue(Bin.class);
                cost = bin.getAmount();
                if(bin != null){
                    progressBar.setVisibility(View.INVISIBLE);
                }
                if(bin.getStat()){
                    statusTextView.setTextColor(Color.RED);
                    statusTextView.setText("Full");
                    textViewHead.setText("Status");
                    button.setVisibility(View.VISIBLE);
                }
                else{
                    statusTextView.setTextColor(Color.GREEN);
                    statusTextView.setText("Not Full");
                    textViewHead.setText("Status");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    public void acceptButtonClicked(View view){
        //Send collection msg
        new CollectionMessage().makeCollectionMsg("Your garbage collection will be done today!").addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Toast.makeText(ViewStatusActivity.this, "The collection message has been sent to user!", Toast.LENGTH_SHORT).show();
                    clearTheBin();
                    updatePrice();
                    finish();
                }
                else{
                    Toast.makeText(ViewStatusActivity.this, "Some error, occured!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void clearTheBin(){
        //clear the users bin value to false
    }

    private void updatePrice(){
        cost += 25;
        statusRef.child("001").child("amount").setValue(cost);
    }
}
