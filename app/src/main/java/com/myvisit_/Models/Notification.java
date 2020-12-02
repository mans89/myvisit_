package com.myvisit_.Models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Notification



{

    private  String msg;
    private String timenote;
    private String date;

    public Notification() {

    }


    public Notification(String msg)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.msg = msg;
        this.timenote = DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date());
        this.date = dateFormat.format(new Date());
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTimenote() {
        return timenote;
    }

    public void setTimenote(String timenote) {
        this.timenote = timenote;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
