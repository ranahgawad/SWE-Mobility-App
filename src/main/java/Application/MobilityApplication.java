package Application;
import Application.Core.IPersistence;
import Application.Core.PassengerRegistration;
import Application.Core.Registration;
import Application.Core.SQLImplementation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import Application.Core.*;



@SpringBootApplication
//@ComponentScan

public class MobilityApplication {
    public static void main(String[] args) {
        IPersistence db = new SQLImplementation();
        db.clearTable("Ride");
//        db.clearTable("Passenger");
//        db.clearTable("Driver");
        //Registration pass = new PassengerRegistration("doha123", "1234", "doha@gmail.com", "011", "12-11-2001");
//        for(int i=0; i<Registration.getUserList().size();i++){
//            System.out.println(Registration.getUserList().get(i).getUsername() + " : " + Registration.getUserList().get(i).getPassword() );
//        }
        SpringApplication.run(MobilityApplication.class, args);
    }

}
