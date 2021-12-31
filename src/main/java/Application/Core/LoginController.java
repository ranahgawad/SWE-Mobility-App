package Application.Core;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    LoginController(){

    }
    @PostMapping ("/login")
    boolean login(@RequestBody User user)  {
        return Login.perfromLogin(user);
    }
}
