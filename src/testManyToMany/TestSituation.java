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
import outilManytoMany.OutilSituation;
import pojo.Localexpo;
import pojo.Newsletter;
import pojo.Oeuvre;
import pojo.Exposition;
import pojo.Transport;
import pojoDAO.ExpositionDao;




public class TestSituation {

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
		
		String typeExpo = "Temporaire";
		
		Transport transp1 = new Transport();
		transp1.setIdTransport(new Integer(2));
		
		Oeuvre oeuv = new Oeuvre();
		oeuv.setIdOeuvre(new Integer(1));
		
		Localexpo loc1 = new Localexpo();
		loc1.setIdAdresseLocal(new Integer(1));
		
		Exposition expo1 = new Exposition();
		expo1.setIdExposition(new Integer(3));
		
		Newsletter newsl1 = new Newsletter();
		newsl1.setIdNewsletter(new Integer(1));
		
		
		OutilSituation sit1 = new OutilSituation(connection);
			
		try {
			sit1.insert(typeExpo, transp1, oeuv, loc1, expo1, newsl1);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch bexpok
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}
		
		
		
		// Insertion d'un tableau d'objets
		System.out.println("Insertion d'un tableau d'objets");
		
		String typeExpo2 = "Temporaire";
		
		Transport transp2 = new Transport();
		transp2.setIdTransport(new Integer(4));
		
		Localexpo loc2 = new Localexpo();
		loc2.setIdAdresseLocal(new Integer(1));
		
		Exposition expo2 = new Exposition();
		expo2.setIdExposition(new Integer(1));
		
		Newsletter newsl2 = new Newsletter();
		newsl2.setIdNewsletter(new Integer(1));
		
		
		Oeuvre oeuv1 = new Oeuvre();
		oeuv1.setIdOeuvre(1);

		Oeuvre oeuv2 = new Oeuvre();
		oeuv2.setIdOeuvre(2);
		
		Oeuvre oeuv3 = new Oeuvre();
		oeuv3.setIdOeuvre(3);
	
				
		Oeuvre [] taboeuv = new Oeuvre [3];
				
		taboeuv[0]=oeuv1;
		taboeuv[1]=oeuv2;
		taboeuv[2]=oeuv3;
					
					
		OutilSituation part2 = new OutilSituation(connection);
						
		try {
			part2.insertTabObjoeuv(typeExpo2, transp2, taboeuv, loc2, expo2, newsl2);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch bexpok
			//e.printStackTrace();
			System.out.println("Echec de l'insertion du tableau d'objets Oeuvre");
		}
						
					
					
					
			
			
		// Insertion d'une Collection d'objets 
		System.out.println("Insertion d'une collection d'objets");
		
		String typeExpo3 = "Temporaire";
		
		Transport transp3 = new Transport();
		transp3.setIdTransport(new Integer(3));
		
		Localexpo loc3 = new Localexpo();
		loc3.setIdAdresseLocal(new Integer(1));
		
		Exposition expo3 = new Exposition();
		expo3.setIdExposition(new Integer(1));
		
		Newsletter newsl3 = new Newsletter();
		newsl3.setIdNewsletter(new Integer(1));
		
		
		Oeuvre oeuv4 = new Oeuvre();
		oeuv4.setIdOeuvre(1);

		Oeuvre oeuv5 = new Oeuvre();
		oeuv5.setIdOeuvre(new Integer(2));
			
		Oeuvre oeuv6 = new Oeuvre();
		oeuv6.setIdOeuvre(new Integer(3));
	
					
		List<Oeuvre> maliste = new ArrayList<Oeuvre>(); 

		maliste.add(oeuv4); 
		maliste.add(oeuv5);
		maliste.add(oeuv6);
					
					
		OutilSituation part3 = new OutilSituation(connection);
					
		try {
			part3.insertCollObjoeuv(typeExpo3, transp3, maliste, loc3, expo3, newsl3);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch bexpok
			//e.printStackTrace();
			System.out.println("Echec de l'insertion d'une Collection d'objets Oeuvre");
		}
		
		
		

		// Suppression d'un objet 
		System.out.println("Supression d'un objet");
		
		Exposition expo4 = new Exposition();
		expo4.setIdExposition(3);
					
		OutilSituation part4 = new OutilSituation(connection);
					
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
		
		String typeExpo5 = "Temporaire";
		
		Transport transp5 = new Transport();
		transp5.setIdTransport(new Integer(1));
		
		Localexpo loc5 = new Localexpo();
		loc5.setIdAdresseLocal(new Integer(1));
		
		Exposition expo5 = new Exposition();
		expo5.setIdExposition(new Integer(1));
		
		Newsletter newsl5 = new Newsletter();
		newsl5.setIdNewsletter(new Integer(1));
		
		
		Oeuvre oeuv7 = new Oeuvre();
		oeuv7.setIdOeuvre(2);

		Oeuvre oeuv8 = new Oeuvre();
		oeuv8.setIdOeuvre(4);
		
		Oeuvre oeuv9 = new Oeuvre();
		oeuv9.setIdOeuvre(3);
		
		List<Oeuvre> maliste2 = new ArrayList<Oeuvre>(); 

		maliste2.add(oeuv7); 
		maliste2.add(oeuv8);
		maliste2.add(oeuv9);
					
		
		
		OutilSituation part5 = new OutilSituation(connection);
			
		try {
			part5.update(typeExpo5, transp5, maliste2, loc5, expo5, newsl5);
			System.out.println("Mise à jour réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la mise à jour");
		}
		
	
		
		


		
		
		// Selection de tous les objets 
		System.out.println("Sélection des objets:");
		
		Exposition expo6 = new Exposition();
		expo6.setIdExposition(1);
		
		Exposition expo7 = new Exposition();
		
		ExpositionDao expoDao = new ExpositionDao(connection);
		List<Oeuvre> listoeuv = new ArrayList<Oeuvre>();
		OutilSituation part6 = new OutilSituation(connection);
		
		try {
			expo7=expoDao.find(expo6.getIdExposition());
			System.out.println("L'exposition: "+ expo7.getIdExposition() +" "
																		+ expo7.getNomExposition());
			
			listoeuv=part6.find(expo6);
		} catch (MyException e) {
			// TODO Auto-generated catch bexpok
			//e.printStackTrace();
			System.out.println("Echec de la sélection des tous les objets");
		}
			
		StringBuffer chaine = new StringBuffer();
			
		for (Oeuvre oeuvr:listoeuv){
			chaine.append(oeuvr.toString()+"\n");
		}
		System.out.println(chaine);
		

		
		
		

		// Fermer la connection
		Singleton.shutdown();
			
	}
		
}
