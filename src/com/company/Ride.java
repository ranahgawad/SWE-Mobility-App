package com.company;


import java.util.ArrayList;

class RideRequest
{
    public static UserNotificationManager notificationSender = new UserNotificationManager();
    private String destination;
    private String source;
    private Passenger requester;
    private Driver receiver;

    public RideRequest(String destination,String source, Passenger requester)
    {
        this.destination = destination;
        this.source = source;
        this.requester = requester;
        this.receiver = receiver;
        notificationSender.setListeners(destination);

    }
    public String getDestination() {
        return destination;
    }

    public Passenger getRequester() {
        return requester;
    }


    public void setReceiver(Driver receiver) {
        this.receiver = receiver;
    }


    public void subscribeDriver(Driver driver)
    {
        ArrayList<String> areas = driver.getFavoriteAreas();
        for(int i = 0; i < areas.size(); i++)
        {
            if(notificationSender.getListeners(areas.get(i)) != null)
            {
                notificationSender.subscribe(areas.get(i), driver);
            }

        }
    }

    public void sendRequest()
    {
        notificationSender.notify(destination,this);
    }


    @Override
    public String toString() {
        return "RideRequest{" +
                ", destination='" + destination + '\'' +
                ", source='" + source + '\'' +
                ", requester=" + requester +
                ", receiver=" + receiver +
                '}';
    }
}

class Offer
{
    Driver driver;
    Double offer;
    RideRequest request;
    public UserNotificationManager notificationSender;

    public Offer(Driver driver, Double offer, RideRequest request)
    {
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