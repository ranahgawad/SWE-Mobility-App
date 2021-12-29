import java.util.ArrayList;

public class AdminModel {
    Admin admin;

    AdminModel(Admin admin){
        this.admin = admin;
    }
    public void getAllPassengers(){
        IPersistence connection = SQLImplementation.getInstance();
        connection.getAllPasengers();
    }

    public void getAllDrivers(){
        IPersistence connection = SQLImplementation.getInstance();
        connection.getAllDrivers();
    }

    public void getPendingDriverVerifications(){
        IPersistence connection = SQLImplementation.getInstance();
        connection.getPendingDriverVerifications();
    }

    public void showEvents(Ride ride){
        ArrayList<Event> rideEvents = ride.getRideEvents();
        for(int i=0; i< rideEvents.size(); i++){
            System.out.println(rideEvents.get(i).toString());
        }
    }

    public void verifyDriver(Driver driver)
    {
        driver.setVerfied(true);
        IPersistence connection = SQLImplementation.getInstance();
        connection.updateDriverVerification(driver, 1);
    }
    public void suspend(User user)
    {
        user.setSuspended(true);
        IPersistence connection = SQLImplementation.getInstance();
        connection.updateUserSuspened(user);
    }


}
