package testComplementaires;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import outilConnect.OutilLectParamConnect;
import outilConnect.Singleton;
import outilDAO.MyException;
import pojo.Adresse;
import pojo.Client;
import pojoDAO.AdresseDao;
import pojoDAO.ClientDao;



public class FormatDateSelect {

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
		

/*		
				// VERSION FINALE INSERT
		
		// Insertion d'un objet Client
		System.out.println("Insertion un objet");
		
		Adresse adresse1 = new Adresse();
		adresse1.setIdAdresse(1);
			
		Client cli = new Client();
		cli.setIdClient(new Integer(0));
		
		
	//***************************************************************************************		
		// Transformer format "SimpleDateFormat" en "Date" dans le but d'effectuer un insert
		Date date = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date1 = "22/06/2006";
		
		try {
			date = simpleDateFormat.parse(date1);
			//System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	//***************************************************************************************
		
		cli.setDateClient(date);
		cli.setAccordNewsletter((byte)1);
		cli.setEmail("Email");
		cli.setNom("Nom");
		cli.setPrenom("Prenom");
		cli.setAdresse(adresse1);		
				
		ClientDao cliDao1 = new ClientDao(connection);
			
		try {
			cliDao1.insert(cli);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}
*/		

		
/*		
	
									// VERSION FINALE POUR SELECT
		// Sélectionner plusieurs client à partir d'un String --> Date --> Select
		
		Date date = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date1 = "22/06/2006";
		
		try {
			date = simpleDateFormat.parse(date1);
			//System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		String sql = "SELECT * FROM client WHERE dateClient = ?;";
		
		ResultSet rs = null;
		Client client = null;
		List <Client> listeClients = null;
		AdresseDao adresseDao = null;
		
	    java.sql.Date datecl = new java.sql.Date(date.getTime());
		
	    
	    try (PreparedStatement pst = connection.prepareStatement(sql)){
	    		
				pst.setDate(1, datecl);
	    		rs = pst.executeQuery();	
	    		listeClients = new ArrayList<Client>();
				
				while (rs.next()){
					
					adresseDao = new AdresseDao(connection);
					client = new Client();
					
					client.setIdClient(rs.getInt(1));
					client.setAccordNewsletter(rs.getByte(2));
					client.setDateClient((Date)rs.getDate(3));
					client.setEmail(rs.getString(4));
					client.setNom(rs.getString(5));
					client.setPrenom(rs.getString(6));
					client.setAdresse(adresseDao.find(rs.getInt(7)));
					listeClients.add(client);
				}
				
				
			} catch (SQLException | MyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				try {
					throw new MyException("Impossible de récupérer la liste des clients");
				} catch (MyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}		
			
			StringBuffer chaine2 = new StringBuffer();
			for (Client c:listeClients){
				chaine2.append(c.toString()+"\n");
			}
			System.out.println(chaine2);
*/

			
		// Fermer la connection
		Singleton.shutdown();
			
	}
		
}
