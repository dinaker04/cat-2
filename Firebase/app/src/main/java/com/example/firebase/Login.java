package com.example.firebase;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity {
    EditText  email;
    EditText pass;
    Button button , gButton;
    TextView signup , forgotpass;
    FirebaseAuth auth;
    SharedPreferences sharedPreferences;
    GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 101;


//    private  static final String prefName = "myapp";
//    private  static final String keyEmail = "email";
//    private  static final String keyPass = "password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        button = findViewById(R.id.button);
        signup = findViewById(R.id.signup);
        forgotpass = findViewById(R.id.forgotpass);
        //gButton = findViewById(R.id.button1);
        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();



        gButton.setOnClickListener(v->{
            // Move the Google Sign-In flow initiation here
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();

            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });

        //sharedPreferences = getSharedPreferences(prefName,MODE_PRIVATE);




        forgotpass.setOnClickListener(v->{
            Intent intent = new Intent(Login.this, ForgotPass.class);
            startActivity(intent);
        });
        button.setOnClickListener(v-> {
            checkUser();
            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString(keyEmail,email.getText().toString());
//            editor.putString(keyPass,pass.getText().toString());
            editor.apply();
        });

        signup.setOnClickListener(v->{
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        });

//        gButton.setOnClickListener(v->{
//            signIn();
//        });
//


    }

    public void checkUser(){
        String usremail = email.getText().toString().trim();
        String usrpass = pass.getText().toString().trim();
        if(usremail.isEmpty()){
            email.setError("Email Empty");
        }

        if(usrpass.isEmpty()){
            pass.setError("Password Empty");
        }
        if(!usremail.isEmpty() && !usrpass.isEmpty()){
            auth.signInWithEmailAndPassword(usremail,usrpass).
                    addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(Login.this,MainActivity.class));
                            startActivity(new Intent(Login.this, Firebase.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }


}