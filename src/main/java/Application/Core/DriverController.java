package Application.Core;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DriverController {

    Driver driver;
    @PostMapping ("driver/login")
    boolean login(@RequestBody User user)  {
        SQLImplementation connection = new SQLImplementation();
        driver=connection.getCurrentDriver(user);
        return Login.perfromLogin(user);
    }

    @GetMapping("/driver/sendOffer/{price}/{requestNumber}")
    public String sendOffer(@PathVariable double price, @PathVariable int requestNumber)
    {
        driver.sendOffer(price, driver.getRequest(requestNumber));
        return "Offer sent";
    }

    @PostMapping("/driver/finishRide")
    public String finishRide() {
        driver.getDriverModel().finishRide();
        return "Ride is finished.";
    }

    @PostMapping("/driver/subscribeToArea/{area}")
    public String subscribeToArea(@PathVariable String area) {
        driver.setFavoriteAreas(area);
        driver.getDriverModel().subscribeToArea();
        return driver + "subscribed to " + driver.getFavoriteAreas();
    }

    @GetMapping("/driver/hello")
    public String hello()
    {
        return "hello";
    }


}
