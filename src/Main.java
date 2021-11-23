//package com.company;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.*;


class Main {
    class Offer {
        private double offer;
        private Driver driver;

        Offer(double offer, Driver driver) {
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

    public static void main(String[] args) throws SQLException {


        Admin admin = new Admin();

        Registration driver1reg = new DriverRegistration("driver1", "54321", "driver2@gmail", "012", "2627", "2010");
        Registration driver2reg = new DriverRegistration("driver2", "12345", "driver1@gmail", "011", "7262", "2019");
        Registration passenger1Reg = new PassengerRegistration("laila1", "12345", "laila@gmail.com", "011");

        // Logging in passenger and driver
        Login log = new Login("laila1", "12345");
        Login log2 = new Login("driver2", "12345");

        Driver driver = new Driver("driver3", "12345", "driver1@gmail", "011", "7262", "2019");
        DriverRegistration driver4 = new DriverRegistration("driver4", "12345", "driver1@gmail", "011", "7262", "2019");

        // Verfiying a driver
        admin.verifyDriver(((DriverRegistration) driver4).getDriver());
        System.out.println(((DriverRegistration) driver4).getDriver().getisVerified());
        admin.verifyDriver(((DriverRegistration) driver4).getDriver());


        // Logging after verifying
        admin.verifyDriver(((DriverRegistration) driver2reg).getDriver());
        Login log3 = new Login("driver2", "12345");
        

        // Suspending driver and passenger
        admin.suspend(((DriverRegistration) driver2reg).getDriver());
        admin.suspend(((PassengerRegistration) passenger1Reg).getPassenger());
        System.out.println(((PassengerRegistration) passenger1Reg).getPassenger().getisSuspended());

        driver.setFavoriteAreas("haram");
        driver.subscribeToArea();
        // Requesting a ride
        ((PassengerRegistration) passenger1Reg).getPassenger().requestRide("haram", "dokki");
        driver.printRequests();
        driver.sendOffer(35.0, driver.getRequest(0));
        ((PassengerRegistration) passenger1Reg).getPassenger().printRideOffers();


    }
}

