package Core;//package com.company;

import java.util.ArrayList;

public abstract class User <T>
{
//    public static int ID;
    private String username;
    private String password;
    private String email;
    private String mobileNumber;
    private boolean isSuspended=false;
    private boolean isLoggedIn = false;
    User()
    {

    }
    public User(String username, String password, String email, String mobileNumber)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public void setSuspended(boolean suspended)
    {
        isSuspended = suspended;
    }

    public abstract void update(T type, T data);

    @Override
    public String toString() {
        return "Core.User{" +
                "Name: '" + username + '\'' +
                 '}';
    }


    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getisSuspended(){
        return isSuspended;
    }

    public void setLoggedIn(boolean logged){
        this.isLoggedIn = logged;
    }

    public boolean getLoggedIn() {
        return isLoggedIn;
    }
}