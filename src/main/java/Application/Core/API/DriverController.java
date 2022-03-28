package Application.Core.API;

import Application.Core.Driver.Driver;
import Application.Core.Storage.IPersistence;
import Application.Core.User.Login;
import Application.Core.Ride.RideRequest;
import Application.Core.Storage.SQLImplementation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/driver")
public class DriverController {

    Driver driver;
    @PostMapping ("/login")
    boolean login(@RequestBody Driver user)  {
        IPersistence connection = new SQLImplementation();
        driver=connection.getCurrentDriver(user);
        return Login.perfromLogin(driver);
    }

    @GetMapping("/viewRideRequests")
    public ArrayList<RideRequest> viewOffers()
    {
        return driver.getRideRequests();

    }

    @PostMapping("/setCarCapacity/{capacity}")
    public void setCarCapacity(@PathVariable int capacity)
    {
        driver.setCarCapacity(capacity);

    }

    @PostMapping("/sendOffer/{price}/{requestNumber}")
    public String sendOffer(@PathVariable double price, @PathVariable int requestNumber)
    {
        driver.getDriverModel().sendOffer(price, driver.getRequest(requestNumber));
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

    @GetMapping("/getMyInfo")
    public Driver getMyInfo() {
        return driver;
    }

}
