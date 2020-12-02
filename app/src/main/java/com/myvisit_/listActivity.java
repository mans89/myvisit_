package com.myvisit_;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class listActivity extends Fragment {
    ListView listView;
    String[] nNames = {"Weam", "Ajwad", "Rania", "Ahlam"};
    int[] iImages = {R.drawable.image_circle, R.drawable.image_circle, R.drawable.image_circle, R.drawable.image_circle};

    public View onCreateView(LayoutInflater inflater,
                             @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_view_content, container, false);

        listView = v.findViewById(R.id.list_view);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),GroupChatActivity.class);
                //intent.putExtra("name", nNames[i]);
                //intent.putExtra("image", iImages[i]);

                startActivity(intent);

            }
        });

        return v;

    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return iImages.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = getLayoutInflater().inflate(R.layout.row_data, null);

            TextView name = v.findViewById(R.id.text_view_name);
            ImageView image = v.findViewById(R.id.images);

            name.setText(nNames[i]);
            image.setImageResource(iImages[i]);

            return v;
        }
    }
}


