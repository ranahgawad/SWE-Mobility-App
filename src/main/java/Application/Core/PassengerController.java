package Application.Core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jdk.nashorn.internal.ir.ObjectNode;
import org.springframework.web.bind.annotation.*;


@RestController
public class PassengerController {
    Passenger pass;

    public PassengerController() {
    }


    @PostMapping("/requestRide")
    public String requestRide(@RequestParam String source,@RequestParam String destination,@RequestParam int num_passengers){
        pass.getPassengerModel().requestRide(source, destination, num_passengers);
        return "request sent";
    }

    @PostMapping ("passenger/login")
    boolean login(@RequestBody User user)  {
         SQLImplementation connection = new SQLImplementation();
         pass=connection.getCurrentPassenger(user);
         return Login.perfromLogin(user);
    }

    @PostMapping ("/passenger/rateDriver/{rating}")
    public String rateDriver(@PathVariable int rating) {
        pass.getPassengerModel().rateDriver(rating);
        return pass + "rated driver " + rating;
    }










}