package Application.Core;

import java.util.List;

public interface IPersistence {
    boolean insert(User user);

    void delete(User user);

    void select(User user);

    boolean suspendPassenger(Passenger passenger);

    boolean suspendDriver(Driver driver);

    boolean updateDriverVerification(Driver driver, int state);

    boolean passengerLogin(Passenger passenger);

    boolean driverLogin(Driver driver);

    boolean updateDriverRating(Driver driver, float rating);

    void insert(Ride ride);

    boolean insert(publicHolidays publicHolidays);

    boolean updateRideisStarted(Ride ride, int started);

    boolean updateRideisFinished(Ride ride, int finished);

    boolean updateCountRides(Passenger passenger, int countRides);

    boolean updateDiscount(Ride ride, double dicount);

    boolean updatePrice(Ride ride, double price);

    void clearTable(String tableName);

    List<Passenger> getAllPasengers();

    List<Driver> getAllDrivers();

    Driver getCurrentDriver(Driver user);

    Passenger getCurrentPassenger(Passenger user);

    List<Driver> getPendingDriverVerifications();

}
