package Application.Core;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegesterationController {
    RegesterationController(){

    }

    @PostMapping("/registerAsPassenger")
    public boolean PassengerReg(@RequestBody Passenger pass){
        return PassengerRegistration.Regestier(pass);
    }

    @PostMapping("/registerAsDriver")
    public boolean PassengerReg(@RequestBody Driver driver){
        return DriverRegistration.Register(driver);
    }


}
