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
        SQLConnecction connection = SQLConnecction.getInstance();
        connection.updateRideisStarted(offer.getRequest().getRide(), 1);
        for (int i=0; i<rideOffers.size(); i++){
            if(rideOffers.get(i) != offer){
                rideOffers.remove(i);
            }
        }
    }


    public Offer getOffer(int index)
    {
        return rideOffers.get(index);
    }

    public void rateDriver(int rating){

        if(rideOffers.isEmpty()){
            System.out.println("Cannot rate driver more than once");
        }else{
            rideOffers.get(0).getDriver().addRating(rating);
            rideOffers.remove(0);
        }
    }


    public void printRideOffers(){
        System.out.println(rideOffers);
    }
}

