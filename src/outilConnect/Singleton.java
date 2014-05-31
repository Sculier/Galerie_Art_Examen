package outilConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Singleton {
	
	private static Connection connection = null;
	private static Properties props = null;
	

	private Singleton () {
		
		try { 
			connection = DriverManager.getConnection(props.getProperty("url"),
								props.getProperty("admin"),props.getProperty("password") );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	public static synchronized Connection getInstance (Properties p){
		
		props = p;
		if (connection == null){
			new Singleton();
		}
		return connection;
		
	}	
		
	
	public static void shutdown(){
			
		if (connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
	}	
		
	
	
	

}
