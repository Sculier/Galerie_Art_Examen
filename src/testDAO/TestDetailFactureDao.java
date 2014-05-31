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
import pojo.DetailFacture;
import pojo.Facture;
import pojo.Oeuvre;
import pojoDAO.DetailFactureDao;



public class TestDetailFactureDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Properties props = null;
		String chemin = "fichiers/Config.properties";
		
		try {
			props= OutilLectParamConnect.recupererProperties(chemin);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch bdetk
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch bdetk
			e.printStackTrace();
		}
		
	
		Connection connection = null;
		connection = Singleton.getInstance(props);

		
				
		// Insertion d'un objet DetailFacture
		System.out.println("Insertion un objet");
		
		Facture facture1 = new Facture();
		facture1.setIdFacture(1);

		Oeuvre oeuvre1 = new Oeuvre();
		oeuvre1.setIdOeuvre(1);
		
		DetailFacture det = new DetailFacture();
		det.setIdDetail(0);
		det.setDateDetail(new Date());
		det.setMontantComissionDetail(4556.55);
		det.setMontantTvaDetail(5645);
		det.setPrixHtvaDetail(5656);
		det.setOeuvre(oeuvre1);
		det.setFacture(facture1);
		

		
		DetailFactureDao detDao1 = new DetailFactureDao(connection);
			
		try {
			detDao1.insert(det);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch bdetk
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}

		
		
		
		
		
		// Insertion d'un tableau d'objets DetailFacture
		System.out.println("Insertion tableau d'objets");
		
		Facture facture2 = new Facture();
		facture2.setIdFacture(2);
		
		Oeuvre oeuvre2 = new Oeuvre();
		oeuvre2.setIdOeuvre(2);
		
		DetailFacture det1 = new DetailFacture();		
		det1.setIdDetail(0);
		det1.setDateDetail(new Date());
		det1.setMontantComissionDetail(4556.55);
		det1.setMontantTvaDetail(5645);
		det1.setPrixHtvaDetail(5656);
		det1.setOeuvre(oeuvre2);
		det1.setFacture(facture2);		
			
		
		
		Facture facture3 = new Facture();
		facture3.setIdFacture(3);
		
		Oeuvre oeuvre3 = new Oeuvre();
		oeuvre3.setIdOeuvre(3);
		
		DetailFacture det2 = new DetailFacture();
		det2.setIdDetail(0);
		det2.setDateDetail(new Date());
		det2.setMontantComissionDetail(4556.55);
		det2.setMontantTvaDetail(5645);
		det2.setPrixHtvaDetail(5656);
		det2.setOeuvre(oeuvre3);
		det2.setFacture(facture3);		
		
		
		
		Facture facture4 = new Facture();
		facture4.setIdFacture(4);
		
		Oeuvre oeuvre4 = new Oeuvre();
		oeuvre4.setIdOeuvre(4);
		
		DetailFacture det3 = new DetailFacture();
		det3.setIdDetail(0);
		det3.setDateDetail(new Date());
		det3.setMontantComissionDetail(4556.55);
		det3.setMontantTvaDetail(5645);
		det3.setPrixHtvaDetail(5656);
		det3.setOeuvre(oeuvre4);
		det3.setFacture(facture4);		
		
		
		
		DetailFacture [] tabdet = new DetailFacture [3];
		
		tabdet[0]=det1;
		tabdet[1]=det2;
		tabdet[2]=det3;
		
		
		
		DetailFactureDao detDao2 = new DetailFactureDao(connection);
				
		try {
			detDao2.insertTabObj(tabdet);
			System.out.println("Insertion réussie");	
		} catch (MyException e) {
			// TODO Auto-generated catch bdetk
			//e.printStackTrace();
			System.out.println("Echec de l'insertion du tableau d'objets DetailFacture");
		}
				
		
			
			
			
			
		// Insertion d'une Collection d'objets DetailFacture
		System.out.println("Insertion collection d'objets");
		
		Facture facture5 = new Facture();
		facture5.setIdFacture(6);
		
		Oeuvre oeuvre5 = new Oeuvre();
		oeuvre5.setIdOeuvre(6);
		
		DetailFacture det4 = new DetailFacture();
		det4.setIdDetail(0);
		det4.setDateDetail(new Date());
		det4.setMontantComissionDetail(4556.55);
		det4.setMontantTvaDetail(5645);
		det4.setPrixHtvaDetail(5656);
		det4.setOeuvre(oeuvre5);
		det4.setFacture(facture5);		
		
		
		
		Facture facture6 = new Facture();
		facture6.setIdFacture(7);
		
		Oeuvre oeuvre6 = new Oeuvre();
		oeuvre6.setIdOeuvre(7);
		
		DetailFacture det5 = new DetailFacture();
		det5.setIdDetail(0);
		det5.setDateDetail(new Date());
		det5.setMontantComissionDetail(4556.55);
		det5.setMontantTvaDetail(5645);
		det5.setPrixHtvaDetail(5656);
		det5.setOeuvre(oeuvre6);
		det5.setFacture(facture6);		
		
		Facture facture7 = new Facture();
		facture7.setIdFacture(8);
		
		Oeuvre oeuvre7 = new Oeuvre();
		oeuvre7.setIdOeuvre(8);
		
		DetailFacture det6 = new DetailFacture();
		det6.setIdDetail(0);
		det6.setDateDetail(new Date());
		det6.setMontantComissionDetail(4556.55);
		det6.setMontantTvaDetail(5645);
		det6.setPrixHtvaDetail(5656);
		det6.setOeuvre(oeuvre7);
		det6.setFacture(facture7);		
		
		
	
		List<DetailFacture> maliste = new ArrayList<DetailFacture>(); 

		maliste.add(det4); 
		maliste.add(det5);
		maliste.add(det6);
			
			
		DetailFactureDao detDao3 = new DetailFactureDao(connection);
			
		try {
			detDao3.insertCollObj(maliste);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch bdetk
			//e.printStackTrace();
			System.out.println("Echec de l'insertion d'une Collection d'objets DetailFacture");
		}
				
			
		

		
		// Mise à jour d'un objet DetailFacture
		System.out.println("Mise à jour d'un objet");
		
		Facture facture8 = new Facture();
		facture8.setIdFacture(9);
		
		DetailFacture det7 = new DetailFacture();
		det7.setIdDetail(2);
		det7.setDateDetail(new Date());
		det7.setMontantComissionDetail(4556.55);
		det7.setMontantTvaDetail(5645);
		det7.setPrixHtvaDetail(5656);
		det7.setFacture(facture8);
			
		DetailFactureDao detDao4 = new DetailFactureDao(connection);
			
		try {
			detDao4.update(det7);
			System.out.println("Mise à jour réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch bdetk
			//e.printStackTrace();
			System.out.println("Echec de la mise à jour");
		}

			
		
		
		
		
		
		// Suppression d'un objet DetailFacture
		System.out.println("Supression d'un objet");
		
		DetailFacture det8 = new DetailFacture();
		det8.setIdDetail(6);
					
		DetailFactureDao detDao5 = new DetailFactureDao(connection);
					
		try {
			detDao5.delete(det8);
			System.out.println("Supression réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch bdetk
			//e.printStackTrace();
			System.out.println("Echec de la supression de l'objet");
		}
					
				
				
			
			
		// Selection d'un objet DetailFacture
		System.out.println("Sélection d'un objet");
		
		DetailFactureDao detDao6 = new DetailFactureDao(connection);
		DetailFacture det9 = new DetailFacture();
			
		try {
			det9=detDao6.find(3);
			System.out.println("Objet sélectionné: "+ det9.getIdDetail() 
															+" "+ det9.getMontantComissionDetail());
		} catch (MyException e) {
			// TODO Auto-generated catch bdetk
			//e.printStackTrace();
			System.out.println("Echec de la selection de l'objet");
		}
			
		
		
		
			
		
		
		
		// Selection de tous les objets DetailFacture
		System.out.println("Sélection de tous les objets");
		
		DetailFactureDao detDao7 = new DetailFactureDao(connection);
		List<DetailFacture> listdet = new ArrayList<DetailFacture>();
			
		try {
			listdet=detDao7.findAll();
			
			StringBuffer chaine = new StringBuffer();
			for (DetailFacture detett:listdet){
				chaine.append(detett.toString()+"\n");
			}
			System.out.println(chaine);
			
		} catch (MyException e) {
			// TODO Auto-generated catch bdetk
			//e.printStackTrace();
			System.out.println("Echec de la sélection des tous les objets");
		}
		
		
		
		
		
	
		// Sélection des objets DetailFacture entre deux PK
		System.out.println("Sélection d'objets entre deux PK");
		
		DetailFactureDao detDao8 = new DetailFactureDao(connection);
		List<DetailFacture> listdet2 = new ArrayList<DetailFacture>();
		Integer a= new Integer(2);
		Integer b= new Integer(4);
	
		try {
			listdet2=detDao8.findBetween(a,b);
			
			StringBuffer chaine2 = new StringBuffer();
			for (DetailFacture detett:listdet2){
				chaine2.append(detett.toString()+"\n");
			}
			System.out.println(chaine2);
			
		} catch (MyException e) {
			// TODO Auto-generated catch bdetk
			//e.printStackTrace();
			System.out.println("Echec de la sélection des objets entre deux PK");
		}
		
		
		


			
		// Fermer la connection
		Singleton.shutdown();
			
	}
		
}
