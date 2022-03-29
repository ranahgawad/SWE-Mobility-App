package Application.Core.API;

import java.util.ArrayList;

import Application.Core.Driver.Driver;
import Application.Core.Storage.IPersistence;
import Application.Core.User.Login;
import Application.Core.Passenger.Passenger;
import Application.Core.Ride.Offer;
import Application.Core.Storage.SQLImplementation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/passenger")

public class PassengerController {
    Passenger pass;

    public PassengerController() {
    }

    @PostMapping("/requestRide")
    public String requestRide(@RequestParam String source,@RequestParam String destination,@RequestParam int num_passengers){
        pass.getPassengerModel().requestRide(source, destination, num_passengers);
        return "request sent";
    }
    @PostMapping ("/login")
    boolean login(@RequestBody Passenger user)  {
        IPersistence connection = new SQLImplementation();
        pass=connection.getCurrentPassenger(user);
        pass.getPassengerModel().printRideOffers();
        return Login.perfromLogin(pass);
    }

    @PostMapping ("/rateDriver/{rating}")
    public String rateDriver(@PathVariable int rating) {
        pass.getPassengerModel().rateDriver(rating);

        return pass + "rated driver " + rating;
    }
    @PostMapping({"/acceptOffer/{offerNumber}"})
    public String acceptOffer(@PathVariable int offerNumber) {
        Driver driver = this.pass.getPassengerModel().getOffer(offerNumber).getDriver();
        this.pass.getPassengerModel().acceptOffer(this.pass.getPassengerModel().getOffer(offerNumber));
        return this.pass + "accepted offer from" + driver;
    }
    @GetMapping ("/getOffer/{offerNumber}")
    public String getOffer(@PathVariable int offerNumber){
        return "offer:" + pass.getPassengerModel().getOffer(offerNumber);
    }
    @GetMapping ("/getAllOffers")
    public ArrayList<Offer> getAllOffers(){
        return pass.getRideOffers();
    }

}