//package com.company;

import java.sql.SQLException;

class Main {
    public static void main(String[] args) throws SQLException {

        Admin admin = new Admin();

        System.out.println("Registering as an already registered user:");
        Registration driver1reg = new DriverRegistration("hassanYoussef", "bleeding54321", "hassanYoussef@gmail.com", "012", "2627", "2010");
        Registration driver2reg = new DriverRegistration("mohebGamal2", "lannister12345", "mohebGamal2@gmail", "011", "7262", "2019");
        Registration passenger1Reg = new PassengerRegistration("lailaAhmed", "idk12345", "lailaAhmed@gmail.com", "011");
        Driver driver = new Driver("kareemDriver3", "california12345", "kareemDriver3@gmail", "011", "7262", "2019");


        System.out.println("\n" + "Logging in as a registered passenger: ");
        Login log = new Login("lailaAhmed", "idk12345");

        System.out.println("\n" + "Logging in as an unverified driver");
        Login log2 = new Login("mohebGamal2", "lannister12345");

        System.out.println("\n" + "Inserting  wrong inputs while logging in: ");
        Login log3 = new Login("mohebGamal2", "12346");
        Login log4 = new Login("driver5", "12346");

        System.out.println("\n" + "Registering as a new user: ");
        Registration driver4reg = new DriverRegistration("mohamedDriver4", "mohamed12345", "mohamedDriver5@gmail", "011", "7265", "2019");
        Registration passenger2Reg = new PassengerRegistration("bakiza", "bakiza12345", "bakiza@gmail.com", "012");

        System.out.println("\n" + "Verifying a driver: ");
        admin.getAdminModel().verifyDriver(((DriverRegistration) driver2reg).getDriver());
        if (((DriverRegistration) driver2reg).getDriver().getisVerified()) {
            System.out.println(((DriverRegistration) driver2reg).getDriver().getUsername() + "is verified");
        }

        System.out.println("\n" + "Logging in as a driver after being verified: ");
        admin.getAdminModel().verifyDriver(((DriverRegistration) driver2reg).getDriver());
        Login log5 = new Login("mohebGamal2", "lannister12345");


        System.out.println("\n" + "Suspending a passenger: ");
        admin.getAdminModel().suspend(((PassengerRegistration) passenger1Reg).getPassenger());
        if (((PassengerRegistration) passenger1Reg).getPassenger().getisSuspended()) {
            Login log6 = new Login("lailaAhmed", "idk12345");
        }

        System.out.println("\n" + "Suspending a driver: ");
        admin.getAdminModel().suspend(((DriverRegistration) driver2reg).getDriver());
        if (((DriverRegistration) driver2reg).getDriver().getisSuspended()) {
            Login log7 = new Login("mohebGamal2", "lannister12345");
        }

        System.out.println("\n" + "adding favorite areas");
        driver.setFavoriteAreas("haram");
       // driver.subscribeToArea();
        driver.printfavoriteAreas();

        System.out.println("\n" + "Requesting a ride: ");
        Passenger testPassenger = ((PassengerRegistration) passenger1Reg).getPassenger();
        testPassenger.getPassengerModel().requestRide("haram", "dokki");
        driver.printRequests();

        System.out.println("\n" + "Sending an offer: ");
        //driver.sendOffer(35.0, driver.getRequest(0));
        testPassenger.getPassengerModel().printRideOffers();
        System.out.println("offer accepted");
        testPassenger.getPassengerModel().acceptOffer(testPassenger.getPassengerModel().getOffer(0));

        System.out.println("\n" + "rating a driver: ");
        //driver.finishRide();
        testPassenger.getPassengerModel().rateDriver(3);
        System.out.println("Driver's average rating:" + driver.getAverageRating());

        System.out.println("\n" + "rating a driver more than once:");
        testPassenger.getPassengerModel().rateDriver(1);
        System.out.println("Driver's average rating:" + driver.getAverageRating());

        System.out.println("\n" + "Requesting new ride:");
        testPassenger.getPassengerModel().requestRide("haram", "maadi");
        driver.printRequests();
        System.out.println("\n" + "Sending an offer: ");
        //driver.sendOffer(70.0, driver.getRequest(0));
        testPassenger.getPassengerModel().printRideOffers();


        System.out.println("offer accepted");
        testPassenger.getPassengerModel().acceptOffer(testPassenger.getPassengerModel().getOffer(0));
        //driver.arriveAtLocation();
        //driver.finishRide();

        System.out.println("\nShowing the events that happended on the last ride");
        admin.getAdminModel().showEvents(driver.getFinishedRides().get(driver.getFinishedRides().size() -1 ));

        System.out.println("\n" + "rating a driver: ");
        testPassenger.getPassengerModel().rateDriver(5);
        System.out.println("Driver's average rating:" + driver.getAverageRating());

        System.out.println("\n" + "List all driver ratings");
        driver.printDriverRatings();

        System.out.println("\nRetriving all passengers");
        admin.getAdminModel().getAllPassengers();

        System.out.println("\nRetriving all drivers");
        admin.getAdminModel().getAllDrivers();

        System.out.println("\nRetriving all drivers pending requests");
        admin.getAdminModel().getPendingDriverVerifications();
    }
}

