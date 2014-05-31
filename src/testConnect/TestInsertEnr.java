package testConnect;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import outilConnect.OutilLectParamConnect;
import outilConnect.Singleton;
import pojo.Categorie;

public class TestInsertEnr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Properties props = null;
		String chemin = "fichiers/Config.properties";
		
		try {
			
			props= OutilLectParamConnect.recupererProperties(chemin);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		Connection connection = null;
		connection = Singleton.getInstance(props);

		PreparedStatement pst = null;
		String sql = null;
		
		try {
			
			
			sql = "INSERT INTO categorie VALUES(NULL,?);";			
			pst = connection.prepareStatement(sql);
			
			pst.setString(1, "Trompe l'oeil");
			int val = pst.executeUpdate();
			
			System.out.println("Si 1, requête effectivement réussie sinon requête ratée:" + val);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Singleton.shutdown();
		}	

		
		
	}

}
