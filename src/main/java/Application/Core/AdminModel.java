package Application.Core;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AdminModel {

    Admin admin;

    AdminModel(Admin admin){
        this.admin = admin;
    }
    public Object[] getAllPassengers(){
        IPersistence connection = SQLImplementation.getInstance();
        ArrayList<Passenger> passengers= connection.getAllPasengers();
        return passengers.toArray();

    }

    public ArrayList<Driver> getAllDrivers(){
        IPersistence connection = SQLImplementation.getInstance();
        return  connection.getAllDrivers();
    }

    public static ArrayList<Driver> getPendingDriverVerifications(){
        IPersistence connection = SQLImplementation.getInstance();
        return connection.getPendingDriverVerifications();
    }

    public List<Event> showRideEvents(Ride ride){
        List<Event> rideEvents = ride.getRideEvents();
        for(int i=0; i< rideEvents.size(); i++){
            System.out.println(rideEvents.get(i).toString());
        }
        return rideEvents;
    }

    public boolean verifyDriver(Driver driver)
    {
        driver.setVerfied(true);
        IPersistence connection = SQLImplementation.getInstance();
        connection.updateDriverVerification(driver, 1);
        return true;
    }
    public boolean suspend(User user)
    {
        user.setSuspended(true);
        IPersistence connection = SQLImplementation.getInstance();
        connection.updateUserSuspened(user);
        return true;
    }


}
