package Application;
import Application.Core.IPersistence;
import Application.Core.SQLImplementation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//import Core.*;



@SpringBootApplication
//@ComponentScan

public class MobilityApplication {
    public static void main(String[] args) {
        SpringApplication.run(MobilityApplication.class, args);
    }

}
