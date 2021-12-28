//package com.company;

import java.util.ArrayList;

public class Admin extends User
{
    private ArrayList<Registration> driverRegistration;

    Admin()
    {
        driverRegistration = new ArrayList<Registration>();
    }
    public void suspend(User user)
    {
        user.setSuspended(true);
        IPersistence connection = SQLImplementation.getInstance();
        connection.updateUserSuspened(user);
    }

    @Override
    public void update(Object type, Object data) {
      driverRegistration.add((Registration)data);
    }

    public void verifyDriver(Driver driver)
    {
        driver.setVerfied(true);
        IPersistence connection = SQLImplementation.getInstance();
        connection.updateDriverVerification(driver, 1);
    }

    public void print()
    {
        System.out.println(driverRegistration);
    }

    public void getAllPassengers(){
        IPersistence connection = SQLImplementation.getInstance();
        connection.getAllPasengers();
    }

    public void getAllDrivers(){
        IPersistence connection = SQLImplementation.getInstance();
        connection.getAllDrivers();
    }

    public void getPendingDriverVerifications(){
        IPersistence connection = SQLImplementation.getInstance();
        connection.getPendingDriverVerifications();
    }

    public void showEvents(Ride ride){
       ArrayList<Event> rideEvents = ride.getRideEvents();
        for(int i=0; i< rideEvents.size(); i++){
            System.out.println(rideEvents.get(i).toString());
        }
    }


}
