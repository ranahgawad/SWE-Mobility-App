//package com.company;

import java.util.ArrayList;

public class Passenger extends User
{
    private static int count=0;
    private int PassengerID;
    private ArrayList<Offer> rideOffers;
    Passenger(String username, String password, String email, String mobileNumber)
    {
        super(username, password, email, mobileNumber);
        rideOffers = new ArrayList<Offer>();
        count++ ;
        this.PassengerID = count;
        SQLConnecction connection = SQLConnecction.getInstance();
        connection.insert(this);
    }

    public void setSuspended(boolean suspended) {
        super.setSuspended(suspended);
    }

    public void requestRide(String source, String destination)
    {
        RideRequest request = new RideRequest(source, destination, this);
        request.sendRequest();
    }


    @Override
    public void update(Object type, Object data) {
        rideOffers.add((Offer)data);
    }

    public int getPassengerID() {
        return PassengerID;
    }

    public void acceptOffer(Offer offer)
    {
        offer.getRequest().getRide().setReceiver(offer.getDriver());
        offer.getRequest().getRide().setStarted(true);

    }



    public void printRideOffers(){
        System.out.println(rideOffers);
    }
}

