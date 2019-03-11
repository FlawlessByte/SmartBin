package co.realinventor.smartbin.UserMod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import co.realinventor.smartbin.Common.Complaint;
import co.realinventor.smartbin.R;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ComplaintActivity extends AppCompatActivity {
    private TextInputEditText complaintEditText;
    private String complaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        complaintEditText = findViewById(R.id.textInputComplaint);

    }

    public void sendButtonClicked(View view){
        complaint = complaintEditText.getText().toString();
        if(TextUtils.isEmpty(complaint))
            Toast.makeText(this, "Please write some complaint!", Toast.LENGTH_SHORT).show();
        else{
            new Complaint().registerComplaint(complaint, FirebaseAuth.getInstance().getCurrentUser().getDisplayName()).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(ComplaintActivity.this, "Your complaint has been sent to admin!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ComplaintActivity.this, UserLoggedActivity.class));
                        finish();
                    }
                    else
                        Toast.makeText(ComplaintActivity.this, "Some error occured!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
