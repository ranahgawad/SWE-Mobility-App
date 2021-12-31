package Application.Core;//package com.company;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

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

    public abstract void registerUser();

    public static ArrayList<User> getUserList() {
        return userList;
    }

}

class PassengerRegistration extends Registration {
    private Passenger passenger;
    private String birthdayDate;




    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    PassengerRegistration(String username, String password, String email, String mobileNumber, String birthdayDate)  {
        super(username, password, email, mobileNumber);
        this.birthdayDate=birthdayDate;
        passenger = new Passenger(username, password, email, mobileNumber,birthdayDate);
        SQLImplementation connection = SQLImplementation.getInstance();
        connection.insert(passenger);
        userList.add(passenger);

    }

    @Override
    public void registerUser() {

    }

    public Passenger getPassenger() {
        return passenger;
    }
}

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
        userList.add(driver);
    }

    @Override
    public void registerUser() {
        notificationSender.notify("driver registration", this);
    }

    @Override
    public String toString() {
        return "Application.Core.DriverRegistration{" +
                "licenseNumber='" + licenseNumber + '\'' +
                ", id='" + nationalID + '\'' +
                '}';
    }

    public Driver getDriver() {
        return driver;
    }
}
