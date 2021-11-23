import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SQLImplementation extends Database {
    private static SQLImplementation instance;
    private Connection conn;


    private SQLImplementation(){

    }
    public static SQLImplementation getInstance(){
        if(instance == null){
            instance = new SQLImplementation();
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

    public void updateDriverRating(Driver driver, float rating){
        String sqlstatement = "UPDATE Driver SET averageRating="+rating+" WHERE driverID="+ driver.getDriverID();
        try {
            conn = DatabaseFileConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            prestmnt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void insert(Ride ride){
        String sqlstatement = "INSERT INTO Ride(passengerID, rideID, source, destination, started, finished) Values(?,?,?,?,?,?)";
        try{
            conn = DatabaseFileConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            prestmnt.setInt(1, ride.getRequester().getPassengerID());
            prestmnt.setInt(2, ride.getRideID());
            prestmnt.setString(3, ride.getSource());
            prestmnt.setString(4, ride.getDestination());
            prestmnt.setBoolean(5, ride.getisStarted());
            prestmnt.setBoolean(6, ride.getisFinished());
            prestmnt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateRideisStarted(Ride ride, int started){
        String sqlstatement = "UPDATE Ride SET started="+started+" WHERE rideID="+ ride.getRideID();
        try{
            conn = DatabaseFileConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            prestmnt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateRideisFinished(Ride ride, int finished){
        String sqlstatement = "UPDATE Ride SET finished="+finished+" WHERE rideID="+ ride.getRideID();
        try{
            conn = DatabaseFileConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            prestmnt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void execute(DBOperation dbo, User user){


    }
}
