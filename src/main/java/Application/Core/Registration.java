package Application.Core;//package com.company;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class Registration {

    public static ArrayList<User> userList = new ArrayList<User>();
    private String username;
    private String password;
    private String email;
    private String mobileNumber;


    public Registration() {
    }

    public Registration(String username, String password, String email, String mobileNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobileNumber = mobileNumber;

    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

}



@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class DriverRegistration extends Registration {
    private String licenseNumber;
    private String nationalID;
    private Driver driver;
    public UserNotificationManager notificationSender;

    DriverRegistration(String username, String password, String email, String mobileNumber, String licenseNumber, String nationalID) {
        super(username, password, email, mobileNumber);
        this.licenseNumber = licenseNumber;
        this.nationalID = this.nationalID;
        notificationSender = new UserNotificationManager("driver registration");
        driver = new Driver(username, password, email, mobileNumber, licenseNumber, nationalID);
        SQLImplementation connection = SQLImplementation.getInstance();
        connection.insert(driver);
//        userList.add(driver);
    }
    static boolean Register(Driver driver){
        SQLImplementation connection = SQLImplementation.getInstance();
        if(connection.insertUser((User) driver) && connection.insert(driver))
            return true;
        else
            return false;

    }

    public Driver getDriver() {
        return driver;
    }
}
