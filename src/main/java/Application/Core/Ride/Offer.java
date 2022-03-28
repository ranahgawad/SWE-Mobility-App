package Application.Core.Ride;

import Application.Core.Driver.Driver;
import Application.Core.User.UserNotificationManager;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Offer {
    Driver driver;
    double offer;
    @JsonIgnore
    RideRequest request;
    @JsonIgnore
    public UserNotificationManager notificationSender;
    public double discount;
    int numPassengers;

    public Offer(Driver driver, Double offer, RideRequest request, double discount) {
        this.driver = driver;
        this.offer = offer;
        this.request = request;
        this.discount = discount;
        numPassengers = driver.getCarCapacity() - driver.getCurrentCapacity();
        notificationSender = new UserNotificationManager(this.request);
    }


    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Double getOffer() {
        return offer;
    }

    public void setOffer(Double offer) {
        this.offer = offer;
    }

    public RideRequest getRequest() {
        return request;
    }

    public Driver getDriver() {
        return driver;
    }

    public int getNumPassengers() {
        return numPassengers;
    }

    @Override
    public String toString() {
        return "Application.Core.Ride.Offer{" +
                "driver=" + driver +
                ", discount=" + (1 - discount) * 100 + "%" +
                ", offer=" + offer +
                ", offer after discount=" + offer * discount +
                ", request=" + request +
                '}';
    }
}
