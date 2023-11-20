package com.otaku.otaku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getName();
    private FirebaseAuth firebaseAuth;
    private EditText inputEmail,inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        //get user inputs
        inputEmail = findViewById(R.id.editTextSignupEmail);
        inputPassword = findViewById(R.id.editTextSignupPassword);

        findViewById(R.id.btnSignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = inputEmail.getText().toString();
                String userPassword = inputPassword.getText().toString();

                Log.d(TAG,"User email: "+userEmail+ "User Password: "+userPassword);

                firebaseAuth.createUserWithEmailAndPassword(userEmail,userPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Log.d(TAG,"createUserWithEmail:success");
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    user.sendEmailVerification();
                                    updateUI(user);
                                    finish();
                                }else{
                                    Log.e(TAG, "createUserWithEmailAndPassword:failed");
                                    Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        findViewById(R.id.textViewSignin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Check if user is signed in (non-null) and update UI accordingly

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            Log.i(TAG, firebaseUser.getEmail());
            updateUI(firebaseUser);
        }else{
            Log.i(TAG,"NO USER AVAILABLE");
        }
    }


    private void updateUI(FirebaseUser user){
        if (user != null){

            if (!user.isEmailVerified()){
                Toast.makeText(RegisterActivity.this,"Please verify your email address",Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(RegisterActivity.this, SignInWithPasswordActivity.class);
            startActivity(intent);
            finish();
        }
    }
}