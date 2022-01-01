package Application.Core;//package com.company;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Passenger extends User
{
    private static int count;
    private int PassengerID;
    private String birthdayDate;
    private ArrayList<Offer> rideOffers;
    private PassengerModel passengerModel;
    private static int countRides=0;


    Passenger(String username, String password, String email, String mobileNumber,  String birthdayDate)
    {

        super(username, password, email, mobileNumber);
        this.birthdayDate=birthdayDate;
        rideOffers = new ArrayList<Offer>();
//        count++ ;
//        this.PassengerID = count;
        passengerModel = new PassengerModel(this);
//        SQLImplementation connection = SQLImplementation.getInstance();
//        connection.insert(this);
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

    public int getCountRides() {
        return countRides;
    }

    public void setCountRides() {
        SQLImplementation connection = SQLImplementation.getInstance();
        for(int i=0; i< rideOffers.size();i++){
            if(rideOffers.get(i).getRequest().getRide().getisFinished() == true){
                countRides++;
            }
            connection.updateCountRides((rideOffers.get(i).getRequest().getRide().getRequester()),countRides);
        }

    }

    public void setPassengerID(int passengerID) {
        PassengerID = passengerID;
    }

    @Override
    public void update(Object type, Object data) {
        rideOffers.add((Offer)data);
    }



}