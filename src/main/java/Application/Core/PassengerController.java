package Application.Core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jdk.nashorn.internal.ir.ObjectNode;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
public class PassengerController {
    Passenger pass;

    public PassengerController() {
    }


    @PostMapping("passenger/requestRide")
    public String requestRide(@RequestParam String source,@RequestParam String destination,@RequestParam int num_passengers){
        pass.getPassengerModel().requestRide(source, destination, num_passengers);
        return "request sent";
    }
    @PostMapping ("passenger/login")
    boolean login(@RequestBody User user)  {
        SQLImplementation connection = new SQLImplementation();
        pass=connection.getCurrentPassenger(user);
        pass.getPassengerModel().printRideOffers();
        return Login.perfromLogin(user);
    }

    @PostMapping ("/passenger/rateDriver/{rating}")
    public String rateDriver(@PathVariable int rating) {
        pass.getPassengerModel().rateDriver(rating);
        return pass + "rated driver " + rating;
    }
    @PostMapping ("passenger/acceptOffer/{offerNumber}")
    public String acceptOffer(@PathVariable int  offerNumber)  {
        pass.getPassengerModel().acceptOffer(pass.getPassengerModel().getOffer(offerNumber));
        return pass+"accepted offer from"+pass.getPassengerModel().getOffer(offerNumber).getDriver();
    }
    @PostMapping ("passenger/getOffer/{offerNumber}")
    public String getOffer(@PathVariable int offerNumber){
        return "offer:" + pass.getPassengerModel().getOffer(offerNumber);
    }
    @PostMapping ("passenger/getAllOffers")
    public ArrayList<Offer> getAllOffers(){
        return pass.getRideOffers();
    }

}