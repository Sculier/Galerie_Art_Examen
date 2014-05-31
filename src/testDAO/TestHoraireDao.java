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
import pojo.Horaire;
import pojo.Localexpo;
import pojoDAO.HoraireDao;



public class TestHoraireDao {

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

		
				
		// Insertion d'un objet Horaire
		
		Localexpo loc = new Localexpo();
		loc.setIdAdresseLocal(1);
		
		Horaire hor1 = new Horaire();
		hor1.setIdHoraire(new Integer(0));
		hor1.setHeureFermeture("InsertHoraire fermeture");
		hor1.setHeureOuverture("InsertHoraire Ouverture");
		hor1.setJour("InsertHoraire Jour");
		hor1.setLocalexpo(loc);
		
		HoraireDao horDao1 = new HoraireDao(connection);
			
		try {
			horDao1.insert(hor1);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}

		
		// Insertion d'un tableau d'objets Horaire
		Horaire h1 = new Horaire();
		h1.setIdHoraire(new Integer(0));
		h1.setHeureFermeture("Fermeture 1");
		h1.setHeureOuverture("Ouverture 1");
		h1.setJour("Jour 1");
		h1.setLocalexpo(loc);

		Horaire h2 = new Horaire();
		h2.setIdHoraire(new Integer(0));
		h2.setHeureFermeture("Fermeture 2");
		h2.setHeureOuverture("Ouverture 2");
		h2.setJour("Jour 2");
		h2.setLocalexpo(loc);
		
		Horaire h3 = new Horaire();
		h3.setIdHoraire(new Integer(0));
		h3.setHeureFermeture("Fermeture 3");
		h3.setHeureOuverture("Ouverture 3");
		h3.setJour("Jour 3");
		h3.setLocalexpo(loc);
		
		Horaire [] tabhor = new Horaire [3];
		
		tabhor[0]=h1;
		tabhor[1]=h2;
		tabhor[2]=h3;
			
			
		HoraireDao horDao2 = new HoraireDao(connection);
				
		try {
			horDao2.insertTabObj(tabhor);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion du tableau d'objets Horaire");
		}
				
			
			
			
			
			
		// Insertion d'une Collection d'objets Horaire
		Horaire h4 = new Horaire();
		h4.setIdHoraire(new Integer(0));
		h4.setHeureFermeture("Fermeture 4");
		h4.setHeureOuverture("Ouverture 4");
		h4.setJour("Jour 4");
		h4.setLocalexpo(loc);
		
		Horaire h5 = new Horaire();
		h5.setIdHoraire(new Integer(0));
		h5.setHeureFermeture("Fermeture 5");
		h5.setHeureOuverture("Ouverture 5");
		h5.setJour("Jour 5");
		h5.setLocalexpo(loc);
	
		Horaire h6 = new Horaire();
		h6.setIdHoraire(new Integer(0));
		h6.setHeureFermeture("Fermeture 6");
		h6.setHeureOuverture("Ouverture 6");
		h6.setJour("Jour 6");
		h6.setLocalexpo(loc);	
			
		List<Horaire> maliste = new ArrayList<Horaire>(); 

		maliste.add(h4); 
		maliste.add(h5);
		maliste.add(h6);
			
			
		HoraireDao horDao3 = new HoraireDao(connection);
			
		try {
			horDao3.insertCollObj(maliste);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion d'une Collection d'objets Horaire");
		}
				
			
		

		
		// Mise à jour d'un objet Horaire
		Horaire h7 = new Horaire();
		h7.setIdHoraire(new Integer(4));
		h7.setHeureFermeture("Fermeture Update");
		h7.setHeureOuverture("Ouverture Update");
		h7.setJour("Jour Update");
		h7.setLocalexpo(loc);
		
		HoraireDao horDao4 = new HoraireDao(connection);
			
		try {
			horDao4.update(h7);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la mise à jour");
		}

		
		
		
		// Suppression d'un objet Horaire
		Horaire h8 = new Horaire();
		h8.setIdHoraire(5);
					
		HoraireDao horDao5 = new HoraireDao(connection);
					
		try {
			horDao5.delete(h8);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la supression de l'objet");
		}
					
				
				
			
			
		// Selection d'un objet Horaire
		HoraireDao horDao6 = new HoraireDao(connection);
		Horaire h9 = new Horaire();
			
		try {
			h9=horDao6.find(2);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la selection de l'objet");
		}
			
		System.out.println(h9.getIdHoraire() +" " + h9.getJour());
		
		
			
		
		
		
		// Selection de tous les objets Horaire
		HoraireDao horDao7 = new HoraireDao(connection);
		List<Horaire> listhor = new ArrayList<Horaire>();
			
		try {
			listhor=horDao7.findAll();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des tous les objets");
		}
			
		StringBuffer chaine = new StringBuffer();
			
		for (Horaire h:listhor){
			chaine.append(h.toString()+"\n");
		}
		System.out.println(chaine);
		
		
		
	
		// Sélection des objets Horaire entre deux PK
		HoraireDao horDao8 = new HoraireDao(connection);
		List<Horaire> listhor2 = new ArrayList<Horaire>();
		Integer a= new Integer(1);
		Integer b= new Integer(7);
	
		try {
			listhor2=horDao8.findBetween(a,b);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des objets entre deux PK");
		}
		
		StringBuffer chaine2 = new StringBuffer();
		
		for (Horaire h:listhor2){
			chaine2.append(h.toString()+"\n");
		}
		System.out.println(chaine2);


			
		// Fermer la connection
		Singleton.shutdown();
			
	}
		
}
