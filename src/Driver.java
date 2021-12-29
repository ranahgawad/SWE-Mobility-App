//package com.company;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Driver extends User {
    private static int count = 0;
    private int driverID;
    private boolean isVerfied = false;
    private String licenseNumber;
    private String nationalID;
    private boolean isAvailable;
    private ArrayList<String> favoriteAreas;
    private ArrayList<RideRequest> rideRequests;
    private float averageRating;
    public UserNotificationManager notificationSender;
    private ArrayList<Integer> driverRatings ;
    private ArrayList<Ride> finishedRides;

    Driver(String username, String password, String email, String mobileNumber, String licenseNumber, String nationalID) {
        super(username, password, email, mobileNumber);
        this.averageRating =0;
        this.licenseNumber = licenseNumber;
        this.nationalID = nationalID;
        rideRequests = new ArrayList<RideRequest>();
        favoriteAreas = new ArrayList<String>();
        notificationSender = new UserNotificationManager();
        count++;
        this.driverID = count;
        driverRatings = new ArrayList<>();
        finishedRides = new ArrayList<>();
        isAvailable = true;
        SQLImplementation connection = SQLImplementation.getInstance();
        connection.insert(this);

    }
    public void  printDriverRatings() {
        for( int i= 0 ; i < driverRatings.size();i++){
            System.out.println(driverRatings.get(i));
        }
    }
    public void printfavoriteAreas(){
        for( int i= 0 ; i < favoriteAreas.size();i++){
            System.out.println(favoriteAreas.get(i));
        }
    }

    @Override
    public void setSuspended(boolean suspended) {
        isAvailable = false;
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
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        Offer offer = new Offer(this, bill, request);
        offer.getRequest().getRide().getRideEvents().add(new rideOfferEvent(date, this.getUsername(), bill));
        notificationSender = new UserNotificationManager(request);
        notificationSender.subscribe(request, request.getRide().getRequester());
        notificationSender.notify(request, offer);

    }

    void setAvailable(boolean availability)
    {
        isAvailable = availability;
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
        SQLImplementation connection = SQLImplementation.getInstance();
        RideRequest rideReq;
        isAvailable = true;
        for(int i=0; i< rideRequests.size();i++){
            if(rideRequests.get(i).getRide().getisStarted() == true){
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());
                rideReq = rideRequests.get(i);
                rideReq.getRide().setFinished(true);
                rideReq.getRide().getRideEvents().add(new rideEndEvent(date,this.getUsername(), rideReq.getRide().getRequester().getUsername()));
                finishedRides.add(rideReq.getRide());
                rideRequests.remove(rideReq);
                connection.updateRideisFinished(rideReq.getRide(), 1);
                return;
            }
        }

    }

    public void arriveAtLocation(){
        RideRequest rideReq;
        for(int i=0; i< rideRequests.size();i++){
            if(rideRequests.get(i).getRide().getisStarted() == true){
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());
                rideReq = rideRequests.get(i);
                rideReq.getRide().getRideEvents().add(new captainArrivalEvent(date,this.getUsername(), rideReq.getRide().getRequester().getUsername()));
                return;
            }
        }
    }

    public void addRating(int rating) {

        driverRatings.add(rating);
        setAverageRating();
    }

    public void setAverageRating(){
        int sum =0;
        for(int i=0; i<driverRatings.size(); i++){
            sum+= driverRatings.get(i);
        }
        float driverRating = (float) (this.averageRating = sum / driverRatings.size());

        SQLImplementation connection = SQLImplementation.getInstance();
        connection.updateDriverRating(this, driverRating);
    }

    public RideRequest getRequest(int index)
    {
        return rideRequests.get(index);
    }

    @Override
    public void update(Object type, Object data) {
        if(isAvailable == true)
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

    public ArrayList<RideRequest> getRideRequests() {
        return rideRequests;
    }

    public ArrayList<Ride> getFinishedRides() {
        return finishedRides;
    }
}
