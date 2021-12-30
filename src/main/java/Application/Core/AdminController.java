package Application.Core;

import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD:src/main/java/AdminController.java
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
=======
import org.springframework.web.bind.annotation.RequestMapping;
>>>>>>> 1858c597d8ae44c598c185be486e2d08299b7e50:src/main/java/Application/Core/AdminController.java
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    AdminController(){

    }

<<<<<<< HEAD:src/main/java/AdminController.java
    @GetMapping("/admin/getAllPassengers")
    public List<Passenger> getAllPassengers(){
        return AdminModel.getAllPassengers();
=======
   /* @GetMapping("/admin/getAllPassengers")
    public  Object[] getAllPassengers(){
        //return getAllPassengers();
>>>>>>> 1858c597d8ae44c598c185be486e2d08299b7e50:src/main/java/Application/Core/AdminController.java
    }

    @GetMapping("/admin/getAllDrivers")
    public List<Driver> getAllDrivers(){
        //return getAllDrivers();
    }
*/
    @GetMapping("/admin/getPendingVerifications")
    public List<Driver> getPendingDriverVerifications(){

        return AdminModel.getPendingDriverVerifications();
    }
    @GetMapping("/hello")
    public String Hello(){

        return "hello";
    }

//    @GetMapping("/admin/showRideEvents")
//    public ArrayList<Event> showEvents(Ride ride){
//       return showRideEvents(ride);
//    }
    @GetMapping("/admin/test")
    public boolean test(){
        return true;
    }

<<<<<<< HEAD:src/main/java/AdminController.java
    @PostMapping("/admin/verifyDriver")
    public boolean verifyDriver(@RequestBody Core.Driver driver)
    {
      return true;
    }
=======
//    @GetMapping("/admin/verifyDriver")
//    public void verifyDriver(Application.Core.Driver driver)
//    {
//      adminModel.verifyDriver(driver);
//    }
>>>>>>> 1858c597d8ae44c598c185be486e2d08299b7e50:src/main/java/Application/Core/AdminController.java
//
//    @GetMapping("/admin/suspendUser")
//    public void suspend(Application.Core.User user)
//    {
//        adminModel.suspend(user);
//    }
}
