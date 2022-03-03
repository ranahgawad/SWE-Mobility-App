//package com.company;
package Application.Core.Driver;
import Application.Core.Ride.Ride;
import Application.Core.Ride.RideRequest;
import Application.Core.Storage.IPersistence;
import Application.Core.Storage.SQLImplementation;
import Application.Core.User.User;
import Application.Core.User.UserNotificationManager;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Driver extends User {
    private int driverID;
    private boolean isVerfied = false;
    private String licenseNumber;
    private String nationalID;
    private boolean isAvailable;
    private ArrayList<String> favoriteAreas;
    @JsonIgnore
    private ArrayList<RideRequest> rideRequests;
    private float averageRating;
    @JsonIgnore
    public UserNotificationManager notificationSender;
    private ArrayList<Integer> driverRatings;
    @JsonIgnore
    private ArrayList<Ride> finishedRides;
    private int currentCapacity;
    private int carCapacity;
    private double balance;
    @JsonIgnore
    private DriverModel driverModel;

    public Driver(String username, String password, String email, String mobileNumber, String licenseNumber, String nationalID) {
        super(username, password, email, mobileNumber);
        this.averageRating = 0;
        this.licenseNumber = licenseNumber;
        this.nationalID = nationalID;
        rideRequests = new ArrayList<RideRequest>();
        favoriteAreas = new ArrayList<String>();
        notificationSender = new UserNotificationManager();
        balance = 0;
        carCapacity = 4; // default
        currentCapacity = carCapacity;
        driverRatings = new ArrayList<>();
        finishedRides = new ArrayList<>();
        driverModel = new DriverModel(this);
        isAvailable = true;
    }

    public void printDriverRatings() {
        for (int i = 0; i < driverRatings.size(); i++) {
            System.out.println(driverRatings.get(i));
        }
    }

    public void printfavoriteAreas() {
        for (int i = 0; i < favoriteAreas.size(); i++) {
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
        IPersistence connection = SQLImplementation.getInstance();

        for (String area : areas) {
            connection.insert(this,area);
            this.favoriteAreas.add(area);
        }
    }

    public List<String> getFavoriteAreas() {
        IPersistence connection = SQLImplementation.getInstance();
        return connection.getDriverFavoriteAreas(this);
    }

    void setAvailable(boolean availability) {
        isAvailable = availability;
    }

    public void setCarCapacity(int capacity) {
        carCapacity = capacity;
        currentCapacity = carCapacity;
    }

    public int getCarCapacity() {
        return carCapacity;
    }

    public void setCurrentCapacity(int capacity) {
        currentCapacity = capacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void addRating(int rating) {
        driverRatings.add(rating);
        driverModel.calculateAverageRating();
    }

    public RideRequest getRequest(int index) {
        return rideRequests.get(index);
    }

    @Override
    public void update(Object type, Object data) {
        RideRequest request = (RideRequest)data;
        //isAvailable == true ||
        if (currentCapacity > 0 || (request.getNumPassengers() == carCapacity - currentCapacity) ||
                (request.getNumPassengers() == 0 && carCapacity == currentCapacity))
        {
            rideRequests.add((RideRequest) data);
        }

        System.out.println(rideRequests);
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
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
        IPersistence connection = SQLImplementation.getInstance();
        this.balance = balance;
        connection.updateDriverBalance(this);
    }

    public ArrayList<Integer> getDriverRatings() {
        return driverRatings;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public double getBalance() {
        return balance;
    }


}

