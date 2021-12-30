package Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import Core.*;
@SpringBootApplication
//@ComponentScan
public class MobilityApplication {
    public static void main(String[] args) {
        Admin admin = new Admin();
        SpringApplication.run(MobilityApplication.class, args);
    }

}
