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
import pojo.Pays;
import pojoDAO.PaysDao;


public class TestPaysDAO {

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

		
				
		// Insertion d'un objet Pays
		Pays pays1 = new Pays();
		pays1.setNom("InsertPays");
		pays1.setIdPays(new Integer(0));
			
		PaysDao paysDao1 = new PaysDao(connection);
			
		try {
			paysDao1.insert(pays1);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}

		
		
		// Insertion personnalisée d'un objet Pays
		Integer valpk=0;
		Pays pays = new Pays();
		pays.setNom("InsertPersoPays");
		pays.setIdPays(new Integer(0));
					
		PaysDao paysDao = new PaysDao(connection);
					
		try {
			valpk=paysDao.insertPerso(pays);
			System.out.println("Valeur de la pk: "+valpk);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}

		

		
		// Insertion d'un tableau d'objets Pays
		Pays p1 = new Pays();
		Pays p2 = new Pays();
		Pays p3 = new Pays();

		p1.setNom("Pays1");
		p1.setIdPays(new Integer(0));
		p2.setNom("Pays2");
		p2.setIdPays(new Integer(0));
		p3.setNom("Pays3");
		p3.setIdPays(new Integer(0));
	
	
		Pays [] tabP = new Pays [3];
	
		tabP[0]=p1;
		tabP[1]=p2;
		tabP[2]=p3;
	
	
		PaysDao paysDao2 = new PaysDao(connection);
		
		try {
			paysDao2.insertTabObj(tabP);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion du tableau d'objets Pays");
		}
		
	
	
	
	
	
		// Insertion d'une Collection d'objets Pays
		Pays p4 = new Pays();
		Pays p5 = new Pays();
		Pays p6 = new Pays();

		p4.setNom("Pays4");
		p4.setIdPays(new Integer(0));
		p5.setNom("Pays5");
		p5.setIdPays(new Integer(0));
		p6.setNom("Pays6");
		p6.setIdPays(new Integer(0));
	
	
		List<Pays> maliste = new ArrayList<Pays>(); 

		maliste.add(p4); 
		maliste.add(p5);
		maliste.add(p6);
	
	
		PaysDao paysDao3 = new PaysDao(connection);
	
		try {
			paysDao3.insertCollObj(maliste);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion d'une Collection d'objets Pays");
		}
		
			

		
		// Mise à jour d'un objet Pays
		Pays pays2 = new Pays();
		pays2.setNom("UpDatePays");
		pays2.setIdPays(new Integer(4));
			
		PaysDao paysDao4 = new PaysDao(connection);
			
		try {
			paysDao4.update(pays2);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la mise à jour");
		}

		
	
		
		// Suppression d'un objet Pays
		Pays pays4 = new Pays();
		pays4.setIdPays(5);
			
		PaysDao paysDao5 = new PaysDao(connection);
			
		try {
			paysDao5.delete(pays4);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la supression de l'objet");
		}
			
		
			
			
		// Selection d'un objet Pays
		PaysDao paysDao6 = new PaysDao(connection);
		Pays pays3 = new Pays();
			
		try {
			pays3=paysDao6.find(2);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la selection de l'objet");
		}
			
		System.out.println(pays3.getIdPays() +" " + pays3.getNom());
		
		
			
	
		// Selection de tous les objets Pays
		PaysDao paysDao7 = new PaysDao(connection);
		List<Pays> listPays = new ArrayList<Pays>();
			
		try {
			listPays=paysDao7.findAll();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des tous les objets");
		}
			
		StringBuffer chaine = new StringBuffer();
			
		for (Pays p:listPays){
			chaine.append(p.toString()+"\n");
		}
		System.out.println(chaine);
		
		
		
		// Sélection des objets Pays entre deux PK
		PaysDao paysDao8 = new PaysDao(connection);
		List<Pays> listPays2 = new ArrayList<Pays>();
		Integer a= new Integer(1);
		Integer b= new Integer(7);
	
		try {
			listPays2=paysDao8.findBetween(a,b);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des objets entre deux PK");
		}
		
		StringBuffer chaine2 = new StringBuffer();
		
		for (Pays p:listPays2){
			chaine2.append(p.toString()+"\n");
		}
		System.out.println(chaine2);

	
	
	
		// Fermer la connection
		Singleton.shutdown();
			
	}
		
}
