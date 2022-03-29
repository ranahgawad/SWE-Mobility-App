package Application.Core.User;

import Application.Core.Driver.Driver;
import Application.Core.Passenger.Passenger;
import Application.Core.Storage.IPersistence;
import Application.Core.Storage.SQLImplementation;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Login {
     Login(){
     }
    public static boolean perfromLogin(Driver driver) {
        IPersistence connection = SQLImplementation.getInstance();
        driver.setLoggedIn(true);
        return connection.driverLogin(driver);
    }

    public static boolean perfromLogin(Passenger passenger) {
        IPersistence connection = SQLImplementation.getInstance();
        passenger.setLoggedIn(true);
        return connection.passengerLogin(passenger);
    }
}
