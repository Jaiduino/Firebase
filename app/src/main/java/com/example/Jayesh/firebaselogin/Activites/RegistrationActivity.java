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
        EmailId = editEmailId.getText().toString();
        pass = editPassword.getText().toString();
        user=getUserData();
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (!EmailId.isEmpty()){
                   if (!pass.isEmpty()){
                           SignUp(EmailId,pass);
                   }else {
                       generateToast("Enter EmailId!!");
                   }
               }else {
                   generateToast("Enter Password!!");
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

    private void SignUp(String emailId, String pass) {
        mAuth.createUserWithEmailAndPassword(emailId, pass)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.e("register", "Register!!!"+user.toString());

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("register", "createUserWithEmail:failure", task.getException());


                        }
                    }
                });
    }

    private void generateToast(String msg) {
        Toast.makeText(RegistrationActivity.this,""+msg,Toast.LENGTH_SHORT).show();
    }


}