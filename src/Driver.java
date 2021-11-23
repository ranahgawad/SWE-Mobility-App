//package com.company;

import java.util.ArrayList;

public class Driver extends User {
    private static int count = 0;
    private int driverID;
    private boolean isVerfied = false;
    private String licenseNumber;
    private String nationalID;
    //    private boolean isAvailable;
    private ArrayList<String> favoriteAreas;
    private ArrayList<RideRequest> rideRequests;
    private float averageRating;
    public UserNotificationManager notificationSender;

    Driver(String username, String password, String email, String mobileNumber, String licenseNumber, String nationalID) {
        super(username, password, email, mobileNumber);
        this.licenseNumber = licenseNumber;
        this.nationalID = nationalID;
        rideRequests = new ArrayList<RideRequest>();
        favoriteAreas = new ArrayList<String>();
        notificationSender = new UserNotificationManager();
        count++;
        this.driverID = count;
        SQLConnecction connection = SQLConnecction.getInstance();
        connection.insert(this);

    }

    @Override
    public void setSuspended(boolean suspended) {
        super.setSuspended(suspended);

    }

    public void setVerfied(boolean verfied) {
        this.isVerfied = verfied;
    }

    public void setFavoriteAreas(String... areas) {
        for (String area : areas) {
            this.favoriteAreas.add(area);
        }
    }

    public ArrayList<String> getFavoriteAreas() {
        return favoriteAreas;
    }

    public void sendOffer(Double bill, RideRequest request) {
        Offer offer = new Offer(this, bill, request);
        notificationSender = new UserNotificationManager(request);
        notificationSender.subscribe(request, request.getRide().getRequester());
        notificationSender.notify(request, offer);
    }

    public void subscribeToArea()
    {
        for(int i = 0; i < favoriteAreas.size(); i++)
        {
            if(RideRequest.notificationSender.getListeners(favoriteAreas.get(i)) != null)
            {
                RideRequest.notificationSender.subscribe(favoriteAreas.get(i), this);
            }
            else
            {
                RideRequest.notificationSender.setListeners(favoriteAreas.get(i));
                RideRequest.notificationSender.subscribe(favoriteAreas.get(i), this);
            }
        }
    }

    public void finishRide() {

    }

    public RideRequest getRequest(int index)
    {
        return rideRequests.get(index);
    }

    @Override
    public void update(Object type, Object data) {
        rideRequests.add((RideRequest) data);
    }

    void printRequests() {
        System.out.println(rideRequests);
        //System.out.println(favoriteAreas);
    }

    public int getDriverID() {
        return driverID;
    }

    public boolean getisVerified() {
        return isVerfied;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public String getNationalID() {
        return nationalID;
    }
}