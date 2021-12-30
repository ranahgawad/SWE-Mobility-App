package Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
<<<<<<< HEAD
import Core.*;

@ComponentScan("Application")
@SpringBootApplication

=======

@SpringBootApplication
@ComponentScan
>>>>>>> 1858c597d8ae44c598c185be486e2d08299b7e50
public class MobilityApplication {
    public static void main(String[] args) {
        SpringApplication.run(MobilityApplication.class, args);
    }

}
