package testComplementaires;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import outilConnect.OutilLectParamConnect;
import outilConnect.Singleton;
import outilDAO.MyException;
import pojo.Horaire;
import pojo.Localexpo;
import pojoDAO.HoraireDao;




public class TestReception {

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
		ResultSet rs = null;
		
		try {
					
			sql = "Select client.idClient, reception.idClient, "
				+ "reception.idNewsletter,"
				+ " newsletter.idNewsletter "
				+ "FROM client, reception, newsletter WHERE "
				+ "client.idClient = reception.idClient AND "
				+ "reception.idNewsletter = newsletter.idNewsletter"
				+ " AND newsletter.idNewsletter = ?";
			
			pst = connection.prepareStatement(sql);
			pst.setInt(1, 2);
			rs = pst.executeQuery();
			
			while (rs.next()){
					System.out.println(rs.getInt(1)+ " " + rs.getInt(2)+ " " 
											+ rs.getInt(3) + " " + rs.getInt(4));
			}
			System.out.println("Requête réussie");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
		

			
		// Fermer la connection
		Singleton.shutdown();
			
	}
		
}
