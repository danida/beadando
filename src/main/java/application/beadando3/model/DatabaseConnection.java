package application.beadando3.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	 private static DatabaseConnection manager = new DatabaseConnection();
	 
	  private static Connection singleConnection;
	
	  
	  private DatabaseConnection() {
	      
	            singleConnection = createConnection();
	            
	        
	      
	    }
	  private Connection createConnection() {
	        Connection Conn = null;
	      
	        try {
	            Conn = DriverManager.getConnection("jdbc:oracle:thin:system/asdQWE123@localhost:1521:XE");
	        }
	        catch (SQLException e) {
	        	e.printStackTrace();
	        }
	        return Conn;
	    }
	    public static Connection getConnection() {
	        return manager.createConnection();
	    }

	    public static Connection singleConnection() {
	        return singleConnection;
	    }
}
