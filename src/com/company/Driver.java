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
    public UserNotificationManager notificationSender;

    Driver(String username, String password, String email, String mobileNumber, String licenseNumber, String id)
    {
        super(username, password, email, mobileNumber);
        this.licenseNumber = licenseNumber;
        this.id = id;
        rideRequests = new ArrayList<RideRequest>();
        favoriteAreas = new ArrayList<String>();
        notificationSender = new UserNotificationManager();
    }

    @Override
    public void setSuspended(boolean suspended) {
        super.setSuspended(suspended);
    }


    public void setFavoriteAreas(String...  areas)
    {
        for (String area : areas) {
            this.favoriteAreas.add(area);
        }
    }

    public ArrayList<String> getFavoriteAreas() {
        return favoriteAreas;
    }

    public void sendOffer(Double bill, RideRequest request)
    {
        Offer offer = new Offer(this, bill, request);
        notificationSender = new UserNotificationManager(request);
        notificationSender.subscribe(request,request.getRequester());
        notificationSender.notify(request, offer);
    }

    public void finishRide()
    {

    }

    @Override
    public void update(Object type, Object data) {

        rideRequests.add((RideRequest)data);
    }

    void print()
    {
        System.out.println(rideRequests);
        //System.out.println(favoriteAreas);
    }

}