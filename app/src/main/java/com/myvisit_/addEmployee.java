package com.myvisit_;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class addEmployee extends AppCompatActivity {
    //private static final String TAG = "addEmployee";

    private EditText textCard, textFullName, textMobile, textEmail, textPosition;
    private Button btnAdd;

    //private static final String card , email , fullName , mobile , position ;

    //private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    //private FirebaseAuth.AuthStateListener mAuthListener;
    //private DatabaseReference myRef;

    private FirebaseFirestore fstore;
    private DatabaseReference myRef;
    private FirebaseDatabase mFirebaseDatabase;
    private CollectionReference colRef ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_employee);

        //myRef = FirebaseDatabase.getInstance().getReference("Employee");

        fstore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        textCard =  (EditText)findViewById(R.id.text_card);
        textFullName = (EditText) findViewById(R.id.text_fullname);
        textMobile = (EditText) findViewById(R.id.text_mobile);
        textEmail = (EditText) findViewById(R.id.text_email);
        textPosition = (EditText) findViewById(R.id.text_position);

        //mFirebaseDatabase = FirebaseDatabase.getInstance();
        //myRef = mFirebaseDatabase.getReference();
       // myRef = FirebaseDatabase.getInstance().getReference();

        btnAdd = (Button) findViewById(R.id.button);

        //btnAdd.setOnClickListener(this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 addUser2();
            }
        });
    }

    public void addUser2(){
        final String card = textCard.getText().toString();
        final String fullName = textFullName.getText().toString();
        final String mobile = textMobile.getText().toString();
        final String email = textEmail.getText().toString();
        final String position = textPosition.getText().toString();

        if (card.isEmpty()){
            textCard.setError("Please enter your Id");
            textCard.requestFocus();
        }

        if (fullName.isEmpty()){
            textFullName.setError("Please enter your FullName");
            textFullName.requestFocus();
        }

        if (mobile.isEmpty()){
            textMobile.setError("Please enter your Mobile ");
            textMobile.requestFocus();

        }
        if (email.isEmpty()){
            textEmail.setError("Please repeate Email");
            textEmail.requestFocus();

        }
        if (position.isEmpty()){
            textPosition.setError("Please enter your Position");
            textPosition.requestFocus();

        }

        if(!TextUtils.isEmpty(card) && !TextUtils.isEmpty(fullName) && !TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(position)    ) {

            mAuth.createUserWithEmailAndPassword(email,card).addOnCompleteListener(addEmployee.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(addEmployee.this , "SignUp unsuccessful, Please try again." , Toast.LENGTH_SHORT).show();

                    } else {
                        //startActivity(new Intent(addEmployee.this , loggED.class));
                        Toast.makeText(addEmployee.this , "Hi , Succeeded" , Toast.LENGTH_SHORT).show();

                        Map<String, Object> userMap = new HashMap<>();

                        userMap.put("id", card);
                        userMap.put("fname", fullName);
                        userMap.put("mobile", mobile);
                        userMap.put("email", email);
                        userMap.put("position", position);

                        fstore.collection("Employee").document(mAuth.getCurrentUser().getUid()).set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(), "Add Employee successful.", Toast.LENGTH_LONG).show();

                                Intent i = new Intent(getApplicationContext(), loggED.class);
                                startActivity(i);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                }
            });

            //Intent i = new Intent(getApplicationContext(), messagesFragment.class);
            //startActivity(i);

            //FragmentTransaction f = getSupportFragmentManager().beginTransaction();
            //f.replace(R.id.fragment_container, new messagesFragment());
            //f.commit();

        }else {
            Toast.makeText(getApplicationContext(), "Required to Fill", Toast.LENGTH_LONG).show();

        }
    }

    public void addUser(){
        String card = textCard.getText().toString();
        String fullName = textFullName.getText().toString();
        String mobile = textMobile.getText().toString();
        String email = textEmail.getText().toString();
        String position = textPosition.getText().toString();

        if(!TextUtils.isEmpty(card) && !TextUtils.isEmpty(fullName) && !TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(position)    ){

            String id = myRef.push().getKey();
            UserInformation userInformation = new UserInformation(card, fullName, mobile, email, position);
            myRef.child(id).setValue(userInformation);
            textCard.setText("");
            textFullName.setText("");
            textMobile.setText("");
            textEmail.setText("");
            textPosition.setText("");

            Toast.makeText(getApplicationContext(), "Add Employee Successful.", Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(),Fragment1.class);
            startActivity(i);

        }else {
            Toast.makeText(getApplicationContext(), "Error to Add Employee .", Toast.LENGTH_LONG).show();

        }
    }

    public void addUser3(){
        //String card = Integer.parseInt(textCard.getText().toString());
        String card = textCard.getText().toString();
        String fullName = textFullName.getText().toString();
        //String mobile = Integer.parseInt(textMobile.getText().toString());
        String mobile = textMobile.getText().toString();
        String email = textEmail.getText().toString();
        String position = textPosition.getText().toString();

        if(!TextUtils.isEmpty(card) && !TextUtils.isEmpty(fullName) && !TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(position)    ){

            String id = myRef.push().getKey();
            UserInformation userInformation = new UserInformation(card, fullName, mobile, email, position);
            myRef.child(id).setValue(userInformation);
            textCard.setText("");
            textFullName.setText("");
            textMobile.setText("");
            textEmail.setText("");
            textPosition.setText("");

            Toast.makeText(getApplicationContext(), "Add", Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(),Fragment1.class);
            startActivity(i);

        }else {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

        }
    }

    /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }*/

}


