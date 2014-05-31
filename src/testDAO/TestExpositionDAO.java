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
import pojo.Exposition;
import pojoDAO.ExpositionDao;



public class TestExpositionDAO {

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

		
				
		// Insertion d'un objet Exposition
		System.out.println("Insertion un objet");
		
	
		Exposition expo = new Exposition();
		expo.setIdExposition(new Integer(0));
		expo.setDateDebut(new Date());
		expo.setDateFin(new Date());
		expo.setDateVernissage(new Date());
		expo.setHeureDebutVernissage("15:00");
		expo.setNomExposition("Nom expo");
		expo.setThemeExpo("Theme expo");
		
		
		ExpositionDao expoDao1 = new ExpositionDao(connection);
			
		try {
			expoDao1.insert(expo);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}

		
		
		
		
		
		// Insertion d'un tableau d'objets Exposition
		System.out.println("Insertion tableau d'objets");
		
		Exposition expo1 = new Exposition();
		expo1.setIdExposition(new Integer(0));
		expo1.setDateDebut(new Date());
		expo1.setDateFin(new Date());
		expo1.setDateVernissage(new Date());
		expo1.setHeureDebutVernissage("18:00");
		expo1.setNomExposition("Nom expo 1");
		expo1.setThemeExpo("Theme expo 1");
		
		Exposition expo2 = new Exposition();
		expo2.setIdExposition(new Integer(0));
		expo2.setDateDebut(new Date());
		expo2.setDateFin(new Date());
		expo2.setDateVernissage(new Date());
		expo2.setHeureDebutVernissage("21:00");
		expo2.setNomExposition("Nom expo 2");
		expo2.setThemeExpo("Theme expo 2");
		
		
		Exposition expo3 = new Exposition();
		expo3.setIdExposition(new Integer(0));
		expo3.setDateDebut(new Date());
		expo3.setDateFin(new Date());
		expo3.setDateVernissage(new Date());
		expo3.setHeureDebutVernissage("22:00");
		expo3.setNomExposition("Nom expo 3");
		expo3.setThemeExpo("Theme expo 3");
		
		
		Exposition [] tabexpo = new Exposition [3];
		
		tabexpo[0]=expo1;
		tabexpo[1]=expo2;
		tabexpo[2]=expo3;
		
		
		
		ExpositionDao expoDao2 = new ExpositionDao(connection);
				
		try {
			expoDao2.insertTabObj(tabexpo);
			System.out.println("Insertion réussie");	
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion du tableau d'objets Exposition");
		}
				
		
			
			
			
			
		// Insertion d'une Collection d'objets Exposition
		System.out.println("Insertion collection d'objets");
		
		Exposition expo4 = new Exposition();
		expo4.setIdExposition(new Integer(0));
		expo4.setDateDebut(new Date());
		expo4.setDateFin(new Date());
		expo4.setDateVernissage(new Date());
		expo4.setHeureDebutVernissage("23:00");
		expo4.setNomExposition("Nom expo 4");
		expo4.setThemeExpo("Theme expo 4");
		
		Exposition expo5 = new Exposition();
		expo5.setIdExposition(new Integer(0));
		expo5.setDateDebut(new Date());
		expo5.setDateFin(new Date());
		expo5.setDateVernissage(new Date());
		expo5.setHeureDebutVernissage("19:00");
		expo5.setNomExposition("Nom expo 5");
		expo5.setThemeExpo("Theme expo 5");
		
		Exposition expo6 = new Exposition();
		expo6.setIdExposition(new Integer(0));
		expo6.setDateDebut(new Date());
		expo6.setDateFin(new Date());
		expo6.setDateVernissage(new Date());
		expo6.setHeureDebutVernissage("13:00");
		expo6.setNomExposition("Nom expo 6");
		expo6.setThemeExpo("Theme expo 6");
		
	
		List<Exposition> maliste = new ArrayList<Exposition>(); 

		maliste.add(expo4); 
		maliste.add(expo5);
		maliste.add(expo6);
			
			
		ExpositionDao expoDao3 = new ExpositionDao(connection);
			
		try {
			expoDao3.insertCollObj(maliste);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion d'une Collection d'objets Exposition");
		}
				
			
		

		
		// Mise à jour d'un objet Exposition
		System.out.println("Mise à jour d'un objet");
		
		Exposition expo7 = new Exposition();
		expo7.setIdExposition(new Integer(2));
		expo7.setDateDebut(new Date());
		expo7.setDateFin(new Date());
		expo7.setDateVernissage(new Date());
		expo7.setHeureDebutVernissage("14:00");
		expo7.setNomExposition("Nom expo Update");
		expo7.setThemeExpo("Theme expo Update");
		
			
		ExpositionDao expoDao4 = new ExpositionDao(connection);
			
		try {
			expoDao4.update(expo7);
			System.out.println("Mise à jour réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la mise à jour");
		}

			
		
		
		
		
		
		// Suppression d'un objet Exposition
		System.out.println("Supression d'un objet");
		
		Exposition expo8 = new Exposition();
		expo8.setIdExposition(5);
					
		ExpositionDao expoDao5 = new ExpositionDao(connection);
					
		try {
			expoDao5.delete(expo8);
			System.out.println("Supression réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la supression de l'objet");
		}
					
				
				
			
			
		// Selection d'un objet Exposition
		System.out.println("Sélection d'un objet");
		
		ExpositionDao expoDao6 = new ExpositionDao(connection);
		Exposition expo9 = new Exposition();
			
		try {
			expo9=expoDao6.find(3);
			System.out.println("Objet sélectionné: "+ expo9.getIdExposition() + " "
																+ expo9.getNomExposition());
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la selection de l'objet");
		}
			
		
		
		
			
		
		
		
		// Selection de tous les objets Exposition
		System.out.println("Sélection de tous les objets");
		
		ExpositionDao expoDao7 = new ExpositionDao(connection);
		List<Exposition> listexpo = new ArrayList<Exposition>();
			
		try {
			listexpo=expoDao7.findAll();
			
			StringBuffer chaine = new StringBuffer();
			for (Exposition exposition:listexpo){
				chaine.append(exposition.toString()+"\n");
			}
			System.out.println(chaine);
			
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des tous les objets");
		}
		
		
		
		
		
	
		// Sélection des objets Exposition entre deux PK
		System.out.println("Sélection d'objets entre deux PK");
		ExpositionDao expoDao8 = new ExpositionDao(connection);
		List<Exposition> listexpo2 = new ArrayList<Exposition>();
		Integer a= new Integer(2);
		Integer b= new Integer(4);
	
		try {
			listexpo2=expoDao8.findBetween(a,b);
			
			StringBuffer chaine2 = new StringBuffer();
			for (Exposition exposition:listexpo2){
				chaine2.append(exposition.toString()+"\n");
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
