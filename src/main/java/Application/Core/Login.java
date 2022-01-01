package Application.Core;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Login {

     Login(){
     }
    static boolean perfromLogin(Driver driver) {
        SQLImplementation connection = SQLImplementation.getInstance();
        return connection.driverLogin(driver);
    }

    static boolean perfromLogin(Passenger passenger) {
        SQLImplementation connection = SQLImplementation.getInstance();
        return connection.passengerLogin(passenger);
    }




}
