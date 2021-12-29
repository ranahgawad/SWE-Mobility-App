//package com.company;

import java.util.ArrayList;

public class Passenger extends User
{
    private static int count=0;
    private int PassengerID;
    private ArrayList<Offer> rideOffers;
    private PassengerModel passengerModel;


    Passenger(String username, String password, String email, String mobileNumber)
    {
        super(username, password, email, mobileNumber);
        rideOffers = new ArrayList<Offer>();
        count++ ;
        this.PassengerID = count;
        passengerModel = new PassengerModel(this);
        SQLImplementation connection = SQLImplementation.getInstance();
        connection.insert(this);
    }

    @Override
    public void setSuspended(boolean suspended) {
        super.setSuspended(suspended);
    }

    public ArrayList<Offer> getRideOffers() {
        return rideOffers;
    }

    @Override
    public boolean getisSuspended() {
        return super.getisSuspended();
    }

    public PassengerModel getPassengerModel() {
        return passengerModel;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean getLoggedIn() {
        return super.getLoggedIn();
    }

    @Override
    public String getMobileNumber() {
        return super.getMobileNumber();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    public int getPassengerID() {
        return PassengerID;
    }


    @Override
    public void update(Object type, Object data) {
        rideOffers.add((Offer)data);
    }



}

