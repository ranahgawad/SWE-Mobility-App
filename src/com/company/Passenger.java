package com.company;

import java.util.ArrayList;

public class Passenger extends User
{
    private ArrayList<Offer> rideOffers;
    Passenger(String username, String password, String email, String mobileNumber)
    {
        super(username, password, email, mobileNumber);
        rideOffers = new ArrayList<Offer>();
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

    public void acceptOffer(Offer offer)
    {
        offer.getRequest().setReceiver(offer.getDriver());
    }

    void print()
    {
        System.out.println(rideOffers);
    }
}

