package com.ex.schoolride;

public class Driver {

    private String Dname, DNIC, DVehicle, DContactNo, DAge;

    public Driver() {

    }

    public Driver(String dname, String DNIC, String DVehicle, String DContactNo, String DAge) {
        Dname = dname;
        this.DNIC = DNIC;
        this.DVehicle = DVehicle;
        this.DContactNo = DContactNo;
        this.DAge = DAge;


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

    public String getDAge() {
        return DAge;
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

    public void setDAge(String DUsername) {
        this.DAge = DAge;
    }




}
