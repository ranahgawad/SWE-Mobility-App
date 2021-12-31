package Application.Core;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Login {

    public static ArrayList<User> users = Registration.getUserList();

     Login(){
     }
    static boolean perfromLogin(User user) {
        SQLImplementation connection = SQLImplementation.getInstance();
        return connection.userExists(user.getUsername(), user.getPassword());
    }

    public ArrayList<User> getUsers() {
        return users;
    }

}
