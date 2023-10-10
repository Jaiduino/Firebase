package com.example.Jayesh.firebaselogin.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Jayesh.firebaselogin.Entitys.User;
import com.example.Jayesh.firebaselogin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationActivity extends AppCompatActivity {
EditText editName,editEmailId,editPassword,editCity,editPhone;
Button SignUpButton;
TextView TextClickHere;
private String EmailId;
private String pass;
FirebaseAuth mAuth;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        TextClickHere = findViewById(R.id.TextViewClickHere);
        SignUpButton = findViewById(R.id.ButtonSignUp);
        editName = findViewById(R.id.EditName);
        editCity = findViewById(R.id.EditCity);
        editEmailId = findViewById(R.id.EditEmailId);
        editPassword = findViewById(R.id.EditPassword);
        editPhone = findViewById(R.id.EditMobile);
        mAuth = FirebaseAuth.getInstance();


        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user=getUserData();
                EmailId = editEmailId.getText().toString();
                pass = editPassword.getText().toString();
               if (!EmailId.equals("")){
                   if (!pass.equals("")){
                       Log.e("jay", "All OK");
                           SignUp(pass,user);
                   }else {
                       Log.e("jay", "empty password");
                       generateToast("Enter password!!");
                   }
               }else {
                   Log.e("jay", "empty Email");
                   generateToast("Enter EmailId!!");
               }
            }
        });
    }

    private User getUserData() {
        User user = new User();
        user.setName(editName.getText().toString());
        user.setCity(editCity.getText().toString());
        user.setPhone(editPhone.getText().toString());
        user.setEmailId(editEmailId.getText().toString());
        if (!user.getEmailId().isEmpty()){
            if (!user.getCity().isEmpty()){
               if (!user.getName().isEmpty()){
                   if (!user.getPhone().isEmpty()){
                          return user;
                   }else {
                       generateToast("Enter Phone No!!");
                   }
               }else {
                   generateToast("Enter Name!!");
               }
            }else {
                generateToast("Enter City!!");
            }
        }else {
            generateToast("Enter EmailId!!");
        }
return null;
    }

    private void SignUp(String pass,User user) {
        mAuth.createUserWithEmailAndPassword(user.getEmailId(), pass)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.e("jay", "Register!!!"+user.toString());
                            String Uid = mAuth.getCurrentUser().getUid();
                            Log.e("jay", "UID Is "+Uid);
                            SaveUserDetails(Uid,user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("jay", "createUserWithEmail:failure", task.getException());


                        }
                    }
                });
    }

    private void SaveUserDetails(String uid, User user) {


        Log.e("jay", "Inside SaveUser");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Users");
        DatabaseReference childRef = databaseReference.child(uid);
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.e("jay", "SaveUser OK..");
                childRef.setValue(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("jay", "SaveUser Failed...");
            }
        });

    }

    private void generateToast(String msg) {
        Toast.makeText(RegistrationActivity.this,""+msg,Toast.LENGTH_SHORT).show();
    }


}