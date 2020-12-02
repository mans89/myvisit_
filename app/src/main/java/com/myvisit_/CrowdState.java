package com.myvisit_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CrowdState extends AppCompatActivity {
    ProgressBar pb;
    int  index;
    TextView tv;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crowd_state);

        pb = findViewById(R.id.CSprogressBar);
        tv = findViewById(R.id.variableCrowdState);

       // index = Integer.parseInt(et.getText().toString());
        index = 19;

        if (index > 0 && index <= 20){
            pb.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
            pb.setProgress(index);
            tv.setText("Not Crowded");
            tv.setTextColor(ColorStateList.valueOf(Color.GREEN));


        }

        else if  (index <= 69 && index >= 20){
            pb.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
            pb.setProgress(index);
            tv.setText("Slightly Crowded");
            tv.setTextColor(ColorStateList.valueOf(Color.YELLOW));
        }


        else if (index >= 70){
            pb.setProgressTintList(ColorStateList.valueOf(Color.RED));
            pb.setProgress(index);
            tv.setText("Crowded");
            tv.setTextColor(ColorStateList.valueOf(Color.RED));
            }

        else
        {

        }

    }

}
