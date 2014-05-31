package testConnect;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class TesterConnect {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		
		String DRIVER = "com.mysql.jdbc.Driver";
		String URL = "jdbc:mysql://localhost:3306/galerieart09";
		String ADMIN = "root";
		String PASSWORD = "";
		
		Connection connection = null;
	
		try {
			connection = DriverManager.getConnection(URL,ADMIN,PASSWORD);
			JOptionPane.showMessageDialog(null,"Paramètres corrects");
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Paramètres incorrects");
		
		}finally{
			
			if(connection != null){ 
				try {
					connection.close(); 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Fermeture de la connection incorrecte");
				}
			}	
		}
	
	}

}
