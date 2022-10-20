package com.ex.schoolride;

public class Student {

    private String sName, sSchool, sAddress, sContactNo, sAge, sEmail, sPassword;

    public Student(){

    }

    public Student(String sName, String sSchool, String sAddress, String sContactNo, String sAge, String sEmail, String sPassword) {
        this.sName = sName;
        this.sSchool = sSchool;
        this.sAddress = sAddress;
        this.sContactNo = sContactNo;
        this.sAge = sAge;
        this.sEmail = sEmail;
        this.sPassword = sPassword;
    }

    //getters

    public String getsName() {
        return sName;
    }

    public String getsSchool() {
        return sSchool;
    }

    public String getsAddress() {
        return sAddress;
    }

    public String getsContactNo() {
        return sContactNo;
    }

    public String getsAge() {
        return sAge;
    }

    public String getsEmail() {
        return sEmail;
    }

    public String getsPassword() {
        return sPassword;
    }

    //setters

    public void setsName(String sName) {
        this.sName = sName;
    }

    public void setsSchool(String sSchool) {
        this.sSchool = sSchool;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public void setsContactNo(String sContactNo) {
        this.sContactNo = sContactNo;
    }

    public void setsAge(String sAge) {
        this.sAge = sAge;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }
}

