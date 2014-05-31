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
import pojo.Localexpo;
import pojo.Adresse;
import pojoDAO.LocalexpoDao;



public class TestLocalexpoDao {

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

		
				
		// Insertion d'un objet Localexpo
		System.out.println("Insertion un objet");
		
		Adresse adresse1 = new Adresse();
		adresse1.setIdAdresse(1);
			
		Localexpo loc = new Localexpo();
		loc.setIdAdresseLocal(0);
		loc.setNomLocal("Nom");
		loc.setSuperficieExpo(55.20);
		loc.setTelephoneLocal("Telephone");
		loc.setAdresse(adresse1);		
		
		
		
		LocalexpoDao locDao1 = new LocalexpoDao(connection);
			
		try {
			locDao1.insert(loc);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}

		
		
		
		
		
		// Insertion d'un tableau d'objets Localexpo
		System.out.println("Insertion tableau d'objets");
		
		Adresse adresse2 = new Adresse();
		adresse2.setIdAdresse(2);
		
		Localexpo loc1 = new Localexpo();
		loc1.setIdAdresseLocal(0);
		loc1.setNomLocal("Nom 1");
		loc1.setSuperficieExpo(27.20);
		loc1.setTelephoneLocal("Telephone 1");
		loc1.setAdresse(adresse2);		
			
		Adresse adresse3 = new Adresse();
		adresse3.setIdAdresse(3);
		
		Localexpo loc2 = new Localexpo();
		loc2.setIdAdresseLocal(0);
		loc2.setNomLocal("Nom 2");
		loc2.setSuperficieExpo(55.20);
		loc2.setTelephoneLocal("Telephone 2");
		loc2.setAdresse(adresse3);		
		
		
		Adresse adresse4 = new Adresse();
		adresse4.setIdAdresse(4);
		
		Localexpo loc3 = new Localexpo();
		loc3.setIdAdresseLocal(0);
		loc3.setNomLocal("Nom 3");
		loc3.setSuperficieExpo(95.20);
		loc3.setTelephoneLocal("Telephone 3");
		loc3.setAdresse(adresse4);		
		
		
		
		Localexpo [] tabLoc = new Localexpo [3];
		
		tabLoc[0]=loc1;
		tabLoc[1]=loc2;
		tabLoc[2]=loc3;
		
		
		
		LocalexpoDao locDao2 = new LocalexpoDao(connection);
				
		try {
			locDao2.insertTabObj(tabLoc);
			System.out.println("Insertion réussie");	
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion du tableau d'objets Localexpo");
		}
				
		
			
			
			
			
		// Insertion d'une Collection d'objets Localexpo
		System.out.println("Insertion collection d'objets");
		
		Adresse adresse5 = new Adresse();
		adresse5.setIdAdresse(6);
		
		Localexpo loc4 = new Localexpo();
		loc4.setIdAdresseLocal(0);
		loc4.setNomLocal("Nom 4");
		loc4.setSuperficieExpo(89.20);
		loc4.setTelephoneLocal("Telephone 4");
		loc4.setAdresse(adresse5);		

		
		Adresse adresse6 = new Adresse();
		adresse6.setIdAdresse(7);
		
		Localexpo loc5 = new Localexpo();
		loc5.setIdAdresseLocal(0);
		loc5.setNomLocal("Nom 5");
		loc5.setSuperficieExpo(83.20);
		loc5.setTelephoneLocal("Telephone 5");
		loc5.setAdresse(adresse6);		
		
		Adresse adresse7 = new Adresse();
		adresse7.setIdAdresse(8);
		
		Localexpo loc6 = new Localexpo();
		loc6.setIdAdresseLocal(0);
		loc6.setNomLocal("Nom 6");
		loc6.setSuperficieExpo(588.20);
		loc6.setTelephoneLocal("Telephone 6");
		loc6.setAdresse(adresse7);		
		
		
		List<Localexpo> maliste = new ArrayList<Localexpo>(); 

		maliste.add(loc4); 
		maliste.add(loc5);
		maliste.add(loc6);
			
			
		LocalexpoDao locDao3 = new LocalexpoDao(connection);
			
		try {
			locDao3.insertCollObj(maliste);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion d'une Collection d'objets Localexpo");
		}
				
			
		

		
		// Mise à jour d'un objet Localexpo
		System.out.println("Mise à jour d'un objet");
		
		Localexpo loc7 = new Localexpo();
		loc7.setIdAdresseLocal(2);
		loc7.setNomLocal("Nom 7 update");
		loc7.setSuperficieExpo(38.20);
		loc7.setTelephoneLocal("Telephone 7 update");	
		
			
		LocalexpoDao locDao4 = new LocalexpoDao(connection);
			
		try {
			locDao4.update(loc7);
			System.out.println("Mise à jour réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la mise à jour");
		}

			
		
		
		
		
		
		// Suppression d'un objet Localexpo
		System.out.println("Supression d'un objet");
		
		Localexpo loc8 = new Localexpo();
		loc8.setIdAdresseLocal(5);
					
		LocalexpoDao locDao5 = new LocalexpoDao(connection);
					
		try {
			locDao5.delete(loc8);
			System.out.println("Supression réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la supression de l'objet");
		}
					
				
				
			
			
		// Selection d'un objet Localexpo
		System.out.println("Sélection d'un objet");
		
		LocalexpoDao locDao6 = new LocalexpoDao(connection);
		Localexpo loc9 = new Localexpo();
			
		try {
			loc9=locDao6.find(3);
			System.out.println("Objet sélectionné: "+ loc9.getIdAdresseLocal() 
															+" "+ loc9.getNomLocal());
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la selection de l'objet");
		}
			
		
		
		
			
		
		
		
		// Selection de tous les objets Localexpo
		System.out.println("Sélection de tous les objets");
		
		LocalexpoDao locDao7 = new LocalexpoDao(connection);
		List<Localexpo> listloc = new ArrayList<Localexpo>();
			
		try {
			listloc=locDao7.findAll();
			
			StringBuffer chaine = new StringBuffer();
			for (Localexpo localexpo:listloc){
				chaine.append(localexpo.toString()+"\n");
			}
			System.out.println(chaine);
			
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des tous les objets");
		}
		
		
		
		
		
	
		// Sélection des objets Localexpo entre deux PK
		System.out.println("Sélection d'objets entre deux PK");
		
		LocalexpoDao locDao8 = new LocalexpoDao(connection);
		List<Localexpo> listloc2 = new ArrayList<Localexpo>();
		Integer a= new Integer(2);
		Integer b= new Integer(4);
	
		try {
			listloc2=locDao8.findBetween(a,b);
			
			StringBuffer chaine2 = new StringBuffer();
			for (Localexpo localexpo:listloc2){
				chaine2.append(localexpo.toString()+"\n");
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
