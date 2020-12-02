package com.myvisit_;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class loggED extends AppCompatActivity {
    Button btnLoggedOut;
    FirebaseAuth mFirebaseAuth;
    TextView uID;
    private  FirebaseAuth.AuthStateListener mAuthStateListener;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseFirestore mstore ;
    private DatabaseReference myRef;
    private DocumentReference ref ;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logg_ed);
        uID = findViewById(R.id.logged_in);

        ;

        btnLoggedOut = findViewById(R.id.logout);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        mstore = FirebaseFirestore.getInstance();
        ref = mstore.collection("Employee").document(user.getUid());

        userID = user.getEmail();

        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    Toast.makeText(getApplicationContext(), "document is exist", Toast.LENGTH_LONG).show();

                    String card = documentSnapshot.getString("id");
                    String fullName = documentSnapshot.getString("fname");
                    String mobile = documentSnapshot.getString("mobile");
                    String email = documentSnapshot.getString("email");
                    String position = documentSnapshot.getString("position");

                    uID.setText(card +" "+ fullName+ " /n" + mobile+" " + email + " " +position );

                }else{
                    Toast.makeText(getApplicationContext(), "document doesn't exist", Toast.LENGTH_LONG).show();


                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failure to access", Toast.LENGTH_LONG).show();
            }
        });
        //if (user != null) {
        // Name, email address, and profile photo Url
        //    String name = user.getDisplayName();

        //uID.setText(userID);
        //uID.setText(user.getDisplayName());


        btnLoggedOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intToHome = new Intent(loggED.this, Home.class);
                startActivity(intToHome);
            }
        });


    }
}
