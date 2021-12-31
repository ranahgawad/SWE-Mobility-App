package Application.Core;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassengerRegController {
    PassengerRegController(){

    }

    @PostMapping("/passengerRegisteration")
    public void PassengerReg(PassengerRegistration passreg){

    }
}
