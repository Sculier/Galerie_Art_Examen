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
import pojo.Newsletter;
import pojoDAO.NewsletterDao;



public class TestNewsletterDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Properties props = null;
		String chemin = "fichiers/Config.properties";
		
		try {
			props= OutilLectParamConnect.recupererProperties(chemin);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch bnewslk
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch bnewslk
			e.printStackTrace();
		}
		
	
		Connection connection = null;
		connection = Singleton.getInstance(props);

		
				
		// Insertion d'un objet Newsletter
		System.out.println("Insertion un objet");
		
		Newsletter newsl = new Newsletter();
		newsl.setIdNewsletter(new Integer(0));
		newsl.setSujet("Sujet");
		newsl.setContenu("Contenu");
		newsl.setDateEnvoi(new Date());
	
		
		NewsletterDao newslDao1 = new NewsletterDao(connection);
			
		try {
			newslDao1.insert(newsl);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch bnewslk
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}

		
		
		
		
		
		// Insertion d'un tableau d'objets Newsletter
		System.out.println("Insertion tableau d'objets");
		
	
		Newsletter newsl1 = new Newsletter();		
		newsl1.setIdNewsletter(new Integer(0));
		newsl1.setSujet("Sujet");
		newsl1.setContenu("Contenu");
		newsl1.setDateEnvoi(new Date());
	
		
		Newsletter newsl2 = new Newsletter();
		newsl2.setIdNewsletter(new Integer(0));
		newsl2.setSujet("Sujet");
		newsl2.setContenu("Contenu");
		newsl2.setDateEnvoi(new Date());
	
		
		Newsletter newsl3 = new Newsletter();
		newsl3.setIdNewsletter(new Integer(0));
		newsl3.setSujet("Sujet");
		newsl3.setContenu("Contenu");
		newsl3.setDateEnvoi(new Date());
	
		
		Newsletter [] tabnewsl = new Newsletter [3];
		
		tabnewsl[0]=newsl1;
		tabnewsl[1]=newsl2;
		tabnewsl[2]=newsl3;
		
		
		
		NewsletterDao newslDao2 = new NewsletterDao(connection);
				
		try {
			newslDao2.insertTabObj(tabnewsl);
			System.out.println("Insertion réussie");	
		} catch (MyException e) {
			// TODO Auto-generated catch bnewslk
			//e.printStackTrace();
			System.out.println("Echec de l'insertion du tableau d'objets Newsletter");
		}
				
		
			
			
			
			
		// Insertion d'une Collection d'objets Newsletter
		System.out.println("Insertion collection d'objets");
	
		Newsletter newsl4 = new Newsletter();
		newsl4.setIdNewsletter(new Integer(0));
		newsl4.setSujet("Sujet");
		newsl4.setContenu("Contenu");
		newsl4.setDateEnvoi(new Date());
	
		
		Newsletter newsl5 = new Newsletter();
		newsl5.setIdNewsletter(new Integer(0));
		newsl5.setSujet("Sujet");
		newsl5.setContenu("Contenu");
		newsl5.setDateEnvoi(new Date());
		
	
		Newsletter newsl6 = new Newsletter();
		newsl6.setIdNewsletter(new Integer(0));
		newsl6.setSujet("Sujet");
		newsl6.setContenu("Contenu");
		newsl6.setDateEnvoi(new Date());
		
		
	
		List<Newsletter> maliste = new ArrayList<Newsletter>(); 

		maliste.add(newsl4); 
		maliste.add(newsl5);
		maliste.add(newsl6);
			
			
		NewsletterDao newslDao3 = new NewsletterDao(connection);
			
		try {
			newslDao3.insertCollObj(maliste);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch bnewslk
			//e.printStackTrace();
			System.out.println("Echec de l'insertion d'une Collection d'objets Newsletter");
		}
				
			
		

		
		// Mise à jour d'un objet Newsletter
		System.out.println("Mise à jour d'un objet");
		
		Newsletter newsl7 = new Newsletter();
		newsl7.setIdNewsletter(new Integer(2));
		newsl7.setSujet("Sujet");
		newsl7.setContenu("Contenu");
		newsl7.setDateEnvoi(new Date());
		
			
		NewsletterDao newslDao4 = new NewsletterDao(connection);
			
		try {
			newslDao4.update(newsl7);
			System.out.println("Mise à jour réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch bnewslk
			//e.printStackTrace();
			System.out.println("Echec de la mise à jour");
		}

			
		
		
		
		
		
		// Suppression d'un objet Newsletter
		System.out.println("Supression d'un objet");
		
		Newsletter newsl8 = new Newsletter();
		newsl8.setIdNewsletter(new Integer(5));
					
		NewsletterDao newslDao5 = new NewsletterDao(connection);
					
		try {
			newslDao5.delete(newsl8);
			System.out.println("Supression réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch bnewslk
			//e.printStackTrace();
			System.out.println("Echec de la supression de l'objet");
		}
					
				
				
			
			
		// Selection d'un objet Newsletter
		System.out.println("Sélection d'un objet");
		
		NewsletterDao newslDao6 = new NewsletterDao(connection);
		Newsletter newsl9 = new Newsletter();
			
		try {
			newsl9=newslDao6.find(new Integer(3));
			System.out.println("Objet sélectionné: "+ newsl9.getIdNewsletter() 
															+" "+ newsl9.getContenu());
		} catch (MyException e) {
			// TODO Auto-generated catch bnewslk
			//e.printStackTrace();
			System.out.println("Echec de la selection de l'objet");
		}
			
		
		
		
			
		
		
		
		// Selection de tous les objets Newsletter
		System.out.println("Sélection de tous les objets");
		
		NewsletterDao newslDao7 = new NewsletterDao(connection);
		List<Newsletter> listnewsl = new ArrayList<Newsletter>();
			
		try {
			listnewsl=newslDao7.findAll();
			
			StringBuffer chaine = new StringBuffer();
			for (Newsletter newslett:listnewsl){
				chaine.append(newslett.toString()+"\n");
			}
			System.out.println(chaine);
			
		} catch (MyException e) {
			// TODO Auto-generated catch bnewslk
			//e.printStackTrace();
			System.out.println("Echec de la sélection des tous les objets");
		}
		
		
		
		
		
	
		// Sélection des objets Newsletter entre deux PK
		System.out.println("Sélection d'objets entre deux PK");
		
		NewsletterDao newslDao8 = new NewsletterDao(connection);
		List<Newsletter> listnewsl2 = new ArrayList<Newsletter>();
		Integer a= new Integer(2);
		Integer b= new Integer(4);
	
		try {
			listnewsl2=newslDao8.findBetween(a,b);
			
			StringBuffer chaine2 = new StringBuffer();
			for (Newsletter newslett:listnewsl2){
				chaine2.append(newslett.toString()+"\n");
			}
			System.out.println(chaine2);
			
		} catch (MyException e) {
			// TODO Auto-generated catch bnewslk
			//e.printStackTrace();
			System.out.println("Echec de la sélection des objets entre deux PK");
		}
		
		
		


			
		// Fermer la connection
		Singleton.shutdown();
			
	}
		
}
