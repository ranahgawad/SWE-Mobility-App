package Application.Core;//package com.company;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class Ride {

    RideRequest rideRequest;
    private boolean isStarted;
    private boolean isFinished;
    private String destination;
    private String source;
    private Passenger requester;
    private Driver receiver;
    public static int count = 0;
    private int rideID;
    private ArrayList<Event> rideEvents;

    public Ride(String source, String destination, Passenger requester) {
        isStarted = false;
        isFinished = false;
        this.destination = destination;
        this.source = source;
        this.requester = requester;
        this.receiver = receiver;
//        count++;
//        this.rideID = count;
        this.rideEvents = new ArrayList<>();
        SQLImplementation connection = SQLImplementation.getInstance();
        connection.insert(this);
        connection.setRideID(this);
    }

    public ArrayList<Event> getRideEvents() {
        return rideEvents;
    }


    public boolean getisStarted() {
        return isStarted;
    }

    public boolean getisFinished() {
        return isFinished;
    }


    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setRequester(Passenger requester) {
        this.requester = requester;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public String getDestination() {
        return destination;
    }

    public Passenger getRequester() {
        return requester;
    }

    public void setRideID(int rideID) {
        this.rideID = rideID;
    }

    public void setReceiver(Driver receiver) {
        this.receiver = receiver;
    }

    public String getSource() {
        return source;
    }

    public int getRideID() {
        return rideID;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

class RideRequest {
    public static UserNotificationManager notificationSender = new UserNotificationManager();
    Ride ride;

    RideRequest(String source, String destination, Passenger requester, int num_passengers) {
        ride = new Ride(source, destination, requester);
        notificationSender.setListeners(ride.getDestination());
    }


    public Ride getRide() {
        return ride;
    }

    public void sendRequest() {
        notificationSender.notify(ride.getSource(), this);
    }


    @Override
    public String toString() {
        return "Application.Core.RideRequest{" +
                "destination='" + ride.getDestination() + '\'' +
                ", source='" + ride.getSource() + '\'' +
                ", requester=" + ride.getRequester() +
                '}';
    }
}
class Offer {
    Driver driver;
    double offer;
    RideRequest request;
    public UserNotificationManager notificationSender;
    public double discount;
    int numPassengers;
    public Offer(Driver driver, Double offer, RideRequest request, double discount) {
        this.driver = driver;
        this.offer = offer;
        this.request = request;
        this.discount=discount;
        numPassengers = driver.getCarCapacity() - driver.getCurrentCapacity();
        notificationSender = new UserNotificationManager(this.request);
    }


    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Double getOffer() {
        return offer;
    }

    public void setOffer(Double offer) {
        this.offer = offer;
    }

    public RideRequest getRequest() {
        return request;
    }

    public Driver getDriver() {
        return driver;
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    @Override
    public String toString() {
        return "Application.Core.Offer{" +
                "driver=" + driver +
                ", discount="+(1-discount)*100+"%"+
                ", offer=" + offer +
                ", offer after discount="+offer*discount+
                ", request=" + request +
                '}';
    }
}









