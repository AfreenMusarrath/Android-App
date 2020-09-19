package com.example.helen;

public class profile {
    private String name;
    private String usn;
    private String DOB;
    private String class1;
    private String phno;

    public profile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getClass1() {
        return class1;
    }

    public void setClass1(String class1) {
        this.class1 = class1;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public profile(String name, String usn, String DOB, String class1, String phno, String email) {
        this.name = name;
        this.usn = usn;
        this.DOB = DOB;
        this.class1 = class1;
        this.phno = phno;
        this.email = email;
    }
}
