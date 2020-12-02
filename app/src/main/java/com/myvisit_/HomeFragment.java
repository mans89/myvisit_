package com.myvisit_;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.icu.util.LocaleData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.myvisit_.R;

import  java.util.*;
import java.lang.*;

import android.os.CountDownTimer;
import android.widget.Toast;


import java.util.Locale;

public class HomeFragment extends Fragment {
    private View homeView;
    private Date obj;
    private TextView Date;
    private Button VTbutton;
    private Button CSbutton;
    private Button WTbutton;
    private FrameLayout fl;
    private DocumentReference ref;
   // private int index;
    private TextView trytv;
    private static final long START_TIME_IN_MILLIS = 600000;
    private double index ;
    //Timer
    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private FirebaseFirestore mstore ;

    private long mEndTime;


    public View onCreateView(LayoutInflater inflater,
                             @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        homeView = inflater.inflate(R.layout.fragment_home, container, false);

        VTbutton = homeView.findViewById(R.id.VTbutton);
        CSbutton = homeView.findViewById(R.id.CSbutton);
        WTbutton = homeView.findViewById(R.id.WTbutton);
        fl = homeView.findViewById(R.id.flNew);
        trytv = homeView.findViewById(R.id.variableCrowdState);

        mstore = FirebaseFirestore.getInstance();

        mstore.collection("CrowdState").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
//                    Toast.makeText(getContext(), "document is exist", Toast.LENGTH_LONG).show();

                    for (QueryDocumentSnapshot document : task.getResult()) {

                 //       index = Double.parseDouble(document.getString("indexOfCrowd"));
                    }
                } else {
                    Toast.makeText(getContext(), "document doesn't exist", Toast.LENGTH_LONG).show();

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Failure to access", Toast.LENGTH_LONG).show();

            }
        });
        /*
        ref = mstore.collection("CrowdState").document();
//
        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    Toast.makeText(getContext(), "document is exist", Toast.LENGTH_LONG).show();

                    index = Double.parseDouble(documentSnapshot.getString("indexOfCrowd"));


                }else{
                    Toast.makeText(getContext(), "document doesn't exist", Toast.LENGTH_LONG).show();


                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Failure to access", Toast.LENGTH_LONG).show();

            }
        });
*/


        VTbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              showVisitTime();
            }
        });

        CSbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showcrowdState();

                //  fl.removeAllViews();

                //  LayoutInflater.from(getContext()).inflate(R.layout.activity_crowd_state, fl, true);

            }
        });

        WTbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             showWaitingTime();

            }
        });

        obj = new java.util.Date();
        Date = homeView.findViewById(R.id.editTextDate);

        Date.setText(obj.toString());


        return homeView;
    }


    private void showcrowdState() {

        fl.removeAllViews();
        // fl = R.layout.activity_crowd_state;
        LayoutInflater.from(getContext()).inflate(R.layout.activity_crowd_state, fl, true);
        TextView CrowsState = fl.findViewById(R.id.CrowsState);
        CrowsState.setText("");
        ProgressBar pb;
       // index = 19;
        TextView tv;
        EditText et;


        pb = fl.findViewById(R.id.CSprogressBar);
        tv = fl.findViewById(R.id.variableCrowdState);


        // index = Integer.parseInt(et.getText().toString());


        if (index > 0 && index <= 20) {
            pb.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
            pb.setProgress((int)index);
            tv.setText("Not Crowded " + index);
            tv.setTextColor(ColorStateList.valueOf(Color.GREEN));


        } else if (index <= 69 && index >= 20) {
            pb.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
            pb.setProgress((int)index);
            tv.setText("Slightly Crowded " + index);
            tv.setTextColor(ColorStateList.valueOf(Color.YELLOW));
        } else if (index >= 70) {
            pb.setProgressTintList(ColorStateList.valueOf(Color.RED));
            pb.setProgress((int)index);
            tv.setText("Crowded " + index);
            tv.setTextColor(ColorStateList.valueOf(Color.RED));
        } else {

        }


    }

    private void showVisitTime() {
        fl.removeAllViews();
        LayoutInflater.from(getContext()).inflate(R.layout.activity_visit_time, fl, true);



    }

    private void showWaitingTime() {

        fl.removeAllViews();
        LayoutInflater.from(getContext()).inflate(R.layout.activity_waiting_time, fl, true);
        mTextViewCountDown = fl.findViewById(R.id.text_view_countdown);

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis , 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;


            }
        }.start();
        mTimerRunning = true;

        updateCountDownText();
    }

    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftInMillis / 1000 )/ 60;
        int seconds = (int) (mTimeLeftInMillis / 1000 ) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

}
