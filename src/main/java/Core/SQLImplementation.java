package Core;

import java.sql.*;
import java.util.ArrayList;


public class SQLImplementation implements IPersistence {
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

    @Override
    public void insert(User user) {
        if(user instanceof Passenger) {
            String sqlstatement = "INSERT INTO Passenger(passengerID, username, email, mobileNumber,password) Values(?,?,?,?,?)";
            try {
                conn = SQLDatabaseConnection.getConnectiontoDataBase();
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
                conn = SQLDatabaseConnection.getConnectiontoDataBase();
                PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
                prestmnt.setInt(1, ((Driver) user).getDriverID());
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

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    @Override
    public void delete(User user){

    }
    @Override
    public void select(User user){

    }
    @Override
    public void updateUserSuspened(User user){
        if(user instanceof Passenger){
            String sqlstatement = "UPDATE Passenger SET isSuspended=1 WHERE passengerID="+ ((Passenger) user).getPassengerID();
            try {
                conn = SQLDatabaseConnection.getConnectiontoDataBase();
                PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
                prestmnt.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else if(user instanceof Driver){
            String sqlstatement = "UPDATE Driver SET isSuspended=1 WHERE driverID="+ ((Driver) user).getDriverID();
            try {
                conn = SQLDatabaseConnection.getConnectiontoDataBase();
                PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
                prestmnt.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    @Override
    public void updateDriverVerification(Driver driver, int state){
        String sqlstatement = "UPDATE Driver SET isVerified="+state+" WHERE driverID="+ driver.getDriverID();
        try {
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            prestmnt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateDriverRating(Driver driver, float rating){
        String sqlstatement = "UPDATE Driver SET averageRating="+rating+" WHERE driverID="+ driver.getDriverID();
        try {
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            prestmnt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void insert(Ride ride){
        String sqlstatement = "INSERT INTO Ride(passengerID, rideID, source, destination, started, finished) Values(?,?,?,?,?,?)";
        try{
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
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

    @Override
    public void updateRideisStarted(Ride ride, int started){
        String sqlstatement = "UPDATE Ride SET started="+started+" WHERE rideID="+ ride.getRideID();
        try{
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            prestmnt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateRideisFinished(Ride ride, int finished){
        String sqlstatement = "UPDATE Ride SET finished="+finished+" WHERE rideID="+ ride.getRideID();
        try{
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            prestmnt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Passenger> getAllPasengers() {
        String sqlstatement = "SELECT * FROM Passenger";
        ArrayList<Passenger> passengers = new ArrayList<>();
        try{
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            Statement prestmnt = conn.createStatement();
            ResultSet result = prestmnt.executeQuery(sqlstatement);

            while(result.next()){
                int id = result.getInt("passengerID");
                String username = result.getString("username");
                String email = result.getString("email");
                String mobile = result.getString("mobileNumber");
                String password = result.getString("password");
               boolean isSuspended ;
               if (result.getInt("isSuspended") == 1) {
                   isSuspended = true;
                }else{
                   isSuspended = false;
               }
               Passenger p = new Passenger(username, password, email, mobile);
               passengers.add(p);
//               System.out.println("PassengerID:"+result.getInt("passengerID")+ ",username: "+ result.getString("username")+",email: "+ result.getString("email")+ ",mobile number: " + result.getString("mobileNumber")+ ",Is Core.Passenger suspended: " + result.getInt("isSuspended"));
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return passengers;
    }

    @Override
    public  ArrayList<Driver> getPendingDriverVerifications() {
        String sqlstatement = "SELECT * FROM Driver WHERE isVerified=0";
        ArrayList<Driver> drivers = new ArrayList<>();
        try{
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            Statement prestmnt = conn.createStatement();
            ResultSet result = prestmnt.executeQuery(sqlstatement);

            while(result.next()){
                String username = result.getString("username");
                String email = result.getString("email");
                String mobile = result.getString("mobileNumber");
                String password = result.getString("password");
                String licenceNumber = String.valueOf(result.getInt("licenceNumber"));
                String nationalID = String.valueOf(result.getInt("licenceNumber"));
                boolean isSuspended ;
                if (result.getInt("isSuspended") == 1) {
                    isSuspended = true;
                }else{
                    isSuspended = false;
                }
                Driver d = new Driver(username, password, email, mobile,licenceNumber, nationalID);
                drivers.add(d);
//                System.out.println("driverID:"+result.getInt("driverID")+ ",username: "+ result.getString("username")+",email: "+ result.getString("email")+ ",mobile number: " + result.getString("mobileNumber")+ ",isDriverSuspended: " + result.getInt("isSuspended")+ ",isDriverVerified: " + result.getInt("isVerified")+",licenceNumber: " + result.getInt("licenceNumber")+",nationalID: " + result.getString("nationalID"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return drivers;
    }

    @Override
    public ArrayList<Driver> getAllDrivers() {
        String sqlstatement = "SELECT * FROM Driver";
        ArrayList<Driver> drivers = new ArrayList<>();
        try{
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            Statement prestmnt = conn.createStatement();
            ResultSet result = prestmnt.executeQuery(sqlstatement);

            while(result.next()){
                String username = result.getString("username");
                String email = result.getString("email");
                String mobile = result.getString("mobileNumber");
                String password = result.getString("password");
                String licenceNumber = String.valueOf(result.getInt("licenceNumber"));
                String nationalID = String.valueOf(result.getInt("licenceNumber"));
                boolean isSuspended ;
                if (result.getInt("isSuspended") == 1) {
                    isSuspended = true;
                }else{
                    isSuspended = false;
                }
                Driver d = new Driver(username, password, email, mobile,licenceNumber, nationalID);
                drivers.add(d);
//                System.out.println("driverID:"+result.getInt("driverID")+ ",username: "+ result.getString("username")+",email: "+ result.getString("email")+ ",mobile number: " + result.getString("mobileNumber")+ ",isDriverSuspended: " + result.getInt("isSuspended")+ ",isDriverVerified: " + result.getInt("isVerified")+",licenceNumber: " + result.getInt("licenceNumber")+",nationalID: " + result.getString("nationalID")+ ",averageRating: " + result.getDouble("averageRating"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return drivers;
    }
}
