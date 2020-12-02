package com.myvisit_;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.myvisit_.Adapters.EmployeeAdapter;
import com.myvisit_.Models.Employee;

import java.util.ArrayList;
import java.util.List;

public class ManageEmployee extends AppCompatActivity {

    private static final String TAG ="----------------------------" ;
    RecyclerView recyclerView;
    EmployeeAdapter employeeAdapter;
    List <Employee> employeeList = new ArrayList<>();

    FirebaseFirestore db = FirebaseFirestore.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_employee);

        employeeAdapter = new EmployeeAdapter(ManageEmployee.this , employeeList , new CustomItemClickListener() {
            @Override
            public void OnItemClick(Employee e) {
                Intent intent = new Intent(getApplicationContext() , EmployeeProfile.class );
                intent.putExtra("name" , e.getFname());
                intent.putExtra("id" , e.getId());
                intent.putExtra("email" , e.getEmail());
                intent.putExtra("mobile" , e.getPhone_num());
                intent.putExtra("postion" , e.getPosition());
                intent.putExtra("DocId" , e.getDodId());
                startActivity(intent);
            }
        });



        recyclerView = findViewById(R.id.emp_rec);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),4);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(employeeAdapter);

        getEmployee();

    }

    private void getEmployee()
    {

 //       Employee emp;
//
//        for (int i=0 ; i<10 ; i++)
//        {
//
//            Log.d(TAG, ""+i);
//            emp= new Employee();
//            emp.setFname(""+i);
//            emp.setEmail(""+i);
//            emp.setId(""+i);
//            emp.setPosition(""+i);
//            emp.setPhone_num(""+i);
//            employeeList.add(emp);
//            employeeAdapter.notifyDataSetChanged();
//        }



//
//
//        db.collection("Employee").document("dd").delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                db.collection("fd");
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                //
//            }
//        });


        db.collection("Employee")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {
                            Employee emp;
                            for (QueryDocumentSnapshot document : task.getResult())
                            {


                                Log.d(TAG, "======================>" + document.getId());
                                emp= new Employee();
                                emp.setFname(document.get("fname").toString());
                                emp.setEmail(document.get("email").toString());
                                emp.setId(document.get("id").toString());
                                emp.setPhone_num(document.get("mobile").toString());
                                emp.setDodId(document.getId());
                                employeeList.add(emp);
                                employeeAdapter.notifyDataSetChanged();



                           //     String s  = document.get("").toString();
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "000000000000000000000000000Error getting documents.", task.getException());
                        }
                    }
                });




    }

    public void Add_New_Employee(View view) {

        Intent intent = new Intent(this , addEmployee.class);
        startActivity(intent);


    }
}
