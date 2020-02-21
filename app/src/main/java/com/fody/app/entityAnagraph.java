package com.fody.app;

public class entityAnagraph {
    private String uid;
    private String firstName;
    private String lastName;
    private String phone;


    public entityAnagraph(){

    }

    public entityAnagraph(String firstName, String lastName, String phone, String uid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.uid =uid;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }



    public String getPhone() {
        return phone;
    }

    public String getUid() {
        return uid;
    }
}
