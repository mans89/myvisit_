package com.myvisit_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.String;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    EditText fullname , email, password , repeatpassword , gender, dateofbirth;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        mFirebaseAuth = FirebaseAuth.getInstance();

       fullname = findViewById(R.id.signupfullname);
        email = findViewById(R.id.signupemail);
        password = findViewById(R.id.signuppassword);
        repeatpassword = findViewById(R.id.signuprepeatpassword);

        gender = findViewById(R.id.signupgender);
        dateofbirth = findViewById(R.id.signupDOB);
        btnSignUp = findViewById(R.id.signupbutton);

        //  tvSignIn = findViewById(R.id.signupclicklogin);

        /*/tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Fragment1.class);
                startActivity(i);

            }
        });
        */


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mFullName = fullname.getText().toString();
                String mEmail = email.getText().toString();
                String mPassword = password.getText().toString();
                String mRepeatPassword = repeatpassword.getText().toString();
                String mGender= gender.getText().toString();
                String mDateofbirth = dateofbirth.getText().toString();

                if (mFullName.isEmpty()){
                    fullname.setError("Please enter your Full Name");
                    fullname.requestFocus();
                }


                else if (mEmail.isEmpty()){
                    email.setError("Please enter your email");
                    email.requestFocus();
                }

                else if (mPassword.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();

                }
                else if (mRepeatPassword.isEmpty()){
                    repeatpassword.setError("Please repeate password");
                    repeatpassword.requestFocus();

                }
                else if (mGender.isEmpty()){
                    gender.setError("Please enter your Gender");
                    gender.requestFocus();

                }
                else if (mDateofbirth.isEmpty()){
                    dateofbirth.setError("Please enter you date of birth");
                    email.requestFocus();

                }
                else if (mFullName.isEmpty() && mEmail.isEmpty() && mPassword.isEmpty() && mRepeatPassword.isEmpty() && mGender.isEmpty() &&  mDateofbirth.isEmpty()){
                    Toast.makeText(SignUp.this , "fields Are Empty!" , Toast.LENGTH_SHORT).show();

                }
                else if (!(mPassword.equals(mRepeatPassword))){
                    Toast.makeText(SignUp.this , "Password doesn't match!" , Toast.LENGTH_SHORT).show();

                }
                else if ( ( (!mFullName.isEmpty() && !mEmail.isEmpty() && !mPassword.isEmpty() && !mRepeatPassword.isEmpty() && !mGender.isEmpty() &&  !mDateofbirth.isEmpty())) && mPassword.equals(mRepeatPassword)  ){

                    mFirebaseAuth.createUserWithEmailAndPassword(mEmail,mPassword).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(SignUp.this , "SignUp unsuccessful, Please try again." , Toast.LENGTH_SHORT).show();


                                    }
                                    else
                                    {

                                        //startActivity(new Intent(SignUp.this , loggED.class));
                                        Toast.makeText(SignUp.this , "Hi , Succeeded" , Toast.LENGTH_SHORT).show();


                                    }
                                }
                            }
                    );
                }
                else {
                    Toast.makeText(SignUp.this , "Error has ocurred" , Toast.LENGTH_SHORT).show();
                }



            }
        });







































































    }

}
