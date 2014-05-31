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
import pojo.Categorie;
import pojoDAO.CategorieDao;


public class TestCategorieDAO {

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

		
				
		// Insertion d'un objet Categorie
		Categorie cat1 = new Categorie();
		cat1.setNomCategorie("InsertCategorie");
		cat1.setIdCategorie(new Integer(0));
			
		CategorieDao catDao1 = new CategorieDao(connection);
			
		try {
			catDao1.insert(cat1);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}

		
	
		
		// Insertion d'un tableau d'objets Categorie
		Categorie c1 = new Categorie();
		Categorie c2 = new Categorie();
		Categorie c3 = new Categorie();

		c1.setNomCategorie("Cat1");
		c1.setIdCategorie(new Integer(0));
		c2.setNomCategorie("Cat2");
		c2.setIdCategorie(new Integer(0));
		c3.setNomCategorie("Cat3");
		c3.setIdCategorie(new Integer(0));
		
		
		Categorie [] tabCat = new Categorie [3];
		
		tabCat[0]=c1;
		tabCat[1]=c2;
		tabCat[2]=c3;
		
		
		CategorieDao catDao2 = new CategorieDao(connection);
			
		try {
			catDao2.insertTabObj(tabCat);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion du tableau d'objets Categorie");
		}
			
		
		
		
		
		
		// Insertion d'une Collection d'objets Categorie
		Categorie c4 = new Categorie();
		Categorie c5 = new Categorie();
		Categorie c6 = new Categorie();

		c4.setNomCategorie("Cat4");
		c4.setIdCategorie(new Integer(0));
		c5.setNomCategorie("Cat5");
		c5.setIdCategorie(new Integer(0));
		c6.setNomCategorie("Cat6");
		c6.setIdCategorie(new Integer(0));
		
		
		List<Categorie> maliste = new ArrayList<Categorie>(); 

		maliste.add(c4); 
		maliste.add(c5);
		maliste.add(c6);
		
		
		CategorieDao catDao3 = new CategorieDao(connection);
		
		try {
			catDao3.insertCollObj(maliste);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion d'une Collection d'objets Categorie");
		}
			
		
		

		
		// Mise à jour d'un objet Categorie
		Categorie cat2 = new Categorie();
		cat2.setNomCategorie("UpDateCategorie");
		cat2.setIdCategorie(new Integer(4));
			
		CategorieDao catDao4 = new CategorieDao(connection);
			
		try {
			catDao4.update(cat2);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la mise à jour");
		}

		
		
		
		// Suppression d'un objet Categorie
		Categorie cat4 = new Categorie();
		cat4.setIdCategorie(5);
			
		CategorieDao catDao5 = new CategorieDao(connection);
			
		try {
			catDao5.delete(cat4);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la supression de l'objet");
		}
			
		
			
			
		// Selection d'un objet Categorie
		CategorieDao catDao6 = new CategorieDao(connection);
		Categorie cat3 = new Categorie();
			
		try {
			cat3=catDao6.find(2);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la selection de l'objet");
		}
			
		System.out.println(cat3.getIdCategorie() +" " + cat3.getNomCategorie());
		
		
			
		
			
			
		// Selection de tous les objets Categorie
		CategorieDao catDao7 = new CategorieDao(connection);
		List<Categorie> listCat = new ArrayList<Categorie>();
			
		try {
			listCat=catDao7.findAll();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des tous les objets");
		}
			
		StringBuffer chaine = new StringBuffer();
			
		for (Categorie c:listCat){
			chaine.append(c.toString()+"\n");
		}
		System.out.println(chaine);
		
		
		
		// Sélection des objets Categorie entre deux PK
		CategorieDao catDao8 = new CategorieDao(connection);
		List<Categorie> listCat2 = new ArrayList<Categorie>();
		Integer a= new Integer(1);
		Integer b= new Integer(7);
		
		try {
			listCat2=catDao8.findBetween(a,b);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des objets entre deux PK");
		}
			
		StringBuffer chaine2 = new StringBuffer();
			
		for (Categorie c:listCat2){
			chaine2.append(c.toString()+"\n");
		}
		System.out.println(chaine2);
		
		

			
		// Fermer la connection
		Singleton.shutdown();
			
	}
		
}
