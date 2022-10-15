package com.ex.schoolride;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

public class Driver {

    private String Dname, DNIC, DVehicle, DContactNo, DUsername, Dpassword;

    public Driver() {

    }

    public Driver(String dname, String DNIC, String DVehicle, String DContactNo, String DUsername, String dpassword) {
        Dname = dname;
        this.DNIC = DNIC;
        this.DVehicle = DVehicle;
        this.DContactNo = DContactNo;
        this.DUsername = DUsername;
        Dpassword = dpassword;


    }

    //getters
    public String getDname() {
        return Dname;
    }

    public String getDNIC() {
        return DNIC;
    }

    public String getDVehicle() {
        return DVehicle;
    }

    public String getDContactNo() {
        return DContactNo;
    }

    public String getDUsername() {
        return DUsername;
    }

    public String getDpassword() {
        return Dpassword;
    }

    //setters


    public void setDname(String dname) {
        Dname = dname;
    }

    public void setDNIC(String DNIC) {
        this.DNIC = DNIC;
    }

    public void setDVehicle(String DVehicle) {
        this.DVehicle = DVehicle;
    }

    public void setDContactNo(String DContactNo) {
        this.DContactNo = DContactNo;
    }

    public void setDUsername(String DUsername) {
        this.DUsername = DUsername;
    }

    public void setDpassword(String dpassword) {
        Dpassword = dpassword;
    }



}
