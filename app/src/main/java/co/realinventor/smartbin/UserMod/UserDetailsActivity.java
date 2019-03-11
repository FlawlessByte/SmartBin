package co.realinventor.smartbin.UserMod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDetailsActivity extends AppCompatActivity {
    private TextInputEditText textInputUserPhone, textInputUserAddress, textInputUserName;
    private String name, address, phone, uid;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        textInputUserAddress = findViewById(R.id.textInputUserAddress);
        textInputUserName = findViewById(R.id.textInputUserName);
        textInputUserPhone = findViewById(R.id.textInputUserPhone);

        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ref = FirebaseDatabase.getInstance().getReference();


    }

    public void userSignUpButtonClicked(View view){
        name = textInputUserName.getText().toString();
        address = textInputUserAddress.getText().toString();
        phone = textInputUserPhone.getText().toString();

        if((TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(phone))){
            Toast.makeText(this, "Fill all the fields and try again!", Toast.LENGTH_SHORT).show();
        }
        else{
            User user = new User();
            user.setUid(uid);
            user.setName(name);
            user.setAddress(address);
            user.setPhone(phone);
            user.setBin("not full");
            user.setBill("");


            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(name).build();

            FirebaseAuth.getInstance().getCurrentUser().updateProfile(profileUpdates);
            
            ref.child("users").child(uid).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(UserDetailsActivity.this, "Your info stored successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UserDetailsActivity.this, UserLoggedActivity.class));
                        finish();
                    }
                    else {
                        Toast.makeText(UserDetailsActivity.this, "Some error occurred!", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().getCurrentUser().delete();
                    }
                }
            });
        }

    }
}
