package Application.Core.Storage;

import Application.Core.Driver.Driver;
import Application.Core.Passenger.Passenger;
import Application.Core.Ride.Ride;
import Application.Core.User.User;
import Application.Core.Ride.publicHolidays;

import java.util.List;

public interface IPersistence {
    void setUserID(User user);

    void setRideID(Ride ride);

    boolean setRideDriver(Ride ride, Driver driver);

    boolean insert(User user);

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

    boolean insert(User user, String area);

    List<String> getDriverFavoriteAreas(User user);

    void clearTable(String tableName);

    List<Passenger> getAllPasengers();

    List<Driver> getAllDrivers();

    Driver getCurrentDriver(Driver user);

    Passenger getCurrentPassenger(Passenger user);

    List<Driver> getPendingDriverVerifications();

    boolean insert(String destination);

    boolean checkDiscountedDestinations(String destination);

    boolean updateDriverBalance(Driver driver);

    boolean checkPublicHoliday(String currDate);
}
