package com.example.akil.onusondhan;

/**
 * Created by Akil on 8/21/2017.
 */

public class User {
    public String email;
    public String password;
    public String phonenum;
    public String gender,id;
    public int age;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public User() {

    }

    public User(String _id,String email, String password, String phonenum, String gender,int age) {
        this.id=_id;
        this.email = email;
         this.password = password;
        this.phonenum = phonenum;
        this.gender = gender;
        this.age = age;
    }


}
