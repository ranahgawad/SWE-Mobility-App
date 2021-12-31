package Application.Core;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SQLImplementation implements IPersistence {
    private static SQLImplementation instance;
    private Connection conn;


    public SQLImplementation() {

    }

    public static SQLImplementation getInstance() {
        if (instance == null) {
            instance = new SQLImplementation();
        }
        return instance;
    }

    public boolean userExists(String username, String password) {
        String sqlstatement = "SELECT * FROM Users" + " WHERE username ='" + username + "' AND password ='" + password + "'";
        try {
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            ResultSet result = prestmnt.executeQuery();
            while (result.next()) {
                if (result.getString("username") != null && result.getString("password") != null) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void insertUser(User user) {
        String sqlstatement = "INSERT INTO Users(username, password) Values(?,?)";
        try {
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            prestmnt.setString(1, user.getUsername());
            prestmnt.setString(2, user.getPassword());
            int rowsAffected = prestmnt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "User successfully inserted!");
            } else {
                JOptionPane.showMessageDialog(null, "Coule not insertUser!", "notInserted", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void insert(User user) {
        if (user instanceof Passenger) {
            String sqlstatement = "INSERT INTO Passenger(passengerID, username, email, mobileNumber,password, countRides, birthdayDate) Values(?,?,?,?,?,?,?)";
            try {
                conn = SQLDatabaseConnection.getConnectiontoDataBase();
                PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
                prestmnt.setInt(1, ((Passenger) user).getPassengerID());
                prestmnt.setString(2, user.getUsername());
                prestmnt.setString(3, user.getEmail());
                prestmnt.setString(4, user.getMobileNumber());
                prestmnt.setString(5, user.getPassword());
                prestmnt.setInt(6, ((Passenger) user).getCountRides());
                prestmnt.setString(7, (((Passenger) user).getBirthdayDate()));
                prestmnt.executeUpdate();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (user instanceof Driver) {
            String sqlstatement = "INSERT INTO Driver(driverID, username, password, email,mobileNumber, isSuspended, isVerified, licenceNumber, averageRating, nationalID,balance) Values(?,?,?,?,?,?,?,?,?,?,?)";
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
                prestmnt.setDouble(11, ((Driver) user).getBalance());
                prestmnt.executeUpdate();


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void select(User user) {

    }

    @Override
    public boolean updateUserSuspened(User user) {
        if (user instanceof Passenger) {
            String sqlstatement = "UPDATE Passenger SET isSuspended=1 WHERE passengerID=" + ((Passenger) user).getPassengerID();
            try {
                conn = SQLDatabaseConnection.getConnectiontoDataBase();
                PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
                int rowsAffected = prestmnt.executeUpdate();

                if (rowsAffected > 0) {
                    return true;
                }


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
        } else if (user instanceof Driver) {
            String sqlstatement = "UPDATE Driver SET isSuspended=1 WHERE driverID=" + ((Driver) user).getDriverID();
            try {
                conn = SQLDatabaseConnection.getConnectiontoDataBase();
                PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
                int rowsAffected = prestmnt.executeUpdate();

                if (rowsAffected > 0) {
                    return true;
                }


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean updateDriverVerification(Driver driver, int state) {
        String sqlstatement = "UPDATE Driver SET isVerified=" + state + " WHERE driverID=" + driver.getDriverID();
        try {
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            int rowsAffected = prestmnt.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateDriverRating(Driver driver, float rating) {
        String sqlstatement = "UPDATE Driver SET averageRating=" + rating + " WHERE driverID=" + driver.getDriverID();
        try {
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            int rowsAffected = prestmnt.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    @Override
    public void insert(Ride ride) {
        String sqlstatement = "INSERT INTO Ride(passengerID, rideID, source, destination, started, finished) Values(?,?,?,?,?,?)";
        try {
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
    public void insert(publicHolidays publicHolidays) {
        String sqlstatement = "INSERT INTO publicHolidays(publicHolidayName, publicHolidayDate) Values(?,?)";
        try {
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            prestmnt.setString(1, publicHolidays.getPublicHolidayName());
            prestmnt.setString(2, publicHolidays.getPublicHolidayDate());
            prestmnt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public boolean updateRideisStarted(Ride ride, int started) {
        String sqlstatement = "UPDATE Ride SET started=" + started + " WHERE rideID=" + ride.getRideID();
        try {
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            prestmnt.executeUpdate();
            int rowsAffected = prestmnt.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateRideisFinished(Ride ride, int finished) {
        String sqlstatement = "UPDATE Ride SET finished=" + finished + " WHERE rideID=" + ride.getRideID();
        try {
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            prestmnt.executeUpdate();

            int rowsAffected = prestmnt.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateCountRides(Passenger passenger, int countRides) {
        String sqlstatement = "UPDATE Passenger SET countRides=" + countRides + " WHERE passengerID=" + passenger.getPassengerID();
        try {
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            prestmnt.executeUpdate();
            int rowsAffected = prestmnt.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateDiscount(Ride ride, double dicount) {
        String sqlstatement = "UPDATE Ride SET Discount=" + dicount + " WHERE RideID=" + ride.getRideID();
        try {
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            prestmnt.executeUpdate();
            int rowsAffected = prestmnt.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updatePrice(Ride ride, double price) {
        String sqlstatement = "UPDATE Ride SET Price=" + price + " WHERE RideID=" + ride.getRideID();
        try {
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            prestmnt.executeUpdate();
            int rowsAffected = prestmnt.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void insert(String destination) {
        String sqlstatement = "INSERT INTO discountDestinations(destination) Values(?)";
        try {
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            prestmnt.setString(1, destination);
            prestmnt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean checkDiscountedDestinations(String destination) {
        String sqlstatement = "SELECT * FROM discountDestinations Where destination = destination";
        try {
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            Statement prestmnt = conn.createStatement();
            ResultSet result = prestmnt.executeQuery(sqlstatement);
            if (result.next()) return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void updateDriverBalance(Driver driver) {
        String sqlstatement = "UPDATE Driver SET balance=" + driver.getBalance() + " WHERE driverID=" + driver.getDriverID();
        try {
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            prestmnt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean checkPublicHoliday(String currDate) {
        String sqlstatement = "SELECT * FROM publicHolidays Where publicHolidayDate =" + currDate;
        try {
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            Statement prestmnt = conn.createStatement();
            ResultSet result = prestmnt.executeQuery(sqlstatement);
            if (result != null) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void clearTable(String tableName) {
        String sqlstatement = "DELETE FROM " + tableName + ";";
        try {
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            PreparedStatement prestmnt = conn.prepareStatement(sqlstatement);
            prestmnt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Passenger> getAllPasengers() {
        String sqlstatement = "SELECT * FROM Passenger";
        List<Passenger> passengers = new ArrayList<>();
        try {
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            Statement prestmnt = conn.createStatement();
            ResultSet result = prestmnt.executeQuery(sqlstatement);
//            int id;
//            String username;
//            String email;
//            String mobile;
//            String password;
            while (result.next()) {
//               boolean isSuspended ;
//               if (result.getInt("isSuspended") == 1) {
//                   isSuspended = true;
//                }else{
//                   isSuspended = false;
//               }
                Passenger p = new Passenger(result.getString("username"), result.getString("password"), result.getString("email"), result.getString("mobileNumber"), result.getString("birthDay"));
                passengers.add(p);
                System.out.println("PassengerID:" + result.getInt("passengerID") + ",username: " + result.getString("username") + ",email: " + result.getString("email") + ",mobile number: " + result.getString("mobileNumber") + ",Is Application.Core.Passenger suspended: " + result.getInt("isSuspended"));
                System.out.println("PassengerID:" + result.getInt("passengerID") + ",username: " + result.getString("username") + ",email: " + result.getString("email") + ",mobile number: " + result.getString("mobileNumber") + ",Is Passenger suspended: " + result.getInt("isSuspended") + " ,countRides: " + result.getInt("countRides") + " ,birthdayDate: " + result.getString("birthdayDate"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return passengers;
    }

    @Override
    public List<Driver> getPendingDriverVerifications() {
        String sqlstatement = "SELECT * FROM Driver WHERE isVerified=0";
        List<Driver> drivers = new ArrayList<>();
        try {
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            Statement prestmnt = conn.createStatement();
            ResultSet result = prestmnt.executeQuery(sqlstatement);

            while (result.next()) {
//                boolean isSuspended ;
//                if (result.getInt("isSuspended") == 1) {
//                    isSuspended = true;
//                }else{
//                    isSuspended = false;
//                }
                Driver d = new Driver(result.getString("username"), result.getString("password"), result.getString("email"), result.getString("mobileNumber"), String.valueOf(result.getInt("licenceNumber")), String.valueOf(result.getInt("licenceNumber")));
                drivers.add(d);
                System.out.println("driverID:" + result.getInt("driverID") + ",username: " + result.getString("username") + ",email: " + result.getString("email") + ",mobile number: " + result.getString("mobileNumber") + ",isDriverSuspended: " + result.getInt("isSuspended") + ",isDriverVerified: " + result.getInt("isVerified") + ",licenceNumber: " + result.getInt("licenceNumber") + ",nationalID: " + result.getString("nationalID"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return drivers;
    }

    @Override
    public List<Driver> getAllDrivers() {
        String sqlstatement = "SELECT * FROM Driver";
        List<Driver> drivers = new ArrayList<>();
        try {
            conn = SQLDatabaseConnection.getConnectiontoDataBase();
            Statement prestmnt = conn.createStatement();
            ResultSet result = prestmnt.executeQuery(sqlstatement);

            while (result.next()) {
                String username = result.getString("username");
                String email = result.getString("email");
                String mobile = result.getString("mobileNumber");
                String password = result.getString("password");
                String licenceNumber = String.valueOf(result.getInt("licenceNumber"));
                String nationalID = String.valueOf(result.getInt("licenceNumber"));
                boolean isSuspended;
                if (result.getInt("isSuspended") == 1) {
                    isSuspended = true;
                } else {
                    isSuspended = false;
                }
                Driver d = new Driver(username, password, email, mobile, licenceNumber, nationalID);
                drivers.add(d);
                System.out.println("driverID:" + result.getInt("driverID") + ",username: " + result.getString("username") + ",email: " + result.getString("email") + ",mobile number: " + result.getString("mobileNumber") + ",isDriverSuspended: " + result.getInt("isSuspended") + ",isDriverVerified: " + result.getInt("isVerified") + ",licenceNumber: " + result.getInt("licenceNumber") + ",nationalID: " + result.getString("nationalID") + ",averageRating: " + result.getDouble("averageRating"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return drivers;
    }
}
