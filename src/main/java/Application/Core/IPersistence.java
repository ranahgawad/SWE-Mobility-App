package Application.Core;

import java.util.ArrayList;
import java.util.List;

public interface IPersistence {
    void insert(User user);

    void delete(User user);

    void select(User user);

    void updateUserSuspened(User user);

    void updateDriverVerification(Driver driver, int state);

    void updateDriverRating(Driver driver, float rating);

    void insert(Ride ride);

    void updateRideisStarted(Ride ride, int started);

    void updateRideisFinished(Ride ride, int finished);

    void clearTable(String tableName);

    List<Passenger> getAllPasengers();

    List<Driver> getAllDrivers();

    List<Driver> getPendingDriverVerifications();

}
