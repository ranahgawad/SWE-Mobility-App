package Application.Core;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public
class PassengerRegistration extends Registration {
    private Passenger passenger;
    private String birthdayDate;

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public PassengerRegistration(String username, String password, String email, String mobileNumber, String birthdayDate)  {

        this.birthdayDate=birthdayDate;
        passenger = new Passenger(username, password, email, mobileNumber,birthdayDate);
        SQLImplementation connection = SQLImplementation.getInstance();
        connection.insert(passenger);
        connection.insertUser((User) passenger);
        userList.add(passenger);
    }

    public Passenger getPassenger() {
        return passenger;
    }
}