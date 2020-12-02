package com.myvisit_.Models;

import java.io.Serializable;

public class Employee implements Serializable {

    private String id;
    private String fname;
    private String phone_num;
    private String email;
    private String position;
    private String DodId;

    public Employee() {
    }

    public Employee(String Name)
    {
        this.fname = Name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDodId() {
        return DodId;
    }

    public void setDodId(String dodId) {
        DodId = dodId;
    }
}
