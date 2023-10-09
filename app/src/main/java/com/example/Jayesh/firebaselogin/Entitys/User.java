package com.example.Jayesh.firebaselogin.Entitys;

public class User {
    private String Name;
    private String EmailId;
    private String Mobile;
    private String DateOfBirth;

    public User() {
    }

    public User(String name, String emailId, String mobile, String dateOfBirth) {
        Name = name;
        EmailId = emailId;
        Mobile = mobile;
        DateOfBirth = dateOfBirth;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "User{" +
                "Name='" + Name + '\'' +
                ", EmailId='" + EmailId + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", DateOfBirth='" + DateOfBirth + '\'' +
                '}';
    }
}
