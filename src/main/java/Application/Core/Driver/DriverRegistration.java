package Application.Core.Driver;//package com.company;

import Application.Core.Storage.IPersistence;
import Application.Core.Storage.SQLImplementation;
import Application.Core.User.UserNotificationManager;
import com.fasterxml.jackson.annotation.JsonAutoDetect;


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
        IPersistence connection = SQLImplementation.getInstance();
        connection.insert(driver);
    }
    public static boolean Register(Driver driver){
        IPersistence connection = SQLImplementation.getInstance();
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
