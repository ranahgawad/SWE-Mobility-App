package Application.Core;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/driver")
public class DriverController {

    Driver driver;
    @PostMapping ("/login")
    boolean login(@RequestBody Driver user)  {
        SQLImplementation connection = new SQLImplementation();
        driver=connection.getCurrentDriver(user);
        return Login.perfromLogin(driver);
    }

    @GetMapping("/viewRideRequests")
    public ArrayList<RideRequest> viewOffers()
    {
        return driver.getRideRequests();

    }

    @GetMapping("/setCarCapacity/{capacity}")
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

    @PutMapping("/finishRide")
    public String finishRide() {
        driver.getDriverModel().finishRide();
        return "Ride is finished.";
    }

    @PutMapping("/arriveAtLocation")
    public String arriveAtLocation() {
        driver.getDriverModel().arriveAtLocation();
        return "Driver arrived at location.";
    }

    @PostMapping("/subscribeToArea/{area}")
    public String subscribeToArea(@PathVariable String area) {
        driver.setFavoriteAreas(area);
        driver.getDriverModel().subscribeToArea();
        return driver + "subscribed to " + driver.getFavoriteAreas();
    }

}
