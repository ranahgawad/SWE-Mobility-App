package com.company;

import java.util.*;
class Main
{
    class Offer
    {
        private double offer;
        private Driver driver;

        Offer(double offer, Driver driver)
        {
            this.offer = offer;
            this.driver = driver;
        }

        public void setDriver(Driver driver) {
            this.driver = driver;
        }

        public void setOffer(double offer) {
            this.offer = offer;
        }

        public double getOffer() {
            return offer;
        }

        public Driver getDriver() {
            return driver;
        }


}
    public static void main(String[] args)
    {
        Admin admin = new Admin();
        DriverRegistration reg =  new DriverRegistration("driver1","12345","driver@gmail","012","7262","2019");
        reg.notificationSender.subscribe("driver registration",admin);
        reg.registerUser();
        admin.print();
        Passenger passenger = new Passenger("passenger1", "12345", "passenger@gmail","012");
        Driver driver1 = new Driver("driver1","12345","driver1@gmail","011","7262","2019");
        Driver driver2 = new Driver("driver2","54321","driver2@gmail","012","2627","2010");
        RideRequest request1 = new RideRequest("el dokki","giza",passenger);
        RideRequest request2 = new RideRequest("el daher","giza",passenger);
        driver1.setFavoriteAreas("el dokki");
        driver2.setFavoriteAreas("el daher");
       request1.subscribeDriver(driver1);
       request1.sendRequest();
        driver1.print();

        request2.subscribeDriver(driver2);
        request2.sendRequest();
        driver2.print();



        driver1.sendOffer(35.0, request1);
        driver2.sendOffer(37.0, request2);

       passenger.print();
    }
}

