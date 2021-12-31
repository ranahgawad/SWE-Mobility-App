package Application.Core;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AdminModel {
    Admin admin;

    AdminModel(Admin admin){
        this.admin = admin;
    }
    public static List<Passenger> getAllPassengers(){
        IPersistence connection = SQLImplementation.getInstance();
        return connection.getAllPasengers();
    }

    public static List<Driver> getAllDrivers(){
        IPersistence connection = SQLImplementation.getInstance();
        return  connection.getAllDrivers();
    }

    public static List<Driver> getPendingDriverVerifications(){
        IPersistence connection = SQLImplementation.getInstance();
        return connection.getPendingDriverVerifications();
    }

    public static List<Event> showRideEvents(Ride ride){
        List<Event> rideEvents = ride.getRideEvents();
        for(int i=0; i< rideEvents.size(); i++){
            System.out.println(rideEvents.get(i).toString());
        }
        return rideEvents;
    }

    public static boolean verifyDriver(Driver driver)
    {
        driver.setVerfied(true);
        IPersistence connection = SQLImplementation.getInstance();
        connection.updateDriverVerification(driver, 1);
        return true;
    }
    public static boolean suspend(User user)
    {
        user.setSuspended(true);
        IPersistence connection = SQLImplementation.getInstance();
        connection.updateUserSuspened(user);
        return true;
    }


}
