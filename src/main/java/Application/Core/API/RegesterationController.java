package Application.Core.API;


import Application.Core.Driver.Driver;
import Application.Core.Driver.DriverRegistration;
import Application.Core.Passenger.Passenger;
import Application.Core.Passenger.PassengerRegistration;
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
    public boolean DriverReg(@RequestBody Driver driver){

        return DriverRegistration.Register(driver);
    }
}
