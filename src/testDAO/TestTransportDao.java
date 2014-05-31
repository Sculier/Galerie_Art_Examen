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
import pojo.Transport;
import pojoDAO.TransportDao;



public class TestTransportDao {

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

		
				
		// Insertion d'un objet Transport
		Transport transp1 = new Transport();
		transp1.setIdTransport(new Integer(0));
		transp1.setDateDepot(new Date());
		transp1.setDateRetour(new Date());
		transp1.setHeureDepot("InsertTransport fermeture");
		transp1.setHeureRetour("InsertTransport Ouverture");
		
			
		TransportDao transpDao1 = new TransportDao(connection);
			
		try {
			transpDao1.insert(transp1);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}

		
		// Insertion d'un tableau d'objets Transport
		Transport t1 = new Transport();
		t1.setIdTransport(new Integer(0));
		t1.setDateDepot(new Date());
		t1.setDateRetour(new Date());
		t1.setHeureDepot("Fermeture 1");
		t1.setHeureRetour("Ouverture 1");
		
		Transport t2 = new Transport();
		t2.setIdTransport(new Integer(0));
		t2.setDateDepot(new Date());
		t2.setDateRetour(new Date());
		t2.setHeureDepot("Fermeture 2");
		t2.setHeureRetour("Ouverture 2");
		
		
		Transport t3 = new Transport();
		t3.setIdTransport(new Integer(0));
		t3.setDateDepot(new Date());
		t3.setDateRetour(new Date());
		t3.setHeureDepot("Fermeture 3");
		t3.setHeureRetour("Ouverture 3");
		
		
		Transport [] tabtransp = new Transport [3];
		
		tabtransp[0]=t1;
		tabtransp[1]=t2;
		tabtransp[2]=t3;
			
			
		TransportDao transpDao2 = new TransportDao(connection);
				
		try {
			transpDao2.insertTabObj(tabtransp);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion du tableau d'objets Transport");
		}
				
			
			
			
			
			
		// Insertion d'une Collection d'objets Transport
		Transport t4 = new Transport();
		t4.setIdTransport(new Integer(0));
		t4.setDateDepot(new Date());
		t4.setDateRetour(new Date());
		t4.setHeureDepot("Fermeture 4");
		t4.setHeureRetour("Ouverture 4");
		

		Transport t5 = new Transport();
		t5.setIdTransport(new Integer(0));
		t5.setDateDepot(new Date());
		t5.setDateRetour(new Date());
		t5.setHeureDepot("Fermeture 5");
		t5.setHeureRetour("Ouverture 5");
		
	
		Transport t6 = new Transport();
		t6.setIdTransport(new Integer(0));
		t6.setDateDepot(new Date());
		t6.setDateRetour(new Date());
		t6.setHeureDepot("Fermeture 6");
		t6.setHeureRetour("Ouverture 6");
		
			
			
		List<Transport> maliste = new ArrayList<Transport>(); 

		maliste.add(t4); 
		maliste.add(t5);
		maliste.add(t6);
			
			
		TransportDao transpDao3 = new TransportDao(connection);
			
		try {
			transpDao3.insertCollObj(maliste);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion d'une Collection d'objets Transport");
		}
				
			
		

		
		// Mise à jour d'un objet Transport
		Transport t7 = new Transport();
		t7.setIdTransport(new Integer(4));
		t7.setDateDepot(new Date());
		t7.setDateRetour(new Date());
		t7.setHeureDepot("Fermeture Update");
		t7.setHeureRetour("Ouverture Update");
		
		
		TransportDao transpDao4 = new TransportDao(connection);
			
		try {
			transpDao4.update(t7);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la mise à jour");
		}

		
		
		
		// Suppression d'un objet Transport
		Transport h8 = new Transport();
		h8.setIdTransport(5);
					
		TransportDao transpDao5 = new TransportDao(connection);
					
		try {
			transpDao5.delete(h8);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la supression de l'objet");
		}
					
				
				
			
			
		// Selection d'un objet Transport
		TransportDao transpDao6 = new TransportDao(connection);
		Transport h9 = new Transport();
			
		try {
			h9=transpDao6.find(2);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la selection de l'objet");
		}
			
		System.out.println(h9.getIdTransport() +" " + h9.getDateDepot());
		
		
			
		
		
		
		// Selection de tous les objets Transport
		TransportDao transpDao7 = new TransportDao(connection);
		List<Transport> listtransp = new ArrayList<Transport>();
			
		try {
			listtransp=transpDao7.findAll();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des tous les objets");
		}
			
		StringBuffer chaine = new StringBuffer();
			
		for (Transport h:listtransp){
			chaine.append(h.toString()+"\n");
		}
		System.out.println(chaine);
		
		
		
	
		// Sélection des objets Transport entre deux PK
		TransportDao transpDao8 = new TransportDao(connection);
		List<Transport> listtransp2 = new ArrayList<Transport>();
		Integer a= new Integer(1);
		Integer b= new Integer(7);
	
		try {
			listtransp2=transpDao8.findBetween(a,b);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des objets entre deux PK");
		}
		
		StringBuffer chaine2 = new StringBuffer();
		
		for (Transport h:listtransp2){
			chaine2.append(h.toString()+"\n");
		}
		System.out.println(chaine2);


			
		// Fermer la connection
		Singleton.shutdown();
			
	}
		
}
