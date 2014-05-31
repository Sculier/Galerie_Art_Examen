package testManyToMany;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import outilConnect.OutilLectParamConnect;
import outilConnect.Singleton;
import outilDAO.MyException;
import outilManytoMany.OutilParticipation;
import pojo.Client;
import pojo.Exposition;
import pojoDAO.ExpositionDao;




public class TestParticipation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Properties props = null;
		String chemin = "fichiers/Config.properties";
		
		try {
			props= OutilLectParamConnect.recupererProperties(chemin);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch bexpok
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch bexpok
			e.printStackTrace();
		}
		
	
		Connection connection = null;
		connection = Singleton.getInstance(props);

		
			
		// Insertion d'un objet 
		System.out.println("Insertion un objet");
		
		Exposition expo1 = new Exposition();
		expo1.setIdExposition(1);
		
		Client cl1 = new Client();
		cl1.setIdClient(1);
		

	
		
		OutilParticipation part1 = new OutilParticipation(connection);
			
		try {
			part1.insert(expo1, cl1);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch bexpok
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}

		
		
		
		// Insertion d'un tableau d'objets
		System.out.println("Insertion d'un tableau d'objets");
		
		Exposition expo2 = new Exposition();
		expo2.setIdExposition(2);
		
		Client cli1 = new Client();
		cli1.setIdClient(1);

		Client cli2 = new Client();
		cli2.setIdClient(2);
		
		Client cli3 = new Client();
		cli3.setIdClient(3);
	
				
		Client [] tabcl = new Client [3];
				
		tabcl[0]=cli1;
		tabcl[1]=cli2;
		tabcl[2]=cli3;
					
					
		OutilParticipation part2 = new OutilParticipation(connection);
						
		try {
			part2.insertTabObjcl(expo2, tabcl);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch bexpok
			//e.printStackTrace();
			System.out.println("Echec de l'insertion du tableau d'objets client");
		}
						
					
					
					
					
				
		// Insertion d'une Collection d'objets 
		System.out.println("Insertion d'une collection d'objets");
		
		Exposition expo3 = new Exposition();
		expo3.setIdExposition(3);
		
		Client cli4 = new Client();
		cli4.setIdClient(1);
	

		Client cli5 = new Client();
		cli5.setIdClient(new Integer(2));
	
			
		Client cli6 = new Client();
		cli6.setIdClient(new Integer(3));
	
					
		List<Client> maliste = new ArrayList<Client>(); 

		maliste.add(cli4); 
		maliste.add(cli5);
		maliste.add(cli6);
					
					
		OutilParticipation part3 = new OutilParticipation(connection);
					
		try {
			part3.insertCollObjcl(expo3, maliste);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch bexpok
			//e.printStackTrace();
			System.out.println("Echec de l'insertion d'une Collection d'objets client");
		}
				
		
		
		

		// Suppression d'un objet 
		System.out.println("Supression d'un objet");
		
		Exposition expo4 = new Exposition();
		expo4.setIdExposition(3);
					
		OutilParticipation part4 = new OutilParticipation(connection);
					
		try {
			part4.delete(expo4);
			System.out.println("Supression réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch bexpok
			//e.printStackTrace();
			System.out.println("Echec de la supression de l'objet");
		}
		
		
		
		
		// Mise à jour d'un objet Horaire
		System.out.println("Mise à jour d'un objet");
		
		Exposition expo5 = new Exposition();
		expo5.setIdExposition(2);
		
		
		Client cli7 = new Client();
		cli7.setIdClient(6);

		Client cli8 = new Client();
		cli8.setIdClient(7);
		
		Client cli9 = new Client();
		cli9.setIdClient(8);
		
		List<Client> maliste2 = new ArrayList<Client>(); 

		maliste2.add(cli7); 
		maliste2.add(cli8);
		maliste2.add(cli9);
					
		
		
		OutilParticipation part5 = new OutilParticipation(connection);
			
		try {
			part5.update(expo5, maliste2);
			System.out.println("Mise à jour réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la mise à jour");
		}
		
	
		
		


		
		
		// Selection de tous les objets 
		System.out.println("Sélection des objets:");
		
		Exposition expo6 = new Exposition();
		expo6.setIdExposition(2);
		
		Exposition expo7 = new Exposition();
		
		ExpositionDao expoDao = new ExpositionDao(connection);
		List<Client> listcl = new ArrayList<Client>();
		OutilParticipation part6 = new OutilParticipation(connection);
		
		try {
			expo7=expoDao.find(expo6.getIdExposition());
			System.out.println("L'exposition: "+ expo7.getIdExposition() +" "
																		+ expo7.getNomExposition());
			
			listcl=part6.find(expo6);
		} catch (MyException e) {
			// TODO Auto-generated catch bexpok
			//e.printStackTrace();
			System.out.println("Echec de la sélection des tous les objets");
		}
			
		StringBuffer chaine = new StringBuffer();
			
		for (Client cl:listcl){
			chaine.append(cl.toString()+"\n");
		}
		System.out.println(chaine);
		

		
		
		

		// Fermer la connection
		Singleton.shutdown();
			
	}
		
}
