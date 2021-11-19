package com.company;

import java.util.ArrayList;

public class Driver extends User
{
    private String licenseNumber;
    private String id;
    private boolean isAvailable;
    private ArrayList<String> favoriteAreas;
    private ArrayList<RideRequest> rideRequests;
    private float averageRating;

    Driver(String username, String password, String email, String mobileNumber, String licenseNumber, String id)
    {
        super(username, password, email, mobileNumber);
        this.licenseNumber = licenseNumber;
        this.id = id;
        rideRequests = new ArrayList<RideRequest>();

    }
    public void sendOffer()
    {

    }

    public void finishRide()
    {

    }

    @Override
    public void update(Object type, Object data) {

        rideRequests.add((RideRequest)data);
    }

    @Override
    public void setSuspended(boolean suspended) {
        super.setSuspended(suspended);
    }

    void print()
    {
        System.out.println(rideRequests);
    }

}