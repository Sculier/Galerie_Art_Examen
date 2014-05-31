package testDAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import outilConnect.OutilLectParamConnect;
import outilConnect.Singleton;
import outilDAO.MyException;
import pojo.Adresse;
import pojo.Pays;
import pojoDAO.AdresseDao;



public class TestAdresseDAO {

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

		
				
		// Insertion d'un objet Adresse
		System.out.println("Insertion un objet");
		
		Pays pays1 = new Pays();
		pays1.setIdPays(1);
			
		Adresse ad = new Adresse();
		ad.setIdAdresse(new Integer(0));
		ad.setCodePostal("Code postal");
		ad.setLocalite("Localite");
		ad.setNumero("Numéro");
		ad.setRue("Rue");
		ad.setPays(pays1);		
		
		
		
		AdresseDao adDao1 = new AdresseDao(connection);
			
		try {
			adDao1.insert(ad);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}

		
		
		
		// Insertion personnalisée d'un objet Adresse
		System.out.println("Insertion un objet");
		
		Integer valpk = 0;
		Pays pays = new Pays();
		pays.setIdPays(2);
					
		Adresse adr = new Adresse();
		adr.setIdAdresse(new Integer(0));
		adr.setCodePostal("Code postal perso");
		adr.setLocalite("Localite perso");
		adr.setNumero("Numéro perso");
		adr.setRue("Rue perso");
		adr.setPays(pays);		
				
		AdresseDao adDao = new AdresseDao(connection);
					
		try {
			valpk=adDao.insertPerso(adr);
			System.out.println("Valeur de la pk "+valpk);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}

		
		
		
		
		
		// Insertion d'un tableau d'objets Adresse
		System.out.println("Insertion tableau d'objets");
		
		Adresse ad1 = new Adresse();
		ad1.setIdAdresse(new Integer(0));
		ad1.setCodePostal("Code postal 1");
		ad1.setLocalite("Localite 1");
		ad1.setNumero("Numéro 1");
		ad1.setRue("Rue 1");
		ad1.setPays(pays1);		
		
		Adresse ad2 = new Adresse();
		ad2.setIdAdresse(new Integer(0));
		ad2.setCodePostal("Code postal 2");
		ad2.setLocalite("Localite 2");
		ad2.setNumero("Numéro 2");
		ad2.setRue("Rue 2");
		ad2.setPays(pays1);		
		
		Adresse ad3 = new Adresse();
		ad3.setIdAdresse(new Integer(0));
		ad3.setCodePostal("Code postal 3");
		ad3.setLocalite("Localite 3");
		ad3.setNumero("Numéro 3");
		ad3.setRue("Rue 3");
		ad3.setPays(pays1);		
	
		
		Adresse [] tabAd = new Adresse [3];
		
		tabAd[0]=ad1;
		tabAd[1]=ad2;
		tabAd[2]=ad3;
		
		
		
		AdresseDao adDao2 = new AdresseDao(connection);
				
		try {
			adDao2.insertTabObj(tabAd);
			System.out.println("Insertion réussie");	
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion du tableau d'objets Adresse");
		}
				
		
			
			
			
			
		// Insertion d'une Collection d'objets Adresse
		System.out.println("Insertion collection d'objets");
		
		Adresse ad4 = new Adresse();
		ad4.setIdAdresse(new Integer(0));
		ad4.setCodePostal("Code postal 4");
		ad4.setLocalite("Localite 4");
		ad4.setNumero("Numéro 4");
		ad4.setRue("Rue 4");
		ad4.setPays(pays1);		
		
		Adresse ad5 = new Adresse();
		ad5.setIdAdresse(new Integer(0));
		ad5.setCodePostal("Code postal 5");
		ad5.setLocalite("Localite 5");
		ad5.setNumero("Numéro 5");
		ad5.setRue("Rue 5");
		ad5.setPays(pays1);		
		
		Adresse ad6 = new Adresse();
		ad6.setIdAdresse(new Integer(0));
		ad6.setCodePostal("Code postal 6");
		ad6.setLocalite("Localite 6");
		ad6.setNumero("Numéro 6");
		ad6.setRue("Rue 6");
		ad6.setPays(pays1);		
		
	
		List<Adresse> maliste = new ArrayList<Adresse>(); 

		maliste.add(ad4); 
		maliste.add(ad5);
		maliste.add(ad6);
			
			
		AdresseDao adDao3 = new AdresseDao(connection);
			
		try {
			adDao3.insertCollObj(maliste);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion d'une Collection d'objets Adresse");
		}
				
			
		

		
		// Mise à jour d'un objet Adresse
		System.out.println("Mise à jour d'un objet");
		
		Adresse ad7 = new Adresse();
		ad7.setIdAdresse(new Integer(2));
		ad7.setCodePostal("Update code postal");
		ad7.setLocalite("Update Localite");
		ad7.setNumero("Update Numéro");
		ad7.setRue("Update Rue");
		ad7.setPays(pays1);		
		
			
		AdresseDao adDao4 = new AdresseDao(connection);
			
		try {
			adDao4.update(ad7);
			System.out.println("Mise à jour réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la mise à jour");
		}

		
		// Mise à jour personnalisée d'un objet Adresse
		System.out.println("Mise à jour d'un objet");
				
		Adresse adupdateperso = new Adresse();
		adupdateperso.setIdAdresse(new Integer(2));
		adupdateperso.setCodePostal("Updateperso code postal");
		adupdateperso.setLocalite("Updateperso Localite");
		adupdateperso.setNumero("Updateperso Numéro");
		adupdateperso.setRue("Updateperso Rue");
		
					
		AdresseDao adDaoupdateperso = new AdresseDao(connection);
					
		try {
			adDaoupdateperso.updatePerso(adupdateperso);
			System.out.println("Mise à jour réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la mise à jour");
		}	
		
		
		
		
		
		// Suppression d'un objet Adresse
		System.out.println("Supression d'un objet");
		
		Adresse ad8 = new Adresse();
		ad8.setIdAdresse(5);
					
		AdresseDao adDao5 = new AdresseDao(connection);
					
		try {
			adDao5.delete(ad8);
			System.out.println("Supression réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la supression de l'objet");
		}
					
				
				
			
			
		// Selection d'un objet Adresse
		System.out.println("Sélection d'un objet");
		
		AdresseDao adDao6 = new AdresseDao(connection);
		Adresse ad9 = new Adresse();
			
		try {
			ad9=adDao6.find(3);
			System.out.println("Objet sélectionné: "+ ad9.getIdAdresse() +" "+ ad9.getLocalite());
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la selection de l'objet");
		}
			
		
		
		
			
		
		
		
		// Selection de tous les objets Adresse
		System.out.println("Sélection de tous les objets");
		
		AdresseDao adDao7 = new AdresseDao(connection);
		List<Adresse> listAd = new ArrayList<Adresse>();
			
		try {
			listAd=adDao7.findAll();
			
			StringBuffer chaine = new StringBuffer();
			for (Adresse adresse:listAd){
				chaine.append(adresse.toString()+"\n");
			}
			System.out.println(chaine);
			
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des tous les objets");
		}
		
		
		
		
		
	
		// Sélection des objets Adresse entre deux PK
		System.out.println("Sélection d'objets entre deux PK");
		AdresseDao adDao8 = new AdresseDao(connection);
		List<Adresse> listAd2 = new ArrayList<Adresse>();
		Integer a= new Integer(2);
		Integer b= new Integer(4);
	
		try {
			listAd2=adDao8.findBetween(a,b);
			
			StringBuffer chaine2 = new StringBuffer();
			for (Adresse adresse:listAd2){
				chaine2.append(adresse.toString()+"\n");
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
