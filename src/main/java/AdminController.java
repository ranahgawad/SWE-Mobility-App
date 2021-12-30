import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import Core.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {

    AdminController(){

    }

    @GetMapping("/admin/getAllPassengers")
    public List<Passenger> getAllPassengers(){
        return AdminModel.getAllPassengers();
    }

    @GetMapping("/admin/getAllDrivers")
    public List<Driver> getAllDrivers(){
        return getAllDrivers();
    }

    @GetMapping("/admin/getPendingVerifications")
    public List<Driver> getPendingDriverVerifications(){
        return getPendingDriverVerifications();
    }

//    @GetMapping("/admin/showRideEvents")
//    public ArrayList<Event> showEvents(Ride ride){
//       return showRideEvents(ride);
//    }
    @GetMapping("/admin/test")
    public boolean test(){
        return true;
    }

    @PostMapping("/admin/verifyDriver")
    public boolean verifyDriver(@RequestBody Core.Driver driver)
    {
      return true;
    }
//
//    @GetMapping("/admin/suspendUser")
//    public void suspend(Core.User user)
//    {
//        adminModel.suspend(user);
//    }
}
