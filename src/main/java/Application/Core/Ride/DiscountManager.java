package Application.Core.Ride;
import Application.Core.Storage.SQLImplementation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DiscountManager {
    static final double TEN_PERCENT_DISCOUNT = 0.1;
    static final double FIVE_PERCENT_DISCOUNT = 0.05;


    public static boolean isBirthDay(String birthdayDate){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM");
        LocalDateTime now = LocalDateTime.now();
        String currDate=dtf.format(now);
        birthdayDate=birthdayDate.substring(0,birthdayDate.lastIndexOf("-"));
        if(currDate.equals(birthdayDate)){return true;}
        return false;
    }
    public static boolean isPublicHoliday(){
        SQLImplementation connection = SQLImplementation.getInstance();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM");
        LocalDateTime now = LocalDateTime.now();
        String currDate=dtf.format(now);
        return connection.checkPublicHoliday(currDate);
    }

    public static boolean containsTwoPassengers(Offer offer){
        return (offer.getRequest().getNumPassengers() + 1) == 2;
    }

    public static boolean addDiscount(String destination){
        SQLImplementation connection = SQLImplementation.getInstance();
        return connection.insert(destination);
    }
    public static boolean checkDiscountedDestinations(String destination){
        SQLImplementation connection = SQLImplementation.getInstance();
        return connection.checkDiscountedDestinations(destination);
    }


    public static double calculateDiscount(Offer offer){
        double discount=1;
        SQLImplementation connection = SQLImplementation.getInstance();
        if(offer.getRequest().getRide().getRequester().getCountRides()==0){
            discount-= TEN_PERCENT_DISCOUNT;
        }
        if(isBirthDay(offer.getRequest().getRide().getRequester().getBirthdayDate())){
            discount-= TEN_PERCENT_DISCOUNT;
        }
        if(checkDiscountedDestinations(offer.getRequest().getRide().getDestination())){
            discount -= TEN_PERCENT_DISCOUNT;
        }
        if(isPublicHoliday()){
            discount -= FIVE_PERCENT_DISCOUNT;
        }
        if(containsTwoPassengers(offer)) {
            discount -= FIVE_PERCENT_DISCOUNT;
        }
        connection.updateDiscount(offer.getRequest().getRide(),((1-discount)*100));
        return discount;
    }

    public static double applyDiscount(Offer offer){
        SQLImplementation connection = SQLImplementation.getInstance();
            connection.updatePrice(offer.getRequest().getRide(),offer.offer*calculateDiscount(offer));
            return offer.offer*calculateDiscount(offer);
    }

}
