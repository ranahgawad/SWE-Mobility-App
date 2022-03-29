package Application.Core.Ride;

import Application.Core.Admin.Admin;
import Application.Core.Passenger.Passenger;
import Application.Core.User.UserNotificationManager;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public
class RideRequest {
    public static UserNotificationManager notificationSender = new UserNotificationManager();
    Ride ride;
    int numPassengers;

    public RideRequest(String source, String destination, Passenger requester, int numPassengers) {
        ride = new Ride(source, destination, requester);
        ride.insertRide(ride);
        this.numPassengers = numPassengers;
        Admin.addRide(ride);
        notificationSender.setListeners(ride.getDestination());
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    public Ride getRide() {
        return ride;
    }

    public void sendRequest() {
        notificationSender.notify(ride.getSource(), this);
    }


    @Override
    public String toString() {
        return "Application.Core.Ride.RideRequest{" +
                "destination='" + ride.getDestination() + '\'' +
                ", source='" + ride.getSource() + '\'' +
                ", requester=" + ride.getRequester() +
                '}';
    }
}
