package com.myvisit_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.myvisit_.Adapters.Notificationadaptor;
import com.myvisit_.Models.Employee;
import com.myvisit_.Models.Notification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class notifications extends Fragment
{

    RecyclerView recyclerView ;
    Notificationadaptor notificationadaptor;
    List<Notification> notificationList = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();



    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup parent , Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_notifications , parent , false);
    }


    @Override
    public void onViewCreated (final View view , Bundle savedInstanceState)
    {


        notificationadaptor = new Notificationadaptor(getContext(),notificationList);
        recyclerView = view.findViewById(R.id.notelist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(notificationadaptor);
        GetDate();
    }



    public void GetDate()
    {

        db.collection("Updates")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                        {
                            Notification notification;
                            for (QueryDocumentSnapshot document : task.getResult())
                            {


                                notification= new Notification();
                                notification.setMsg(document.get("name").toString());
                                notification.setDate(document.get("date").toString());
                                notification.setTimenote(document.get("time").toString());
                                notificationList.add(notification);
                                notificationadaptor.notifyDataSetChanged();


                                //     String s  = document.get("").toString();
                                Log.d("h", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("H", "000000000000000000000000000Error getting documents.", task.getException());
                        }
                    }
                });










//        Notification notification;
//
//        for (int i =0; i< 10; i++){
//
//            notification= new Notification("Mans");
//            notificationList.add(notification);
//            notificationadaptor.notifyDataSetChanged();
//
//        }


    }


}
