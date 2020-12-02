package com.myvisit_.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myvisit_.Models.Notification;
import com.myvisit_.R;

import java.util.List;

public class Notificationadaptor extends RecyclerView.Adapter <Notificationadaptor.ViewHolder>
{

    Context context;
    private List<Notification> notifictionList;

    public Notificationadaptor(Context context, List<Notification> notifictionList )
    {
        this.context = context;
        this.notifictionList = notifictionList;
    }

    @Override
    public Notificationadaptor.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.noterow, viewGroup, false);
        return new Notificationadaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Notificationadaptor.ViewHolder viewHolder, final int i)
    {
        viewHolder.textholder.setText(notifictionList.get(i).getDate().toString());
        viewHolder.message.setText(notifictionList.get(i).getMsg());
        viewHolder.notrtimr.setText(notifictionList.get(i).getTimenote().toString());
    }

    @Override
    public int getItemCount() {
        return notifictionList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder
    {
        TextView textholder;
        TextView message;
        TextView notrtimr;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textholder= itemView.findViewById(R.id.textheld);
            message= itemView.findViewById(R.id.textmsg);
            notrtimr= itemView.findViewById(R.id.textmsgtime);
        }

        }
    }