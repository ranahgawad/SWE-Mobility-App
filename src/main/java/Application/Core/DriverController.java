package Application.Core;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/driver")
public class DriverController {

    Driver driver;
    @PostMapping ("/login")
    boolean login(@RequestBody User user)  {
        SQLImplementation connection = new SQLImplementation();
        driver=connection.getCurrentDriver(user);
        driver.printRequests();
        return Login.perfromLogin(user);
    }

    @GetMapping("/viewRideRequests")
    public ArrayList<RideRequest> viewOffers()
    {
        return driver.getRideRequests();

    }

    @GetMapping("/viewRideRequests/{capacity}")
    public void setCarCapacity(@PathVariable int capacity)
    {
        driver.setCarCapacity(capacity);

    }

    @GetMapping("/sendOffer/{price}/{requestNumber}")
    public String sendOffer(@PathVariable double price, @PathVariable int requestNumber)
    {
        driver.sendOffer(price, driver.getRequest(requestNumber));
        return "Offer sent";
    }

    @PostMapping("/finishRide")
    public String finishRide() {
        driver.getDriverModel().finishRide();
        return "Ride is finished.";
    }

    @PostMapping("/subscribeToArea/{area}")
    public String subscribeToArea(@PathVariable String area) {
        driver.setFavoriteAreas(area);
        driver.getDriverModel().subscribeToArea();
        return driver + "subscribed to " + driver.getFavoriteAreas();
    }

}
