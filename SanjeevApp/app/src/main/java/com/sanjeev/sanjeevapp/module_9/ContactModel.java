package com.sanjeev.sanjeevapp.module_9;

import java.io.Serializable;

public class ContactModel implements Serializable {

    private String name;
    private String telphone;
    private String email;
    private String address;
    private String blob;

    private int id;

    public String getBlob() {
        return blob;
    }

    public void setBlob(String blob) {
        this.blob = blob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContactModel(String blob, String name, String telphone, String email, String address, int id) {
        this.name = name;
        this.telphone = telphone;
        this.email = email;
        this.address = address;
        this.id = id;
        this.blob = blob;
    }
}
