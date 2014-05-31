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
import outilManytoMany.OutilReception;
import pojo.Client;
import pojo.Newsletter;
import pojoDAO.NewsletterDao;




public class TestReception {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Properties props = null;
		String chemin = "fichiers/Config.properties";
		
		try {
			props= OutilLectParamConnect.recupererProperties(chemin);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch bnewsk
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch bnewsk
			e.printStackTrace();
		}
		
	
		Connection connection = null;
		connection = Singleton.getInstance(props);

		
		
		// Insertion d'un objet 
		System.out.println("Insertion un objet");
		
		Newsletter news1 = new Newsletter();
		news1.setIdNewsletter(1);
		
		Client cl1 = new Client();
		cl1.setIdClient(1);
		

	
		
		OutilReception recep1 = new OutilReception(connection);
			
		try {
			recep1.insert(news1, cl1);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch bnewsk
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}

		
		
		
		// Insertion d'un tableau d'objets
		System.out.println("Insertion d'un tableau d'objets");
		
		Newsletter news2 = new Newsletter();
		news2.setIdNewsletter(2);
		
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
					
					
		OutilReception recep2 = new OutilReception(connection);
						
		try {
			recep2.insertTabObjcl(news2, tabcl);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch bnewsk
			//e.printStackTrace();
			System.out.println("Echec de l'insertion du tableau d'objets client");
		}
						
					
					
					
					
				
		// Insertion d'une Collection d'objets 
		System.out.println("Insertion d'une collection d'objets");
		
		Newsletter news3 = new Newsletter();
		news3.setIdNewsletter(3);
		
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
					
					
		OutilReception recep3 = new OutilReception(connection);
					
		try {
			recep3.insertCollObjcl(news3, maliste);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch bnewsk
			//e.printStackTrace();
			System.out.println("Echec de l'insertion d'une Collection d'objets client");
		}
				
		
		
		

		// Suppression d'un objet 
		System.out.println("Supression d'un objet");
		
		Newsletter news4 = new Newsletter();
		news4.setIdNewsletter(1);
					
		OutilReception recep4 = new OutilReception(connection);
					
		try {
			recep4.delete(news4);
			System.out.println("Supression réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch bnewsk
			//e.printStackTrace();
			System.out.println("Echec de la supression de l'objet");
		}
		
	
		
		
		// Mise à jour d'un objet Reception
		System.out.println("Mise à jour d'un objet");
		
		Newsletter news5 = new Newsletter();
		news5.setIdNewsletter(1);
		
		
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
					
		
		
		OutilReception recep5 = new OutilReception(connection);
			
		try {
			recep5.update(news5, maliste2);
			System.out.println("Mise à jour réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la mise à jour");
		}
		
	
		
		



		
		// Selection de tous les objets 
		System.out.println("Sélection des objets:");
		
		Newsletter news6 = new Newsletter();
		news6.setIdNewsletter(2);
		
		Newsletter news7 = new Newsletter();
		
		NewsletterDao newsDao = new NewsletterDao(connection);
		List<Client> listcl = new ArrayList<Client>();
		OutilReception recep6 = new OutilReception(connection);
		
		try {
			news7=newsDao.find(news6.getIdNewsletter());
			System.out.println("La newsletter: "+ news7.getIdNewsletter() +" "
																		+ news7.getContenu());
			
			listcl=recep6.find(news6);
		} catch (MyException e) {
			// TODO Auto-generated catch bnewsk
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
