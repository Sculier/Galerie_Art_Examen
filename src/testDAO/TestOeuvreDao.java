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
import pojo.Artiste;
import pojo.Courant;
import pojo.Oeuvre;
import pojo.Categorie;
import pojo.Technique;
import pojoDAO.OeuvreDao;



public class TestOeuvreDao {

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

		
				
		// Insertion d'un objet Oeuvre
		System.out.println("Insertion un objet");
		
		
		Artiste artiste1 = new Artiste();
		artiste1.setIdArtiste(1);
		
		Categorie categorie1 = new Categorie();
		categorie1.setIdCategorie(1);
		
		Courant courant1 = new Courant();
		courant1.setIdCourant(1);
		
		
		Technique technique1 = new Technique();
		technique1.setIdTechnique(1);
				
		
		Oeuvre oeuv = new Oeuvre();
		oeuv.setIdOeuvre(new Integer(0));
		oeuv.setDescription("Description");
		oeuv.setHauteur(150);
		oeuv.setLargeur(100);
		oeuv.setNomOeuvre("Nom oeuvre");
		oeuv.setPourcentageComission(10.20f);
		oeuv.setPrixAffiche(2522);
		oeuv.setPrixNegocie(64656);
		oeuv.setPrixPlancher(1255);
		oeuv.setVendu((byte)1);
		oeuv.setArtiste(artiste1);
		oeuv.setCategorie(categorie1);		
		oeuv.setCourant(courant1);	
		oeuv.setTechnique(technique1);	
		oeuv.setTauxTva(13.2f);
				
		
		OeuvreDao oeuvDao1 = new OeuvreDao(connection);
			
		try {
			oeuvDao1.insert(oeuv);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}

		
		
		
		
		
		// Insertion d'un tableau d'objets Oeuvre
		System.out.println("Insertion tableau d'objets");
		
		Oeuvre oeuv1 = new Oeuvre();
		oeuv1.setIdOeuvre(new Integer(0));
		oeuv1.setDescription("Description 1");
		oeuv1.setHauteur(150);
		oeuv1.setLargeur(100);
		oeuv1.setNomOeuvre("Nom oeuvre 1");
		oeuv1.setPourcentageComission(10.20f);
		oeuv1.setPrixAffiche(2522);
		oeuv1.setPrixNegocie(4556);
		oeuv1.setPrixPlancher(1255);
		oeuv1.setVendu((byte)1);
		oeuv1.setArtiste(artiste1);
		oeuv1.setCategorie(categorie1);		
		oeuv1.setCourant(courant1);	
		oeuv1.setTechnique(technique1);	
		oeuv1.setTauxTva(13.2f);
		
		Oeuvre oeuv2 = new Oeuvre();
		oeuv2.setIdOeuvre(new Integer(0));
		oeuv2.setDescription("Description 2");
		oeuv2.setHauteur(150);
		oeuv2.setLargeur(100);
		oeuv2.setNomOeuvre("Nom oeuvre 2");
		oeuv2.setPourcentageComission(10.20f);
		oeuv2.setPrixAffiche(2522);
		oeuv2.setPrixNegocie(4556);
		oeuv2.setPrixPlancher(1255);
		oeuv2.setVendu((byte)1);
		oeuv2.setArtiste(artiste1);
		oeuv2.setCategorie(categorie1);		
		oeuv2.setCourant(courant1);		
		oeuv2.setTechnique(technique1);	
		oeuv2.setTauxTva(13.2f);
		
		Oeuvre oeuv3 = new Oeuvre();
		oeuv3.setIdOeuvre(new Integer(0));
		oeuv3.setDescription("Description 2");
		oeuv3.setHauteur(150);
		oeuv3.setLargeur(100);
		oeuv3.setNomOeuvre("Nom oeuvre 2");
		oeuv3.setPourcentageComission(10.20f);
		oeuv3.setPrixAffiche(2522);
		oeuv3.setPrixNegocie(4556);
		oeuv3.setPrixPlancher(1255);
		oeuv3.setVendu((byte)1);
		oeuv3.setArtiste(artiste1);
		oeuv3.setCategorie(categorie1);		
		oeuv3.setCourant(courant1);	
		oeuv3.setTechnique(technique1);	
		oeuv3.setTauxTva(13.2f);
		
		Oeuvre [] taboeuv = new Oeuvre [3];
		
		taboeuv[0]=oeuv1;
		taboeuv[1]=oeuv2;
		taboeuv[2]=oeuv3;
		
		
		
		OeuvreDao oeuvDao2 = new OeuvreDao(connection);
				
		try {
			oeuvDao2.insertTabObj(taboeuv);
			System.out.println("Insertion réussie");	
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion du tableau d'objets Oeuvre");
		}
				
		
			
			
			
			
		// Insertion d'une Collection d'objets Oeuvre
		System.out.println("Insertion collection d'objets");
		
		
		Oeuvre oeuv4 = new Oeuvre();
		oeuv4.setIdOeuvre(new Integer(0));
		oeuv4.setDescription("Description 4");
		oeuv4.setHauteur(150);
		oeuv4.setLargeur(100);
		oeuv4.setNomOeuvre("Nom oeuvre 4");
		oeuv4.setPourcentageComission(10.20f);
		oeuv4.setPrixAffiche(2522);
		oeuv4.setPrixNegocie(4556);
		oeuv4.setPrixPlancher(1255);
		oeuv4.setVendu((byte)1);
		oeuv4.setArtiste(artiste1);
		oeuv4.setCategorie(categorie1);		
		oeuv4.setCourant(courant1);	
		oeuv4.setTechnique(technique1);	
		oeuv4.setTauxTva(13.2f);
		
		Oeuvre oeuv5 = new Oeuvre();
		oeuv5.setIdOeuvre(new Integer(0));
		oeuv5.setDescription("Description 5");
		oeuv5.setHauteur(150);
		oeuv5.setLargeur(100);
		oeuv5.setNomOeuvre("Nom oeuvre 5");
		oeuv5.setPourcentageComission(10.20f);
		oeuv5.setPrixAffiche(2522);
		oeuv5.setPrixNegocie(4556);
		oeuv5.setPrixPlancher(1255);
		oeuv5.setVendu((byte)1);
		oeuv5.setArtiste(artiste1);
		oeuv5.setCategorie(categorie1);		
		oeuv5.setCourant(courant1);	
		oeuv5.setTechnique(technique1);	
		oeuv5.setTauxTva(13.2f);	
		
		Oeuvre oeuv6 = new Oeuvre();
		oeuv6.setIdOeuvre(new Integer(0));
		oeuv6.setDescription("Description 6");
		oeuv6.setHauteur(150);
		oeuv6.setLargeur(100);
		oeuv6.setNomOeuvre("Nom oeuvre 6");
		oeuv6.setPourcentageComission(10.20f);
		oeuv6.setPrixAffiche(2522);
		oeuv6.setPrixNegocie(4556);
		oeuv6.setPrixPlancher(1255);
		oeuv6.setVendu((byte)1);
		oeuv6.setArtiste(artiste1);
		oeuv6.setCategorie(categorie1);		
		oeuv6.setCourant(courant1);	
		oeuv6.setTechnique(technique1);	
		oeuv6.setTauxTva(13.2f);
	
		List<Oeuvre> maliste = new ArrayList<Oeuvre>(); 

		maliste.add(oeuv4); 
		maliste.add(oeuv5);
		maliste.add(oeuv6);
			
			
		OeuvreDao oeuvDao3 = new OeuvreDao(connection);
			
		try {
			oeuvDao3.insertCollObj(maliste);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion d'une Collection d'objets Oeuvre");
		}
				

		

		
		// Mise à jour d'un objet Oeuvre
		System.out.println("Mise à jour d'un objet");
		
		Oeuvre oeuv7 = new Oeuvre();
		oeuv7.setIdOeuvre(new Integer(2));
		oeuv7.setDescription("Description update");
		oeuv7.setHauteur(150);
		oeuv7.setLargeur(100);
		oeuv7.setNomOeuvre("Nom oeuvre update");
		oeuv7.setPourcentageComission(10.20f);
		oeuv7.setPrixAffiche(2522);
		oeuv7.setPrixNegocie(4556);
		oeuv7.setPrixPlancher(1255);
		oeuv7.setVendu((byte)1);
		oeuv7.setArtiste(artiste1);
		oeuv7.setCategorie(categorie1);		
		oeuv7.setCourant(courant1);	
		oeuv7.setTechnique(technique1);	
		oeuv7.setTauxTva(13.2f);
			
		OeuvreDao oeuvDao4 = new OeuvreDao(connection);
			
		try {
			oeuvDao4.update(oeuv7);
			System.out.println("Mise à jour réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la mise à jour");
		}

			
		
		
		
		
		
		// Suppression d'un objet Oeuvre
		System.out.println("Supression d'un objet");
		
		Oeuvre oeuv8 = new Oeuvre();
		oeuv8.setIdOeuvre(5);
					
		OeuvreDao oeuvDao5 = new OeuvreDao(connection);
					
		try {
			oeuvDao5.delete(oeuv8);
			System.out.println("Supression réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la supression de l'objet");
		}
					
				
				
			
			
		// Selection d'un objet Oeuvre
		System.out.println("Sélection d'un objet");
		
		OeuvreDao oeuvDao6 = new OeuvreDao(connection);
		Oeuvre oeuv9 = new Oeuvre();
			
		try {
			oeuv9=oeuvDao6.find(3);
			System.out.println("Objet sélectionné: "+ oeuv9.getIdOeuvre() 
														+" "+ oeuv9.getNomOeuvre());
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la selection de l'objet");
		}
			
		
		
		
			
		
		
		
		// Selection de tous les objets Oeuvre
		System.out.println("Sélection de tous les objets");
		
		OeuvreDao oeuvDao7 = new OeuvreDao(connection);
		List<Oeuvre> listoeuv = new ArrayList<Oeuvre>();
			
		try {
			listoeuv=oeuvDao7.findAll();
			
			StringBuffer chaine = new StringBuffer();
			for (Oeuvre oeuvr:listoeuv){
				chaine.append(oeuvr.toString()+"\n");
			}
			System.out.println(chaine);
			
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des tous les objets");
		}
		
		
		
		
		
	
		// Sélection des objets Oeuvre entre deux PK
		System.out.println("Sélection d'objets entre deux PK");
		OeuvreDao oeuvDao8 = new OeuvreDao(connection);
		List<Oeuvre> listoeuv2 = new ArrayList<Oeuvre>();
		Integer a= new Integer(2);
		Integer b= new Integer(4);
	
		try {
			listoeuv2=oeuvDao8.findBetween(a,b);
			
			StringBuffer chaine2 = new StringBuffer();
			for (Oeuvre oeuvr:listoeuv2){
				chaine2.append(oeuvr.toString()+"\n");
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
