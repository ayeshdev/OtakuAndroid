package com.otaku.otaku;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getName();
   private FirebaseAuth auth;
   private FirebaseUser user;
   private SignInClient signInClient;

   private CallbackManager callbackManager;



    public LoginActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        signInClient = Identity.getSignInClient(getApplicationContext());

        //Initialize Facebook Login Button
        callbackManager = CallbackManager.Factory.create();


        //START CONTINUE WITH GOOGLE
        findViewById(R.id.btnContinueWithGoogle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetSignInIntentRequest signInIntentRequest = GetSignInIntentRequest
                        .builder()
                        .setServerClientId(getString(R.string.web_client_id))
                        .build();

                Task<PendingIntent> signInIntent = signInClient.getSignInIntent(signInIntentRequest);

                signInIntent.addOnSuccessListener(new OnSuccessListener<PendingIntent>() {
                    @Override
                    public void onSuccess(PendingIntent pendingIntent) {
                        IntentSenderRequest intentSenderRequest = new IntentSenderRequest.Builder(pendingIntent)
                                .build();

                        signInLauncher.launch(intentSenderRequest);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });


        //END CONTINUE WITH GOOGLE


        //START CONTINUE WITH FACEBOOK

        findViewById(R.id.btnContinueWithFacebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performFacebookLogin();
            }
        });

        //END CONTINUE WITH FACEBOOK

        findViewById(R.id.btnSignInWithPassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignInWithPasswordActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.textViewSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    //START FACEBOOK LOGIN
    private void performFacebookLogin(){
        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("email","public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i(TAG,"facebook:onSuccess: " +loginResult.getAccessToken());
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(@NonNull FacebookException e) {
                Log.e(TAG,e.getMessage());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    private void handleFacebookAccessToken(AccessToken token){
        Log.d(TAG,"handleFacebookAccessToken: "+ token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG,"signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();
                            updateUI(user);
                        }else{
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    //END FACEBOOK LOGIN


    //FIREBASE AUTHENTICATE WITH GOOGLE

    private void firebaseAuthWithGoogle(String googleIdToken){
        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleIdToken, null);
        Task<AuthResult> authResultTask = auth.signInWithCredential(authCredential);

        authResultTask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FirebaseUser currentUser = auth.getCurrentUser();
                updateUI(currentUser);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(user != null && user.isEmailVerified()){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    //UPDATE UI
    private void updateUI(FirebaseUser user){
        if (user != null){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void handleSignInResult(Intent intent){
        try {
            SignInCredential signInCredential = signInClient
                    .getSignInCredentialFromIntent(intent);

            String googleIdToken = signInCredential.getGoogleIdToken();

            firebaseAuthWithGoogle(googleIdToken);

        }catch(ApiException e){
            Log.e(TAG,e.getMessage());
        }
    }

    private final ActivityResultLauncher<IntentSenderRequest> signInLauncher = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            handleSignInResult(result.getData());
        }
    });
}