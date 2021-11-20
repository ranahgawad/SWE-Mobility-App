package com.company;

public abstract class Registration {

    private String username;
    private String password;
    private String email;
    private String mobileNumber;

    public Registration() {}
    public Registration(String username, String password, String email, String mobileNumber)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }
    public abstract void registerUser();
}

class PassengerRegistration extends Registration
{
    PassengerRegistration(String username, String password, String email, String mobileNumber)
    {
        super(username, password, email, mobileNumber);

    }
    @Override
    public void registerUser()
    {

    }
}

class DriverRegistration extends Registration
{
    private String licenseNumber;
    private String id;
    public UserNotificationManager notificationSender;

    DriverRegistration(String username, String password, String email, String mobileNumber, String licenseNumber, String id)
    {
        super(username, password, email, mobileNumber);
        this.licenseNumber = licenseNumber;
        this.id = id;
        notificationSender = new UserNotificationManager("driver registration");
    }
    @Override
    public void registerUser() {
        notificationSender.notify("driver registration",this);
    }

    @Override
    public String toString() {
        return "DriverRegistration{" +
                "licenseNumber='" + licenseNumber + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
