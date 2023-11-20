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

public class SignInWithPasswordActivity extends AppCompatActivity {

    private static final String TAG = SignInWithPasswordActivity.class.getName();

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_with_password);

        //Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        EditText inputEmail = findViewById(R.id.editTextSigninEmail);
        EditText inputPassword =findViewById(R.id.editTextSigninPassword);

        findViewById(R.id.btnSignin).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Log.d(TAG,"singInWithEmail:success");
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    updateUI(user);
                                }
                            }
                        });
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
                Toast.makeText(SignInWithPasswordActivity.this,"Please verify your email address",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Intent intent = new Intent(SignInWithPasswordActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}