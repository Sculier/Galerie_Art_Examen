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
import pojo.Technique;
import pojoDAO.TechniqueDao;


public class TestTechniqueDAO {

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

		
				
		// Insertion d'un objet Technique
		Technique tech1 = new Technique();
		tech1.setNomTechnique("InsertTechnique");
		tech1.setIdTechnique(new Integer(0));
			
		TechniqueDao techDao1 = new TechniqueDao(connection);
			
		try {
			techDao1.insert(tech1);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}

		
		
		
		
		// Insertion d'un tableau d'objets Technique
		Technique t1 = new Technique();
		Technique t2 = new Technique();
		Technique t3 = new Technique();

		t1.setNomTechnique("Tech1");
		t1.setIdTechnique(new Integer(0));
		t2.setNomTechnique("Tech2");
		t2.setIdTechnique(new Integer(0));
		t3.setNomTechnique("Tech3");
		t3.setIdTechnique(new Integer(0));
	
	
		Technique [] tabTech = new Technique [3];
	
		tabTech[0]=t1;
		tabTech[1]=t2;
		tabTech[2]=t3;
	
	
		TechniqueDao techDao2 = new TechniqueDao(connection);
		
		try {
			techDao2.insertTabObj(tabTech);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion du tableau d'objets Technique");
		}
		
	
	
	
	
	
		// Insertion d'une Collection d'objets Technique
		Technique t4 = new Technique();
		Technique t5 = new Technique();
		Technique t6 = new Technique();

		t4.setNomTechnique("Tech4");
		t4.setIdTechnique(new Integer(0));
		t5.setNomTechnique("Tech5");
		t5.setIdTechnique(new Integer(0));
		t6.setNomTechnique("Tech6");
		t6.setIdTechnique(new Integer(0));
	
	
		List<Technique> maliste = new ArrayList<Technique>(); 

		maliste.add(t4); 
		maliste.add(t5);
		maliste.add(t6);
	
	
		TechniqueDao techDao3 = new TechniqueDao(connection);
	
		try {
			techDao3.insertCollObj(maliste);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion d'une Collection d'objets Technique");
		}
		
	

		
		// Mise à jour d'un objet Technique
		Technique tech2 = new Technique();
		tech2.setNomTechnique("UpDateTechnique");
		tech2.setIdTechnique(new Integer(4));
			
		TechniqueDao techDao4 = new TechniqueDao(connection);
			
		try {
			techDao4.update(tech2);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la mise à jour");
		}

		
		// Suppression d'un objet Technique
		Technique tech4 = new Technique();
		tech4.setIdTechnique(5);
					
		TechniqueDao techDao5 = new TechniqueDao(connection);
					
		try {
			techDao5.delete(tech4);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la supression de l'objet");
		}
					
				
				
		
			
			
		// Selection d'un objet Technique
		TechniqueDao techDao6 = new TechniqueDao(connection);
		Technique tech3 = new Technique();
			
		try {
			tech3=techDao6.find(2);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la selection de l'objet");
		}
			
		System.out.println(tech3.getIdTechnique() +" " + tech3.getNomTechnique());
		
		
		
			
		// Selection de tous les objets Technique
		TechniqueDao techDao7 = new TechniqueDao(connection);
		List<Technique> listTech = new ArrayList<Technique>();
			
		try {
			listTech=techDao7.findAll();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des tous les objets");
		}
			
		StringBuffer chaine = new StringBuffer();
			
		for (Technique t:listTech){
			chaine.append(t.toString()+"\n");
		}
		System.out.println(chaine);
		
		
	
		// Sélection des objets Technique entre deux PK
		TechniqueDao techDao8 = new TechniqueDao(connection);
		List<Technique> listTech2 = new ArrayList<Technique>();
		Integer a= new Integer(1);
		Integer b= new Integer(7);
	
		try {
			listTech2=techDao8.findBetween(a,b);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des objets entre deux PK");
		}
		
		StringBuffer chaine2 = new StringBuffer();
		
		for (Technique t:listTech2){
			chaine2.append(t.toString()+"\n");
		}
		System.out.println(chaine2);

			

			
		// Fermer la connection
		Singleton.shutdown();
			
	}
		
}
