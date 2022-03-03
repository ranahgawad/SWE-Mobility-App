package Application.Core.Passenger;

import Application.Core.Storage.IPersistence;
import Application.Core.Storage.SQLImplementation;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public
class PassengerRegistration  {
    private Passenger passenger;
    private String username;
    private String password;
    private String email;
    private String mobileNumber;
    private String birthdayDate;

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

     PassengerRegistration(String username, String password, String email, String mobileNumber, String birthdayDate)  {
         this.username = username;
         this.password = password;
         this.email = email;
         this.mobileNumber = mobileNumber;
         this.birthdayDate=birthdayDate;
        passenger = new Passenger(username, password, email, mobileNumber,birthdayDate);
         IPersistence connection = SQLImplementation.getInstance();
        connection.insert(passenger);
        connection.setUserID(passenger);
    }

    public static boolean Regestier(Passenger passenger){
        IPersistence connection = SQLImplementation.getInstance();
        if(connection.insert(passenger)){
            connection.setUserID(passenger);
            return true;
        }
        else
            return false;
    }
    public Passenger getPassenger() {
        return passenger;
    }
}