package com.myvisit_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.myvisit_.Models.Notification;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EmployeeProfile extends AppCompatActivity {

    ImageView remove, edit;
    EditText id, mobile , email , postion , emp_name;
    TextView done;
    String DocId;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseFirestore ab = FirebaseFirestore.getInstance();
    String mymesg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);

        remove =findViewById(R.id.empremove);
        id =findViewById(R.id.emp_pro_id);
        done =findViewById(R.id.doneediting);
        emp_name=findViewById(R.id.emp_pro_name);
        mobile=findViewById(R.id.emp_pro_mobile);
        email=findViewById(R.id.emp_pro_email);
        postion=findViewById(R.id.emp_pro_postion);
        edit=findViewById(R.id.empedit);

        Intent intent = getIntent();

        DocId = intent.getStringExtra("DocId");

        id.setText(intent.getStringExtra("id"));
        mobile.setText(intent.getStringExtra("mobile"));
        email.setText(intent.getStringExtra("email"));
        postion.setText(intent.getStringExtra("postion"));
        emp_name.setText(intent.getStringExtra("name"));

          // To Delete An Employee
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.collection("Employee").document(DocId).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(getApplicationContext() , "Deleted" , Toast.LENGTH_LONG).show();

                        Notification notification= new Notification(emp_name.getText().toString().trim());

                        // Add a new document with a generated id.
                        Map<String, Object> data = new HashMap<>();
                        data.put("name", "Employee Profile Has Been Removed "+notification.getMsg());
                        data.put("date", notification.getDate());
                        data.put("time", notification.getTimenote());

                        db.collection("Updates").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                //          Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                            }
                        })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        //          Log.w(TAG, "Error adding document", e);
                                    }
                                });

                        Intent intent = new Intent(getApplicationContext(), ManageEmployee.class);
                        startActivity(intent);

                    }
                });

            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DocumentReference washingtonRef = db.collection("Employee").document(DocId);

                final Map<String, Object> data = new HashMap<>();
                data.put("fname", emp_name.getText().toString().trim());
                data.put("mobile", mobile.getText().toString().trim());
                data.put("position", postion.getText().toString().trim());
                data.put("id", id.getText().toString().trim());
                data.put("email", email.getText().toString().trim());

                washingtonRef.update(data).addOnSuccessListener(new OnSuccessListener<Void>()
                        {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(getApplicationContext() , "Employee Data successfully updated!" , Toast.LENGTH_LONG).show();
                                mymesg="employ profile has been modfied";
                                id.setEnabled(false);
                                mobile.setEnabled(false);
                                email.setEnabled(false);
                                postion.setEnabled(false);
                                email.setEnabled(false);
                                emp_name.setEnabled(false);

                                Notification notification= new Notification(emp_name.getText().toString().trim());

                                // Add a new document with a generated id.
                                Map<String, Object> data = new HashMap<>();
                                data.put("name", "Employee Profile Has Been Updated "+notification.getMsg());
                                data.put("date", notification.getDate());
                                data.put("time", notification.getTimenote());

         db.collection("Updates").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                      //          Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                      //          Log.w(TAG, "Error adding document", e);
                                            }
                                        });


                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(getApplicationContext() , "Error updating document "+e.toString() , Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id.setEnabled(true);
                mobile.setEnabled(true);
                email.setEnabled(true);
                postion.setEnabled(true);
                emp_name.setEnabled(true);
            }
        });

    }
}
