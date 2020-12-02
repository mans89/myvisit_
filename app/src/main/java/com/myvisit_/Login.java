package com.myvisit_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.net.Uri;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.URI;
import java.net.URL;


public class Login extends AppCompatActivity {
    private EditText email, password;
    private Button btnLogIn;
    private TextView tvSignIn;
    private FirebaseAuth mAuth;
    private String  mEmail , mPassword ;

    private static final String TAG = "Login";
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email = findViewById(R.id.loginemail);
        password = findViewById(R.id.loginpassword);
        btnLogIn = findViewById(R.id.loginbutton);

        mAuth = FirebaseAuth.getInstance();

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                log();

            }
        });
    }

    public void log () {
        mEmail = email.getText().toString();
        mPassword = password.getText().toString();

        if(!TextUtils.isEmpty(mEmail) && !TextUtils.isEmpty(mPassword) ) {

            mAuth.signInWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        //Log.d("Login", "signInWithEmail:success");
                        Toast.makeText(Login.this, "Authentication successfull.", Toast.LENGTH_SHORT).show();

                        //FirebaseUser user = mAuth.getCurrentUser();
                        //Intent intent = new Intent(getApplicationContext(), Home.class);
                        //startActivity(intent);
                        Intent i = new Intent(Login.this , loggED.class);
                        startActivity(i);
                        //updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        //Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        //if (user != null) {
                        // Name, email address, and profile photo Url
                        //    String name = user.getDisplayName();
                        //  String email = user.getEmail();
                        //Uri photoUrl = user.getPhotoUrl();

                        // Check if user's email is verified
                        //   boolean emailVerified = user.isEmailVerified();

                        // The user's ID, unique to the Firebase project. Do NOT use this value to
                        // authenticate with your backend server, if you have one. Use
                        // FirebaseUser.getIdToken() instead.
                        //  String uid = user.getUid();
                        // }

                        //  updateUI(null);
                    }
                }
            });

        }else {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

        }
    }


   /*     mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

                if(mFirebaseUser != null){
                    Toast.makeText(Login.this, "you are logged in" , Toast.LENGTH_SHORT).show();
                    Log.d(TAG , "onAuthStateChanged:signed_in:" + mFirebaseUser.getUid());
                    Intent i = new Intent(Login.this , loggED.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(Login.this, "Please login" , Toast.LENGTH_SHORT).show();

                }
            }
        };

        btnLogIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

               // String mFullName = fullname.getText().toString();

                  String mEmail = email.getText().toString();
                   String mPassword = password.getText().toString();

              //  String mRepeatPassword = repeatpassword.getText().toString();


             //   String mGender= gender.getText().toString();
             //   String mDateofbirth = dateofbirth.getText().toString();


                if (mEmail.isEmpty()){
                    email.setError("Please enter your email");
                    email.requestFocus();

                }
                else if (mPassword.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();

                }

                else if ( mEmail.isEmpty() && mPassword.isEmpty() ){
                    Toast.makeText(Login.this , "fields Are Empty!" , Toast.LENGTH_SHORT).show();

                }

                else if (  !mEmail.isEmpty() && !mPassword.isEmpty() ){

                    mFirebaseAuth.signInWithEmailAndPassword(mEmail,mPassword).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(Login.this , "Login Error" , Toast.LENGTH_SHORT).show();
                            }
                            else    {
                                Intent  inToHome = new Intent(Login.this, loggED.class);
                                startActivity(inToHome);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(Login.this , "Error has Occurred" , Toast.LENGTH_SHORT).show();


                }


            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intToforgotPass = new Intent(Login.this , SignUp.class);
                startActivity(intToforgotPass);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);


    }*/
}

