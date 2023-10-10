package com.example.Jayesh.firebaselogin.Entitys;

import androidx.dynamicanimation.animation.SpringAnimation;

public class User {
    private String name;
    private String city;
    private String emailId;
    private String phone;

    public User() {
    }

    public User(String name, String city, String emailId, String phone) {
        this.name = name;
        this.city = city;
        this.emailId = emailId;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
