package com.example.Jayesh.firebaselogin.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Jayesh.firebaselogin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {
private TextView textViewSignUp,textViewForgetPassword;
private EditText editEmail,editPassword;
private Button buttonLogin;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textViewSignUp = findViewById(R.id.TextViewSignUp);
        textViewForgetPassword = findViewById(R.id.TextViewForgetPassword);
        editEmail = findViewById(R.id.EditEmail);
        editPassword = findViewById(R.id.EditPassword);
        buttonLogin = findViewById(R.id.ButtonLogin);
        mAuth = FirebaseAuth.getInstance();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailId = editEmail.getText().toString();
                String pass = editPassword.getText().toString();
                if (!emailId.equals("")){
                   if (!pass.equals("")){
                         Login(emailId,pass);
                           }else {
                      generateToast("Enter Password!!!");
                       }
                }else {
                    generateToast("Enter EmailId!!!");
                }
            }
        });
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });
    }

    private void Login(String emailId, String pass) {
        mAuth.signInWithEmailAndPassword(emailId,pass)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                             startActivity(new Intent(LoginActivity.this, MainActivity.class));
                             finish();

                        } else {
                            // If sign in fails, display a message to the user.

                                generateToast("Something Went Wrong !!!");
                                Log.e("login","status"+task.getException());
                            }

                        }
                });
    }

    private void generateToast(String msg) {
        Toast.makeText(LoginActivity.this,msg, Toast.LENGTH_SHORT).show();
    }
}