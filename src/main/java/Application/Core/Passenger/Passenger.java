package Application.Core.Passenger;//package com.company;

import Application.Core.Ride.Offer;
import Application.Core.User.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Passenger extends User
{
    private int PassengerID;
    private String birthdayDate;
    @JsonIgnore
    private ArrayList<Offer> rideOffers;
    private static int countRides=0;
    @JsonIgnore
    private PassengerModel passengerModel;

    public Passenger(String username, String password, String email, String mobileNumber, String birthdayDate)
    {
        super(username, password, email, mobileNumber);
        this.birthdayDate=birthdayDate;
        rideOffers = new ArrayList<Offer>();
        passengerModel = new PassengerModel(this);
    }

    @Override
    public void setSuspended(boolean suspended) {
        super.setSuspended(suspended);
    }

    public ArrayList<Offer> getRideOffers() {
        return rideOffers;
    }


    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    @Override
    public boolean getisSuspended() {
        return super.getisSuspended();
    }

    public PassengerModel getPassengerModel() {
        return passengerModel;
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
        return this.PassengerID;
    }

    public int getCountRides() {
        return countRides;
    }

    public static void setCountRides(int countRides) {
        Passenger.countRides = countRides;
    }

    public void setPassengerID(int passengerID) {
        this.PassengerID = passengerID;
    }

    @Override
    public void update(Object type, Object data) {
        Offer offer = (Offer)data;
        if((offer.getNumPassengers() == offer.getRequest().getNumPassengers()))
        {
            rideOffers.add((Offer)data);
        }

    }



}