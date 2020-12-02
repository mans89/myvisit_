package com.myvisit_;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class messagesFragment extends Fragment implements View.OnClickListener{
    private View messageFragmentView;
    private Button buttonGroup ;
    private Button buttonAll;
    private Button buttonAdd;

    public View onCreateView( LayoutInflater inflater,
                              @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        messageFragmentView = inflater.inflate(R.layout.fragment_messages, container, false);

        buttonGroup = (Button) messageFragmentView.findViewById(R.id.buttonGroup);
        buttonAll = (Button) messageFragmentView.findViewById(R.id.buttonAll);
        buttonAdd = (Button) messageFragmentView.findViewById(R.id.btn_employee);
        /*buttonGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new listActivity());
                fr.commit();
            }
        });*/
        buttonGroup.setOnClickListener(this);
        buttonAll.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);

        return messageFragmentView;

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()){
            case R.id.buttonGroup:
                Toast.makeText(getContext(),"Button Group Message",Toast.LENGTH_SHORT).show();
                FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new listActivity());
                fr.commit();
                break;

            case R.id.buttonAll:
                Toast.makeText(getContext(),"Button All Message",Toast.LENGTH_SHORT).show();
                FragmentTransaction f = getParentFragmentManager().beginTransaction();
                f.replace(R.id.fragment_container, new listActivity());
                f.commit();
                break;

            case R.id.btn_employee:
                //Toast.makeText(getContext(),"Button All Message",Toast.LENGTH_SHORT).show();
                //FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                //ft.replace(R.id.fragment_container, new addEmployee());
                //ft.commit();
                Intent intent = new Intent(getContext(),ManageEmployee.class);
                startActivity(intent);
                break;

        }

    }
}
