package testDAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import outilConnect.OutilLectParamConnect;
import outilConnect.Singleton;
import outilDAO.MyException;
import pojo.Client;
import pojo.Adresse;
import pojoDAO.ClientDao;



public class TestClientDao {

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

		
		// Insertion d'un objet Client
		System.out.println("Insertion un objet");
		
		Adresse adresse1 = new Adresse();
		adresse1.setIdAdresse(1);
			
		Client cli = new Client();
		cli.setIdClient(new Integer(0));
		cli.setDateClient(new Date());
		cli.setEmail("Email");
		cli.setNom("Nom");
		cli.setPrenom("Prenom");
		cli.setAdresse(adresse1);	
		cli.setAccordNewsletter((byte) 1);
		
		
		
		ClientDao cliDao1 = new ClientDao(connection);
			
		try {
			cliDao1.insert(cli);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}

		
		
		
		
		
		// Insertion d'un tableau d'objets Client
		System.out.println("Insertion tableau d'objets");
		
		Client cli1 = new Client();
		cli1.setIdClient(new Integer(0));
		cli1.setDateClient(new Date());
		cli1.setEmail("Email 1");
		cli1.setNom("Nom 1");
		cli1.setPrenom("Prenom 1");
		cli1.setAdresse(adresse1);		
		cli1.setAccordNewsletter((byte)1);
		
		Client cli2 = new Client();
		cli2.setIdClient(new Integer(0));
		cli2.setDateClient(new Date());
		cli2.setEmail("Email 2");
		cli2.setNom("Nom 2");
		cli2.setPrenom("Prenom 2");
		cli2.setAdresse(adresse1);		
		cli2.setAccordNewsletter((byte)1);
		
		Client cli3 = new Client();
		cli3.setIdClient(new Integer(0));
		cli3.setDateClient(new Date());
		cli3.setEmail("Email 3");
		cli3.setNom("Nom 3");
		cli3.setPrenom("Prenom 3");
		cli3.setAdresse(adresse1);		
		cli3.setAccordNewsletter((byte)1);
		
		Client [] tabCli = new Client [3];
		
		tabCli[0]=cli1;
		tabCli[1]=cli2;
		tabCli[2]=cli3;
		
		
		
		ClientDao cliDao2 = new ClientDao(connection);
				
		try {
			cliDao2.insertTabObj(tabCli);
			System.out.println("Insertion réussie");	
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion du tableau d'objets Client");
		}
				
		
			
			
			
			
		// Insertion d'une Collection d'objets Client
		System.out.println("Insertion collection d'objets");
		
		
		Client cli4 = new Client();
		cli4.setIdClient(new Integer(0));
		cli4.setDateClient(new Date());
		cli4.setEmail("Email 4");
		cli4.setNom("Nom 4");
		cli4.setPrenom("Prenom 4");
		cli4.setAdresse(adresse1);		
		cli4.setAccordNewsletter((byte)1);
		
		Client cli5 = new Client();
		cli5.setIdClient(new Integer(0));
		cli5.setDateClient(new Date());
		cli5.setEmail("Email 5");
		cli5.setNom("Nom 5");
		cli5.setPrenom("Prenom 5");
		cli5.setAdresse(adresse1);		
		cli5.setAccordNewsletter((byte)1);	
		
		Client cli6 = new Client();
		cli6.setIdClient(new Integer(0));
		cli6.setDateClient(new Date());
		cli6.setEmail("Email 6");
		cli6.setNom("Nom 6");
		cli6.setPrenom("Prenom 6");
		cli6.setAdresse(adresse1);		
		cli6.setAccordNewsletter((byte)1);
		
	
		List<Client> maliste = new ArrayList<Client>(); 

		maliste.add(cli4); 
		maliste.add(cli5);
		maliste.add(cli6);
			
			
		ClientDao cliDao3 = new ClientDao(connection);
			
		try {
			cliDao3.insertCollObj(maliste);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion d'une Collection d'objets Client");
		}
				
			
		

		
		// Mise à jour d'un objet Client
		System.out.println("Mise à jour d'un objet");
		
		Client cli7 = new Client();
		cli7.setIdClient(new Integer(2));
		cli7.setDateClient(new Date());
		cli7.setEmail("Email update");
		cli7.setNom("Nom update");
		cli7.setPrenom("Prenom update");
		cli7.setAdresse(adresse1);		
		cli7.setAccordNewsletter((byte)1);
			
		ClientDao cliDao4 = new ClientDao(connection);
			
		try {
			cliDao4.update(cli7);
			System.out.println("Mise à jour réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la mise à jour");
		}

			
		
		
		
		
		
		// Suppression d'un objet Client
		System.out.println("Supression d'un objet");
		
		Client cli8 = new Client();
		cli8.setIdClient(5);
					
		ClientDao cliDao5 = new ClientDao(connection);
					
		try {
			cliDao5.delete(cli8);
			System.out.println("Supression réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la supression de l'objet");
		}
					
				
				
			
			
		// Selection d'un objet Client
		System.out.println("Sélection d'un objet");
		
		ClientDao cliDao6 = new ClientDao(connection);
		Client cli9 = new Client();
			
		try {
			cli9=cliDao6.find(3);
			System.out.println("Objet sélectionné: "+ cli9.getIdClient() +" "+ cli9.getNom());
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la selection de l'objet");
		}
			
		
		
		
			
		
		
		
		// Selection de tous les objets Client
		System.out.println("Sélection de tous les objets");
		
		ClientDao cliDao7 = new ClientDao(connection);
		List<Client> listcli = new ArrayList<Client>();
			
		try {
			listcli=cliDao7.findAll();
			
			StringBuffer chaine = new StringBuffer();
			for (Client c:listcli){
				chaine.append(c.toString()+"\n");
			}
			System.out.println(chaine);
			
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des tous les objets");
		}
		
		
		
		
		
	
		// Sélection des objets Client entre deux PK
		System.out.println("Sélection d'objets entre deux PK");
		ClientDao cliDao8 = new ClientDao(connection);
		List<Client> listcli2 = new ArrayList<Client>();
		Integer a= new Integer(2);
		Integer b= new Integer(4);
	
		try {
			listcli2=cliDao8.findBetween(a,b);
			
			StringBuffer chaine2 = new StringBuffer();
			for (Client c:listcli2){
				chaine2.append(c.toString()+"\n");
			}
			System.out.println(chaine2);
			
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des objets entre deux PK");
		}
		
		
		


			
		// Fermer la connection
		Singleton.shutdown();
			
	}
		
}
