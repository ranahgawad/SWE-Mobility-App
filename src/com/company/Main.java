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

        Driver driver = new Driver("driver1","12345","driver@gmail","012","7262","2019");
        Ride ride = new Ride("giza","el dokki",driver);
        RideRequest request = new RideRequest(ride);
       request.notificationSender.subscribe("el dokki",driver);
       request.sendRequest();
       driver.print();

    }
}

