package Application.Core;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    AdminController(){

    }


    @GetMapping("/admin/getAllPassengers")
    public List<Passenger> getAllPassengers() {
        return AdminModel.getAllPassengers();
    }

    @GetMapping("/admin/getAllDrivers")
    public List<Driver> getAllDrivers(){
        return AdminModel.getAllDrivers();
    }

    @GetMapping("/admin/getPendingVerifications")
    public List<Driver> getPendingDriverVerifications(){

        return AdminModel.getPendingDriverVerifications();
    }

    @GetMapping("/hello")
    public String Hello(){
        return "hello";
    }

    @GetMapping("/admin/showRideEvents")
    public List<Event> showEvents(@RequestBody Ride ride){
       return AdminModel.showRideEvents(ride);
    }


    @GetMapping("/admin/test")
    public boolean test(){
        return true;
    }


    @PostMapping("/admin/verifyDriver")
    public boolean verifyDriver(@RequestBody Driver driver)
    {
      return AdminModel.verifyDriver(driver);
    }



//
//    @GetMapping("/admin/suspendUser")
//    public void suspend(Application.Core.User user)
//    {
//        adminModel.suspend(user);
//    }
}
