package Application;
import Application.Core.IPersistence;
import Application.Core.PassengerRegistration;
import Application.Core.SQLImplementation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import Application.Core.*;
@SpringBootApplication

public class MobilityApplication {
    public static void main(String[] args) {
//        IPersistence db = new SQLImplementation();
//        db.clearTable("Ride");
//        db.clearTable("Passenger");
//        db.clearTable("Driver");
        SpringApplication.run(MobilityApplication.class, args);
    }

}
