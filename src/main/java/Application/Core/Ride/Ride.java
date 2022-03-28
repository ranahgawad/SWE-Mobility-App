package Application.Core.Ride;//package com.company;

import Application.Core.Driver.Driver;
import Application.Core.Event.Event;
import Application.Core.Passenger.Passenger;
import Application.Core.Storage.IPersistence;
import Application.Core.Storage.SQLImplementation;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class Ride {

    RideRequest rideRequest;
    int driverID;
    private boolean isStarted;
    private boolean isFinished;
    private String destination;
    private String source;
    private Passenger requester;
    private Driver receiver;
    private int rideID;
    private ArrayList<Event> rideEvents;

    public Ride(String source, String destination, Passenger requester) {
        isStarted = false;
        isFinished = false;
        this.destination = destination;
        this.source = source;
        this.requester = requester;
        this.receiver = receiver;
        this.rideEvents = new ArrayList<>();

    }


    public void insertRide(Ride ride){
        IPersistence connection = SQLImplementation.getInstance();
        connection.insert(ride);
        connection.setRideID(ride);
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
    public Driver getReceiver() {
        return receiver;
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

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }
}









