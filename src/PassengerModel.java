import java.text.SimpleDateFormat;
import java.util.Date;

public class PassengerModel {
   private Passenger passenger;
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
    public void requestRide(String source, String destination)
    {
        RideRequest request = new RideRequest(source, destination, passenger);
        request.sendRequest();
    }

    public void acceptOffer(Offer offer)
    {
        if(this.passenger.getRideOffers() == null || this.passenger.getRideOffers().size() == 0){
            System.out.println("There are no ride offers to be accepted");
        }else{
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            offer.getRequest().getRide().setReceiver(offer.getDriver());
            offer.getRequest().getRide().setStarted(true);
            offer.getRequest().getRide().getRideEvents().add(new rideOfferAccepted(date, offer.getRequest().getRide().getRequester().getUsername()));
            SQLImplementation connection = SQLImplementation.getInstance();
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




}
