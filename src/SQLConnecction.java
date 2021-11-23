import org.sqlite.SQLiteException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SQLConnecction extends Database {
    private static SQLConnecction instance;
    private Connection conn;


    private SQLConnecction(){

    }
    public static SQLConnecction getInstance(){
        if(instance == null){
            instance = new SQLConnecction();
        }
        return instance;
    }




    public void insert(User user) {
        if(user instanceof Passenger) {
            String sqlstatement = "INSERT INTO Passenger(passengerID, username, email, mobileNumber,password) Values(?,?,?,?,?)";
            try {
                conn = DatabaseFileConnection.getConnectiontoDataBase();
                PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
                prestmnt.setInt(1, ((Passenger) user).getPassengerID());
                prestmnt.setString(2, user.getUsername());
                prestmnt.setString(3, user.getEmail());
                prestmnt.setString(4, user.getMobileNumber());
                prestmnt.setString(5, user.getPassword());
                prestmnt.executeUpdate();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }else if(user instanceof Driver){
            String sqlstatement = "INSERT INTO Driver(driverID, username, password, email,mobileNumber, isSuspended, isVerified, licenceNumber, averageRating, nationalID) Values(?,?,?,?,?,?,?,?,?,?)";
            try {
                conn = DatabaseFileConnection.getConnectiontoDataBase();
                PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
                prestmnt.setInt(1, ((Driver) user).getDriverID());
//                prestmnt.setInt(1, 5);
                prestmnt.setString(2, user.getUsername());
                prestmnt.setString(3, user.getPassword());
                prestmnt.setString(4, user.getEmail());
                prestmnt.setString(5, user.getMobileNumber());
                prestmnt.setBoolean(6, ((Driver) user).getisSuspended());
                prestmnt.setBoolean(7, ((Driver) user).getisVerified());
                prestmnt.setString(8, ((Driver) user).getLicenseNumber());
                prestmnt.setFloat(9, ((Driver) user).getAverageRating());
                prestmnt.setString(10, ((Driver) user).getNationalID());
                prestmnt.executeUpdate();
//                if(conn != null){
//                    conn.close();
//                }


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void delete(User user){

    }
    public void select(User user){

    }
    public void updateUserSuspened(User user){
        if(user instanceof Passenger){
            String sqlstatement = "UPDATE Passenger SET isSuspended=1 WHERE passengerID="+ ((Passenger) user).getPassengerID();
            try {
                conn = DatabaseFileConnection.getConnectiontoDataBase();
                PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
                prestmnt.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else if(user instanceof Driver){
            String sqlstatement = "UPDATE Driver SET isSuspended=1 WHERE driverID="+ ((Driver) user).getDriverID();
            try {
                conn = DatabaseFileConnection.getConnectiontoDataBase();
                PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
                prestmnt.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void updateDriverVerification(Driver driver, int state){
        String sqlstatement = "UPDATE Driver SET isVerified="+state+" WHERE driverID="+ driver.getDriverID();
        try {
            conn = DatabaseFileConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            prestmnt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert(RideRequest ridereqest){
        String sqlstatement = "INSERT INTO RideRequest(passengerID, rideRequestID, source, destination) Values(?,?,?,?)";
        try{
            conn = DatabaseFileConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            prestmnt.setInt(1, ridereqest.getRequester().getPassengerID());
            prestmnt.setInt(2, ridereqest.getRideRequestID());
            prestmnt.setString(3, ridereqest.getSource());
            prestmnt.setString(4, ridereqest.getDestination());
            prestmnt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void execute(DBOperation dbo, User user){


    }
}
