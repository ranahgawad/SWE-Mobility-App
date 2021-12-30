package Core;

import java.sql.SQLException;
import java.util.ArrayList;

public class Login {
    ArrayList<User> users = Registration.getUserList();
    Login(String username, String password) throws SQLException {
        Boolean nameFound = false;
        for(int i=0; i< users.size(); i++){
            if(users.get(i).getUsername() == username && users.get(i).getPassword() == password){
                if(users.get(i).getisSuspended() == true){
                    System.out.println("You have been suspended and cannot log to the system");
                    return;
                }
                if(users.get(i) instanceof Driver && ((Driver) users.get(i)).getisVerified() == false){
                    System.out.println("Your account is not verified yet");
                    return;
                }
                System.out.println("Hello " + username);
                users.get(i).setLoggedIn(true);
                return;
            }
            if (users.get(i).getUsername()==username&&users.get(i).getPassword() != password){
                nameFound = true;
            }
        }
        if(nameFound == true){
            System.out.println("Incorrect username or password");
        }
        else{
            System.out.println("Please register first");
        }
    }
}
