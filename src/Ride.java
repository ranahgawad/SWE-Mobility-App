//package com.company;


import java.util.ArrayList;

class Ride {

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
    private int num_passengers;

    public Ride(String source, String destination, Passenger requester, int num_passengers) {
        isStarted = false;
        isFinished = false;
        this.destination = destination;
        this.source = source;
        this.requester = requester;
        this.receiver = receiver;
        this.num_passengers = num_passengers;
        count++;
        this.rideID = count;
        this.rideEvents = new ArrayList<>();
        SQLImplementation connection = SQLImplementation.getInstance();
        connection.insert(this);

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
        this.receiver.setAvailable(false);
    }

    public String getDestination() {
        return destination;
    }

    public Passenger getRequester() {
        return requester;
    }


    public void setReceiver(Driver receiver) {
        this.receiver = receiver;
        this.receiver.setAvailable(false);
    }

    public String getSource() {
        return source;
    }

    public int getRideID() {
        return rideID;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
        this.receiver.setAvailable(true);
    }
}


class RideRequest {
    public static UserNotificationManager notificationSender = new UserNotificationManager();
    Ride ride;

    RideRequest(String source, String destination, Passenger requester, int num_passengers) {
        ride = new Ride(source, destination, requester, num_passengers);
        notificationSender.setListeners(ride.getDestination());
    }

    RideRequest(String source, String destination, Passenger requester) {
        ride = new Ride(source, destination, requester, 1);
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
        return "RideRequest{" +
                "destination='" + ride.getDestination() + '\'' +
                ", source='" + ride.getSource() + '\'' +
                ", requester=" + ride.getRequester() +
                '}';
    }
}

class Offer {
    Driver driver;
    Double offer;
    RideRequest request;
    public UserNotificationManager notificationSender;

    public Offer(Driver driver, Double offer, RideRequest request) {
        this.driver = driver;
        this.offer = offer;
        this.request = request;
        notificationSender = new UserNotificationManager(this.request);
    }

    public RideRequest getRequest() {
        return request;
    }

    public Driver getDriver() {
        return driver;
    }


    @Override
    public String toString() {
        return "Offer{" +
                "driver=" + driver +
                ", offer=" + offer +
                ", request=" + request +
                '}';
    }
}

