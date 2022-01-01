package Application.Core;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;
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
       for(int i=0; i<Admin.getRides().size(); i++){
           if(Admin.getRides().get(i).getRideID() == ride.getRideID())
               return Admin.getRides().get(i).getRideEvents();
       }
       return null;
    }
    public static boolean addDiscount(String destination){
       return DiscountManager.addDiscount(destination);
    }
    public static boolean addPublicHoliday(publicHolidays publicHolidays){
        return publicHolidays.addPublicHoliday(publicHolidays);
    }

    public static boolean verifyDriver(Driver driver)
    {
        driver.setVerfied(true);
        IPersistence connection = SQLImplementation.getInstance();
        return connection.updateDriverVerification(driver, 1);

    }
    public static boolean suspendDriver(Driver driver)
    {
        driver.setSuspended(true);
        IPersistence connection = SQLImplementation.getInstance();
        return connection.suspendDriver(driver);
    }

    public static boolean suspendPassenger(Passenger pass)
    {
        pass.setSuspended(true);
        IPersistence connection = SQLImplementation.getInstance();
        return connection.suspendPassenger(pass);
    }

    public static ArrayList<Ride> getAllRides(){
        return Admin.getRides();
    }

}
