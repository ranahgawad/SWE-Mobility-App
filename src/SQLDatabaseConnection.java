import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDatabaseConnection {

    private static SQLDatabaseConnection connectionInstance;
    private static Connection connectiontoDataBase;

    private SQLDatabaseConnection(){

    }

    private static Connection connect(){
        String url = "jdbc:sqlite:databasetest.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to the database has been setup");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static Connection getConnectiontoDataBase(){
        if(connectiontoDataBase == null){
            connectiontoDataBase = connectionInstance.connect();
        }
        return connectiontoDataBase;
    }



}
