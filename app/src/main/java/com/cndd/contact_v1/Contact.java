package com.cndd.contact_v1;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class Contact implements Serializable {
    public static int ID = 0;
    private int id;
    private String name;
    private String avatar;
    private String phone;

    public Contact() {
    }

    public Contact(int ID, String name, @Nullable String avatar , String phone) {
        this.id = ID;
        this.name = name;
        this.avatar = avatar;
        this.phone = phone;
        Contact.ID++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
