package lk.ijse.groceryshop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbcConnection;
    private final Connection connection;

    public DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/lms_gdse","root","KPsuneetha@123");
        }catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException("Failed to load the database");
        }
    }
    public static DBConnection getDbcConnection(){
        return dbcConnection==null?dbcConnection= new DBConnection():dbcConnection;
    }
    public Connection getConnection(){
        return connection;
    }
}
