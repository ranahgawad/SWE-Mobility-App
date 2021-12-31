//package com.company;
package Application.Core;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
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
    private DriverModel driverModel;
    public UserNotificationManager notificationSender;
    private ArrayList<Integer> driverRatings ;
    private ArrayList<Ride> finishedRides;
    private int carCapacity;
    private int currentCapacity;
    private double balance;

    Driver(String username, String password, String email, String mobileNumber, String licenseNumber, String nationalID) {
        super(username, password, email, mobileNumber);
        this.averageRating =0;
        this.licenseNumber = licenseNumber;
        this.nationalID = nationalID;
        rideRequests = new ArrayList<RideRequest>();
        favoriteAreas = new ArrayList<String>();
        notificationSender = new UserNotificationManager();
        balance=0;
        count++;
        this.driverID = count;
        driverRatings = new ArrayList<>();
        finishedRides = new ArrayList<>();
        driverModel = new DriverModel(this);
        isAvailable = true;
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
        Offer offer = new Offer(this, bill, request,0.0);
        offer.setDiscount(DiscountManager.calculateDiscount(offer));
        offer.getRequest().getRide().getRideEvents().add(new rideOfferEvent(date, this.getUsername(), bill));
        notificationSender = new UserNotificationManager(request);
        notificationSender.subscribe(request, request.getRide().getRequester());
        notificationSender.notify(request, offer);

    }

    void setAvailable(boolean availability)
    {
        isAvailable = availability;
    }


    public void setCarCapacity(int capacity)
    {
        carCapacity = capacity;
        currentCapacity = carCapacity;
    }

    public int getCarCapacity(int capacity)
    {
        return carCapacity;
    }

    public void setCurrentCapacity(int capacity)
    {
        currentCapacity = capacity;
    }

    public int getCurrentCapacity()
    {
        return currentCapacity;
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
        if(isAvailable == true || currentCapacity > 0)
            rideRequests.add((RideRequest) data);
    }

    void printRequests() {
        System.out.println(rideRequests);
        //System.out.println(favoriteAreas);
    }

    public DriverModel getDriverModel() {
        return driverModel;
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
    public void setBalance(double balance) {
        SQLImplementation connection = SQLImplementation.getInstance();
        this.balance = balance;
        connection.updateDriverBalance(this);
    }
    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverID=" + driverID +
                ", isVerfied=" + isVerfied +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", nationalID='" + nationalID + '\'' +
                ", isAvailable=" + isAvailable +
                ", favoriteAreas=" + favoriteAreas +
                ", rideRequests=" + rideRequests +
                ", averageRating=" + averageRating +
                ", driverModel=" + driverModel +
                ", notificationSender=" + notificationSender +
                ", driverRatings=" + driverRatings +
                ", finishedRides=" + finishedRides +
                ", carCapacity=" + carCapacity +
                ", currentCapacity=" + currentCapacity +
                ", balance=" + balance +
                '}';
    }
}
