package Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import Core.*;

@ComponentScan("Application")
@SpringBootApplication

public class MobilityApplication {
    public static void main(String[] args) {
        SpringApplication.run(MobilityApplication.class, args);
    }

}
