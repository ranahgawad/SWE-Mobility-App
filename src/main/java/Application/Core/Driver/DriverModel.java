package Application.Core.Driver;

import Application.Core.Event.captainArrivalEvent;
import Application.Core.Event.rideEndEvent;
import Application.Core.Event.rideOfferEvent;
import Application.Core.Ride.DiscountManager;
import Application.Core.Ride.Offer;
import Application.Core.Ride.RideRequest;
import Application.Core.Storage.IPersistence;
import Application.Core.Storage.SQLImplementation;
import Application.Core.User.UserNotificationManager;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DriverModel {

    Driver driver;
    UserNotificationManager notificationSender;
    DriverModel(Driver driver)
    {
        this.driver = driver;
        notificationSender = new UserNotificationManager();
    }


    public void sendOffer(Double bill, RideRequest request) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        Offer offer = new Offer(driver, bill, request, 0.0);
        offer.setDiscount(DiscountManager.calculateDiscount(offer));
        offer.getRequest().getRide().getRideEvents().add(new rideOfferEvent(date, driver.getUsername(), bill));
        notificationSender = new UserNotificationManager(request);
        notificationSender.subscribe(request, request.getRide().getRequester());
        notificationSender.notify(request, offer);

    }

    public void finishRide() {
        IPersistence connection = SQLImplementation.getInstance();
        RideRequest rideReq;
        driver.setAvailable(true);
        driver.setCurrentCapacity(driver.getCurrentCapacity() + 1);
        for (int i = 0; i < driver.getRideRequests().size(); i++) {

            if (driver.getRideRequests().get(i).getRide().getisStarted() == true) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());
                rideReq = driver.getRideRequests().get(i);
                rideReq.getRide().setFinished(true);
                rideReq.getRide().getRequester().getPassengerModel().setCountRides();
                rideReq.getRide().getRideEvents().add(new rideEndEvent(date, driver.getUsername(), rideReq.getRide().getRequester().getUsername()));
                driver.getFinishedRides().add(rideReq.getRide());
                driver.getRideRequests().remove(rideReq);
                connection.updateRideisFinished(rideReq.getRide(), 1);
                return;
            }
        }


    }

    public void arriveAtLocation(){
        RideRequest rideReq;
        for(int i = 0; i< driver.getRideRequests().size(); i++){
            if(driver.getRideRequests().get(i).getRide().getisStarted() == true){
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());
                rideReq = driver.getRideRequests().get(i);
                rideReq.getRide().getRideEvents().add(new captainArrivalEvent(date, driver.getUsername(), rideReq.getRide().getRequester().getUsername()));
                return;
            }
        }
    }

    public void subscribeToArea()
    {


        for(int i = 0; i < driver.getFavoriteAreas().size(); i++)
        {
            if(RideRequest.notificationSender.getListeners(driver.getFavoriteAreas().get(i)) != null)
            {
                RideRequest.notificationSender.subscribe(driver.getFavoriteAreas().get(i), driver);
            }
            else
            {
                RideRequest.notificationSender.setListeners(driver.getFavoriteAreas().get(i));
                RideRequest.notificationSender.subscribe(driver.getFavoriteAreas().get(i), driver);
            }
        }
    }

    public void calculateAverageRating() {
        int sum = 0;
        for (int i = 0; i < driver.getDriverRatings().size(); i++) {
            sum += driver.getDriverRatings().get(i);
        }
        float driverRating = (float) (sum / driver.getDriverRatings().size());
        driver.setAverageRating(driverRating);

        IPersistence connection = SQLImplementation.getInstance();
        connection.updateDriverRating(driver, driverRating);
    }

    void insert(Driver driver)
    {
        IPersistence connection = SQLImplementation.getInstance();
        connection.insert(driver);
    }
}
