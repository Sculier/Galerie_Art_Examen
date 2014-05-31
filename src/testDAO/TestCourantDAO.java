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
import pojo.Courant;
import pojoDAO.CourantDao;



public class TestCourantDAO {

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

		
				
		// Insertion d'un objet Courant
		Courant cour1 = new Courant();
		cour1.setNomCourant("InsertCourant");
		cour1.setIdCourant(new Integer(0));
			
		CourantDao courDao1 = new CourantDao(connection);
			
		try {
			courDao1.insert(cour1);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}

		
		// Insertion d'un tableau d'objets Courant
		Courant c1 = new Courant();
		Courant c2 = new Courant();
		Courant c3 = new Courant();

		c1.setNomCourant("Cour1");
		c1.setIdCourant(new Integer(0));
		c2.setNomCourant("Cour2");
		c2.setIdCourant(new Integer(0));
		c3.setNomCourant("Cour3");
		c3.setIdCourant(new Integer(0));
			
			
		Courant [] tabCour = new Courant [3];
		
		tabCour[0]=c1;
		tabCour[1]=c2;
		tabCour[2]=c3;
			
			
		CourantDao courDao2 = new CourantDao(connection);
				
		try {
			courDao2.insertTabObj(tabCour);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion du tableau d'objets Courant");
		}
				
			
			
			
			
			
		// Insertion d'une Collection d'objets Courant
		Courant c4 = new Courant();
		Courant c5 = new Courant();
		Courant c6 = new Courant();

		c4.setNomCourant("Cour4");
		c4.setIdCourant(new Integer(0));
		c5.setNomCourant("Cour5");
		c5.setIdCourant(new Integer(0));
		c6.setNomCourant("Cour6");
		c6.setIdCourant(new Integer(0));
			
			
		List<Courant> maliste = new ArrayList<Courant>(); 

		maliste.add(c4); 
		maliste.add(c5);
		maliste.add(c6);
			
			
		CourantDao courDao3 = new CourantDao(connection);
			
		try {
			courDao3.insertCollObj(maliste);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion d'une Collection d'objets Courant");
		}
				
			
		

		
		// Mise à jour d'un objet Courant
		Courant cour2 = new Courant();
		cour2.setNomCourant("UpDateCourant");
		cour2.setIdCourant(new Integer(4));
			
		CourantDao courDao4 = new CourantDao(connection);
			
		try {
			courDao4.update(cour2);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la mise à jour");
		}

		
		
		
		// Suppression d'un objet Courant
		Courant cour4 = new Courant();
		cour4.setIdCourant(5);
					
		CourantDao courDao5 = new CourantDao(connection);
					
		try {
			courDao5.delete(cour4);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la supression de l'objet");
		}
					
				
				
			
			
		// Selection d'un objet Courant
		CourantDao courDao6 = new CourantDao(connection);
		Courant cour3 = new Courant();
			
		try {
			cour3=courDao6.find(2);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la selection de l'objet");
		}
			
		System.out.println(cour3.getIdCourant() +" " + cour3.getNomCourant());
		
		
			
		
		
		
		// Selection de tous les objets Courant
		CourantDao courDao7 = new CourantDao(connection);
		List<Courant> listCour = new ArrayList<Courant>();
			
		try {
			listCour=courDao7.findAll();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des tous les objets");
		}
			
		StringBuffer chaine = new StringBuffer();
			
		for (Courant c:listCour){
			chaine.append(c.toString()+"\n");
		}
		System.out.println(chaine);
		
		
		
	
		// Sélection des objets Courant entre deux PK
		CourantDao courDao8 = new CourantDao(connection);
		List<Courant> listCour2 = new ArrayList<Courant>();
		Integer a= new Integer(1);
		Integer b= new Integer(7);
	
		try {
			listCour2=courDao8.findBetween(a,b);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des objets entre deux PK");
		}
		
		StringBuffer chaine2 = new StringBuffer();
		
		for (Courant c:listCour2){
			chaine2.append(c.toString()+"\n");
		}
		System.out.println(chaine2);


			
		// Fermer la connection
		Singleton.shutdown();
			
	}
		
}
