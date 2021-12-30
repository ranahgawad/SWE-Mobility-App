import java.text.SimpleDateFormat;
import java.util.Date;

public class DriverModel {

    Driver driver;
    UserNotificationManager notificationSender;
    DriverModel(Driver driver)
    {
        this.driver = driver;
        notificationSender = new UserNotificationManager();
    }
    public void sendOffer(Driver driver, Double bill, RideRequest request) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        Offer offer = new Offer(driver, bill, request);
        offer.getRequest().getRide().getRideEvents().add(new rideOfferEvent(date, driver.getUsername(), bill));
        notificationSender = new UserNotificationManager(request);
        notificationSender.subscribe(request, request.getRide().getRequester());
        notificationSender.notify(request, offer);

    }

    public void finishRide() {
        SQLImplementation connection = SQLImplementation.getInstance();
        RideRequest rideReq;
        driver.setAvailable(true);
        for(int i=0; i< driver.getRideRequests().size();i++){
            if(driver.getRideRequests().get(i).getRide().getisStarted() == true){
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());
                rideReq = driver.getRideRequests().get(i);
                rideReq.getRide().setFinished(true);
                rideReq.getRide().getRideEvents().add(new rideEndEvent(date,driver.getUsername(), rideReq.getRide().getRequester().getUsername()));
                driver.getFinishedRides().add(rideReq.getRide());
                driver.getRideRequests().remove(rideReq);
                connection.updateRideisFinished(rideReq.getRide(), 1);
                return;
            }
        }

    }

    public void arriveAtLocation(){
        RideRequest rideReq;
        for(int i=0; i< driver.getRideRequests().size();i++){
            if(driver.getRideRequests().get(i).getRide().getisStarted() == true){
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());
                rideReq = driver.getRideRequests().get(i);
                rideReq.getRide().getRideEvents().add(new captainArrivalEvent(date,driver.getUsername(), rideReq.getRide().getRequester().getUsername()));
                return;
            }
        }
    }



    void insert(Driver driver)
    {
        SQLImplementation connection = SQLImplementation.getInstance();
        connection.insert(driver);

    }
}
