//package com.company;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.*;


class Main {

    public static void main(String[] args) throws SQLException {


        Admin admin = new Admin();

        System.out.println("Registering as an already registered user:");
        Registration driver1reg = new DriverRegistration("hassanYoussef", "bleeding54321", "hassanYoussef@gmail.com", "012", "2627", "2010");
        Registration driver2reg = new DriverRegistration("mohebGamal2", "lannister12345", "mohebGamal2@gmail", "011", "7262", "2019");
        Registration passenger1Reg = new PassengerRegistration("lailaAhmed", "idk12345", "lailaAhmed@gmail.com", "011");
        Driver driver = new Driver("kareemDriver3", "california12345", "kareemDriver3@gmail", "011", "7262", "2019");


        System.out.println("Logging in as a registered passenger: ");
        Login log = new Login("lailaAhmed", "idk12345");

        System.out.println("Logging in as an unverified driver: ");
        Login log2 = new Login("mohebGamal2", "lannister12345");

        System.out.println("Inserting  wrong inputs while logging in: ");
        Login log3 = new Login("mohebGamal2","12346");
        Login log4 = new Login("driver5","12346");

        System.out.println("Registering as a new user: ");
        Registration driver4reg = new DriverRegistration("mohamedDriver4", "mohamed12345", "mohamedDriver5@gmail", "011", "7265", "2019");
        Registration passenger2Reg = new PassengerRegistration("bakiza", "bakiza12345", "bakiza@gmail.com", "012");

        System.out.println("Verifying a driver: ");
        admin.verifyDriver(((DriverRegistration) driver2reg).getDriver());
        if(((DriverRegistration) driver2reg).getDriver().getisVerified()){
            System.out.println( ((DriverRegistration) driver2reg).getDriver().getUsername()+"is verified");
        }

        System.out.println("Logging in as a driver after being verified: ");
        admin.verifyDriver(((DriverRegistration) driver2reg).getDriver());
        Login log5 = new Login("mohebGamal2", "lannister12345");


        System.out.println("Suspending a passenger: ");
        admin.suspend(((PassengerRegistration) passenger1Reg).getPassenger());
        System.out.println(((PassengerRegistration) passenger1Reg).getPassenger().getisSuspended());
        Login log6 = new Login("lailaAhmed", "idk12345");

        System.out.println("Suspending a driver: ");
        admin.suspend(((DriverRegistration) driver2reg).getDriver());
        System.out.println(((DriverRegistration) driver2reg).getDriver().getisSuspended());
        Login log7 = new Login("mohebGamal2", "lannister12345");

        // Adding Favorite areas
        driver.setFavoriteAreas("haram");
        driver.subscribeToArea();

        System.out.println("Requesting a ride: ");
        Passenger testPassenger = ((PassengerRegistration) passenger1Reg).getPassenger();
        testPassenger.requestRide("haram", "dokki");
        driver.printRequests();

        System.out.println("Sending an offer: ");
        driver.sendOffer(35.0, driver.getRequest(0));
        testPassenger.printRideOffers();
        // accepting offer
        testPassenger.acceptOffer(testPassenger.getOffer(0));

        System.out.println("rating a driver: ");
        driver.finishRide();
        testPassenger.rateDriver(3);
        System.out.println(driver.getAverageRating());

        System.out.println("rating a driver more than once:");
        testPassenger.rateDriver(1);
        System.out.println(driver.getAverageRating());

        System.out.println("Requesting new ride:");
        testPassenger.requestRide("haram", "maadi");
        driver.printRequests();
        System.out.println("Sending an offer: ");
        driver.sendOffer(70.0, driver.getRequest(0));
        testPassenger.printRideOffers();

        // accepting offer
        testPassenger.acceptOffer(testPassenger.getOffer(0));

        System.out.println("rating a driver: ");
        driver.finishRide();
        testPassenger.rateDriver(5);
        System.out.println(driver.getAverageRating());
    }
}

