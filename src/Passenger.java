//package com.company;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        SQLImplementation connection = SQLImplementation.getInstance();
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
        if(rideOffers == null || rideOffers.size() == 0){
            System.out.println("There are no ride offers to be accepted");
        }else{
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            offer.getRequest().getRide().setReceiver(offer.getDriver());
            offer.getRequest().getRide().setStarted(true);
            offer.getRequest().getRide().getRideEvents().add(new rideOfferAccepted(date, offer.getRequest().getRide().getRequester().getUsername()));
            SQLImplementation connection = SQLImplementation.getInstance();
            connection.updateRideisStarted(offer.getRequest().getRide(), 1);
            for (int i=0; i<rideOffers.size(); i++){
                if(rideOffers.get(i) != offer){
                    rideOffers.remove(i);
                }
            }
        }

    }


    public Offer getOffer(int index)
    {
        if(rideOffers == null || rideOffers.size() == 0){
            System.out.println("Currently there are no ride offers, If you made sure you requested a ride please wait for drivers to send you offers.");
            return null;
        }else{
            return rideOffers.get(index);
        }
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
        if(rideOffers == null || rideOffers.size() ==0){
            System.out.println("Currently there are no ride offers, If you made sure you requested a ride please wait for drivers to send you offers.");
        }else{
            System.out.println(rideOffers);
        }

    }
}

