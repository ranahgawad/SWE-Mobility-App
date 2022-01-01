package Application.Core;

import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/admin/showRideEvents")
    public List<Event> showEvents(@RequestBody Ride ride){
       return AdminModel.showRideEvents(ride);
    }


    @PutMapping("/admin/verifyDriver")
    public boolean verifyDriver(@RequestBody Driver driver)
    {
      return AdminModel.verifyDriver(driver);
    }


    @PutMapping("/admin/suspendDriver")
    public boolean suspendDriver(@RequestBody Driver driver)
    {
        return  AdminModel.suspendDriver(driver);
    }

    @PutMapping("/admin/suspendPassenger")
    public boolean suspendPassenger(@RequestBody Passenger pass)
    {
        return  AdminModel.suspendPassenger(pass);
    }
    @PostMapping("/admin/addpublicholiday")
    public boolean addPublicHoliday(@RequestBody publicHolidays publicHolidays){
        return AdminModel.addPublicHoliday(publicHolidays);
    }

    @PostMapping("/admin/adddiscount/{destination}")
    public boolean addDiscount(@PathVariable  String destination){
        return AdminModel.addDiscount(destination);
    }
}
