package Application.Core;

import java.util.List;

public interface IPersistence {
    void insert(User user);

    void delete(User user);

    void select(User user);

    void updateUserSuspened(User user);

    void updateDriverVerification(Driver driver, int state);

    void updateDriverRating(Driver driver, float rating);

    void insert(Ride ride);

    void insert(publicHolidays publicHolidays);

    void updateRideisStarted(Ride ride, int started);

    void updateRideisFinished(Ride ride, int finished);

    void updateCountRides(Passenger passenger, int countRides);

    void updateDiscount(Ride ride, double dicount);

    void updatePrice(Ride ride, double price);


    void clearTable(String tableName);

    List<Passenger> getAllPasengers();

    List<Driver> getAllDrivers();

    List<Driver> getPendingDriverVerifications();

}
