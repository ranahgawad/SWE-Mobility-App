package com.company;

public class Ride {
    private String destination;
    private String source;
    private Passenger requester;
    private Driver receiver;

    public Ride(String destination,String source, Driver receiver)
    {
        this.destination = destination;
        this.source = source;
        //this.requester = requester;
        this.receiver = receiver;
    }
    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "destination='" + destination + '\'' +
                ", source='" + source + '\'' +
                ", requester=" + requester +
                ", receiver=" + receiver +
                '}';
    }
}

class RideRequest
{
    public UserNotificationManager notificationSender;
    private Ride ride;
    //private Status status;

    public RideRequest(Ride ride)
    {
        this.ride = ride;
        notificationSender = new UserNotificationManager("el dokki");
    }

    public void sendRequest()
    {
        notificationSender.notify("el dokki",this);
    }

    @Override
    public String toString() {
        return "RideRequest{" +
                "ride=" + ride +
                '}';
    }
}