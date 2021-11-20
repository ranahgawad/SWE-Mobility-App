package com.company;

public abstract class User <T>
{
    private String username;
    private String password;
    private String email;
    private String mobileNumber;
    private boolean isSuspended;

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
        return "User{" +
                "Name: '" + username + '\'' +
                 '}';
    }
}