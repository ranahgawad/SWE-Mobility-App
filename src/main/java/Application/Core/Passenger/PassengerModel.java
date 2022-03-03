package Application.Core.Passenger;

import Application.Core.Event.rideOfferAccepted;
import Application.Core.Ride.DiscountManager;
import Application.Core.Ride.Offer;
import Application.Core.Ride.RideRequest;
import Application.Core.Storage.SQLImplementation;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.text.SimpleDateFormat;
import java.util.Date;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PassengerModel {
    private  Passenger passenger;
    PassengerModel(Passenger passenger){
        this.passenger = passenger;
    }
    public void setSuspended(boolean suspended) {
        this.passenger.setSuspended(suspended);
    }

    public void requestRide(String source, String destination, int num_passengers)
    {
        RideRequest request = new RideRequest(source, destination, passenger, num_passengers);
        request.sendRequest();
    }

    public void acceptOffer(Offer offer)
    {
        SQLImplementation connection = SQLImplementation.getInstance();
        if(this.passenger.getRideOffers() == null || this.passenger.getRideOffers().size() == 0){
            System.out.println("There are no ride offers to be accepted");
        }else{
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            offer.getRequest().getRide().setReceiver(offer.getDriver());
            offer.getRequest().getRide().setStarted(true);
            // setting driver id
            connection.setRideDriver(offer.getRequest().getRide(), offer.getRequest().getRide().getReceiver());
            offer.getRequest().getRide().setDriverID(offer.getRequest().getRide().getReceiver().getDriverID());
            // --- //
            offer.getRequest().getRide().getRideEvents().add(new rideOfferAccepted(date, offer.getRequest().getRide().getRequester().getUsername()));
            offer.getDriver().setCurrentCapacity(offer.getDriver().getCurrentCapacity() - 1);
            DiscountManager.applyDiscount(offer);
            offer.getDriver().setBalance(offer.getDriver().getBalance()+offer.getOffer());
            connection.updateRideisStarted(offer.getRequest().getRide(), 1);
            for (int i=0; i<this.passenger.getRideOffers().size(); i++){
                if(this.passenger.getRideOffers().get(i) != offer){
                    this.passenger.getRideOffers().remove(i);
                }
            }
        }

    }


    public Offer getOffer(int index)
    {
        if(this.passenger.getRideOffers() == null || this.passenger.getRideOffers().size() == 0){
            System.out.println("Currently there are no ride offers, If you made sure you requested a ride please wait for drivers to send you offers.");
            return null;
        }else{
            return this.passenger.getRideOffers().get(index);
        }
    }

    public void rateDriver(int rating){

        if(this.passenger.getRideOffers().isEmpty()){
            System.out.println("Cannot rate driver more than once");
        }else{
            this.passenger.getRideOffers().get(0).getDriver().addRating(rating);
            this.passenger.getRideOffers().remove(0);
        }
    }


    public void printRideOffers(){
        if(this.passenger.getRideOffers() == null || this.passenger.getRideOffers().size() ==0){
            System.out.println("Currently there are no ride offers, If you made sure you requested a ride please wait for drivers to send you offers.");
        }else{
            System.out.println(this.passenger.getRideOffers());
        }

    }

    public void setCountRides() {
        SQLImplementation connection = SQLImplementation.getInstance();
        for(int i=0; i< passenger.getRideOffers().size();i++){
            if(passenger.getRideOffers().get(i).getRequest().getRide().getisFinished() == true){
                passenger.setCountRides(passenger.getCountRides()+1);
            }
            connection.updateCountRides((passenger.getRideOffers().get(i).getRequest().getRide().getRequester()), passenger.getCountRides());
        }

    }

}

