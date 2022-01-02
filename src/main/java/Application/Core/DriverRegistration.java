package Application.Core;//package com.company;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;



@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DriverRegistration  {
    private String username;
    private String password;
    private String email;
    private String mobileNumber;
    private String licenseNumber;
    private String nationalID;
    private Driver driver;
    public UserNotificationManager notificationSender;

    DriverRegistration(String username, String password, String email, String mobileNumber, String licenseNumber, String nationalID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.licenseNumber = licenseNumber;
        this.nationalID = nationalID;
        notificationSender = new UserNotificationManager("driver registration");
        driver = new Driver(username, password, email, mobileNumber, licenseNumber, nationalID);
        SQLImplementation connection = SQLImplementation.getInstance();
        connection.insert(driver);
    }
    static boolean Register(Driver driver){
        SQLImplementation connection = SQLImplementation.getInstance();
        if(connection.insert(driver)) {
            connection.setUserID(driver);
            return true;
        }
        else {
            return false;
        }

    }

    public Driver getDriver() {
        return driver;
    }
}
