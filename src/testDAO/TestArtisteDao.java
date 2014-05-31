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
import pojo.Artiste;
import pojo.Adresse;
import pojoDAO.ArtisteDao;



public class TestArtisteDao {

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

		
				
		// Insertion d'un objet Artiste
		System.out.println("Insertion un objet");
		
		Adresse adresse1 = new Adresse();
		adresse1.setIdAdresse(1);
			
		Artiste art = new Artiste();
		art.setIdArtiste(new Integer(0));
		art.setEmail("Email");
		art.setNom("Nom");
		art.setPrenom("Prenom");
		art.setTelephone("Telephone");
		art.setAdresse(adresse1);		
		
		
		
		ArtisteDao artDao1 = new ArtisteDao(connection);
			
		try {
			artDao1.insert(art);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}

		
		

		
		// Insertion personnalisée d'un objet Artiste
		System.out.println("Insertion un objet");
		
		Integer valpk=0;
		Adresse adresse = new Adresse();
		adresse.setIdAdresse(1);
					
		Artiste arti = new Artiste();
		arti.setIdArtiste(new Integer(0));
		arti.setEmail("Email perso");
		arti.setNom("Nom perso");
		arti.setPrenom("Prenom perso");
		arti.setTelephone("Telephone perso");
		arti.setAdresse(adresse);		
				
				
				
		ArtisteDao artDao = new ArtisteDao(connection);
					
		try {
			valpk=artDao.insertPerso(arti);
			System.out.println("Valeur de la pk: "+valpk);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}

		
		
		
		
		
		// Insertion d'un tableau d'objets Artiste
		System.out.println("Insertion tableau d'objets");
		
		Artiste art1 = new Artiste();
		art1.setIdArtiste(new Integer(0));
		art1.setEmail("Email 1");
		art1.setNom("Nom 1");
		art1.setPrenom("Prenom 1");
		art1.setTelephone("Telephone 1");
		art1.setAdresse(adresse1);		
		
		
		Artiste art2 = new Artiste();
		art2.setIdArtiste(new Integer(0));
		art2.setEmail("Email 2");
		art2.setNom("Nom 2");
		art2.setPrenom("Prenom 2");
		art2.setTelephone("Telephone 2");
		art2.setAdresse(adresse1);		
			
		
		Artiste art3 = new Artiste();
		art3.setIdArtiste(new Integer(0));
		art3.setEmail("Email 3");
		art3.setNom("Nom 3");
		art3.setPrenom("Prenom 3");
		art3.setTelephone("Telephone 3");
		art3.setAdresse(adresse1);		
		
		
		Artiste [] tabArt = new Artiste [3];
		
		tabArt[0]=art1;
		tabArt[1]=art2;
		tabArt[2]=art3;
		
		
		
		ArtisteDao artDao2 = new ArtisteDao(connection);
				
		try {
			artDao2.insertTabObj(tabArt);
			System.out.println("Insertion réussie");	
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion du tableau d'objets Artiste");
		}
				
		
			
			
			
			
		// Insertion d'une Collection d'objets Artiste
		System.out.println("Insertion collection d'objets");
		
		
		Artiste art4 = new Artiste();
		art4.setIdArtiste(new Integer(0));
		art4.setEmail("Email 4");
		art4.setNom("Nom 4");
		art4.setPrenom("Prenom 4");
		art4.setTelephone("Telephone 4");
		art4.setAdresse(adresse1);		
		
		
		Artiste art5 = new Artiste();
		art5.setIdArtiste(new Integer(0));
		art5.setEmail("Email 5");
		art5.setNom("Nom 5");
		art5.setPrenom("Prenom 5");
		art5.setTelephone("Telephone 5");
		art5.setAdresse(adresse1);		
			
		
		Artiste art6 = new Artiste();
		art6.setIdArtiste(new Integer(0));
		art6.setEmail("Email 6");
		art6.setNom("Nom 6");
		art6.setPrenom("Prenom 6");
		art6.setTelephone("Telephone 6");
		art6.setAdresse(adresse1);		
		
		
	
		List<Artiste> maliste = new ArrayList<Artiste>(); 

		maliste.add(art4); 
		maliste.add(art5);
		maliste.add(art6);
			
			
		ArtisteDao artDao3 = new ArtisteDao(connection);
			
		try {
			artDao3.insertCollObj(maliste);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion d'une Collection d'objets Artiste");
		}
				
			
		

		
		// Mise à jour d'un objet Artiste
		System.out.println("Mise à jour d'un objet");
		
		Artiste art7 = new Artiste();
		art7.setIdArtiste(new Integer(2));
		art7.setEmail("Email update");
		art7.setNom("Nom update");
		art7.setPrenom("Prenom update");
		art7.setTelephone("Telephone update");
		art7.setAdresse(adresse1);		
		
			
		ArtisteDao artDao4 = new ArtisteDao(connection);
			
		try {
			artDao4.update(art7);
			System.out.println("Mise à jour réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la mise à jour");
		}

		
		
		
		
		
		// Mise à jour personnalisée d'un objet Artiste
		System.out.println("Mise à jour d'un objet");
				
		Artiste artupdate = new Artiste();
		artupdate.setIdArtiste(new Integer(2));
		artupdate.setEmail("Email update perso");
		artupdate.setNom("Nom update perso");
		artupdate.setPrenom("Prenom update perso");
		artupdate.setTelephone("Telephone update perso");
				
					
		ArtisteDao artDaoupdate = new ArtisteDao(connection);
					
		try {
			artDaoupdate.updatePerso(artupdate);
			System.out.println("Mise à jour réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la mise à jour");
		}

		
		
		
		
		
		// Suppression d'un objet Artiste
		System.out.println("Supression d'un objet");
		
		Artiste art8 = new Artiste();
		art8.setIdArtiste(5);
					
		ArtisteDao artDao5 = new ArtisteDao(connection);
					
		try {
			artDao5.delete(art8);
			System.out.println("Supression réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la supression de l'objet");
		}
					
				
				
			
			
		// Selection d'un objet Artiste
		System.out.println("Sélection d'un objet");
		
		ArtisteDao artDao6 = new ArtisteDao(connection);
		Artiste art9 = new Artiste();
			
		try {
			art9=artDao6.find(3);
			System.out.println("Objet sélectionné: "+ art9.getIdArtiste() +" "+ art9.getNom());
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la selection de l'objet");
		}
			
		
		
		
			
		
		
		
		// Selection de tous les objets Artiste
		System.out.println("Sélection de tous les objets");
		
		ArtisteDao artDao7 = new ArtisteDao(connection);
		List<Artiste> listArt = new ArrayList<Artiste>();
			
		try {
			listArt=artDao7.findAll();
			
			StringBuffer chaine = new StringBuffer();
			for (Artiste artist:listArt){
				chaine.append(artist.toString()+"\n");
			}
			System.out.println(chaine);
			
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des tous les objets");
		}
		
		
		
		
		
	
		// Sélection des objets Artiste entre deux PK
		System.out.println("Sélection d'objets entre deux PK");
		ArtisteDao artDao8 = new ArtisteDao(connection);
		List<Artiste> listArt2 = new ArrayList<Artiste>();
		Integer a= new Integer(2);
		Integer b= new Integer(4);
	
		try {
			listArt2=artDao8.findBetween(a,b);
			
			StringBuffer chaine2 = new StringBuffer();
			for (Artiste artist:listArt2){
				chaine2.append(artist.toString()+"\n");
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
