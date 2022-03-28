package Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
