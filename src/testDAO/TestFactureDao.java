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
import pojo.Facture;
import pojo.Client;
import pojoDAO.FactureDao;



public class TestFactureDao {

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

		
				
		// Insertion d'un objet Facture
		System.out.println("Insertion un objet");
		
		Client client1 = new Client();
		client1.setIdClient(1);
		
		
		Facture fact = new Facture();
		fact.setIdFacture(new Integer(0));
		fact.setDateVente(new Date());
		fact.setMontantComission(5662.02);
		fact.setMontantTva(8452.05);
		fact.setNumeroFacture("5GRDX56");
		fact.setPrixHtva(64541.45);
		fact.setClient(client1);		
		fact.setMontantTotal(8665.22);
		
		
		FactureDao factDao1 = new FactureDao(connection);
			
		try {
			factDao1.insert(fact);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion");
		}

		
		
		
		
		
		// Insertion d'un tableau d'objets Facture
		System.out.println("Insertion tableau d'objets");
	
			
		Facture fact1 = new Facture();
		fact1.setIdFacture(new Integer(0));
		fact1.setDateVente(new Date());
		fact1.setMontantComission(4552.02);
		fact1.setMontantTva(662.05);
		fact1.setNumeroFacture("POJ656");
		fact1.setPrixHtva(9981.45);
		fact1.setClient(client1);		
		fact1.setMontantTotal(8665.22);
		
		
		Facture fact2 = new Facture();
		fact2.setIdFacture(new Integer(0));
		fact2.setDateVente(new Date());
		fact2.setMontantComission(3022.02);
		fact2.setMontantTva(662.05);
		fact2.setNumeroFacture("RRE956");
		fact2.setPrixHtva(9275.45);
		fact2.setClient(client1);		
		fact2.setMontantTotal(8665.22);
			
		
		Facture fact3 = new Facture();
		fact3.setIdFacture(new Integer(0));
		fact3.setDateVente(new Date());
		fact3.setMontantComission(5559.02);
		fact3.setMontantTva(3652.05);
		fact3.setNumeroFacture("KKDR556");
		fact3.setPrixHtva(741.45);
		fact3.setClient(client1);		
		fact3.setMontantTotal(8665.22);
		
		
		Facture [] tabfact = new Facture [3];
		
		tabfact[0]=fact1;
		tabfact[1]=fact2;
		tabfact[2]=fact3;
		
/*		
		StringBuffer chaine2 = new StringBuffer();
		for (Facture factur:tabfact){
			chaine2.append(factur.toString()+"\n");
		}
		System.out.println(chaine2);
*/		
		
		
		FactureDao factDao2 = new FactureDao(connection);
				
		try {
			factDao2.insertTabObj(tabfact);
			System.out.println("Insertion réussie");	
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion du tableau d'objets Facture");
		}
				
		
			
			
			
			
		// Insertion d'une Collection d'objets Facture
		System.out.println("Insertion collection d'objets");
		
		
		Facture fact4 = new Facture();
		fact4.setIdFacture(new Integer(0));
		fact4.setDateVente(new Date());
		fact4.setMontantComission(9682.02);
		fact4.setMontantTva(7112.05);
		fact4.setNumeroFacture("MMR556");
		fact4.setPrixHtva(3331.45);
		fact4.setClient(client1);		
		fact4.setMontantTotal(8665.22);
		
		Facture fact5 = new Facture();
		fact5.setIdFacture(new Integer(0));
		fact5.setDateVente(new Date());
		fact5.setMontantComission(9663.02);
		fact5.setMontantTva(7862.05);
		fact5.setNumeroFacture("UUR556");
		fact5.setPrixHtva(3321.45);
		fact5.setClient(client1);		
		fact5.setMontantTotal(8665.22);
		
		Facture fact6 = new Facture();
		fact6.setIdFacture(new Integer(0));
		fact6.setDateVente(new Date());
		fact6.setMontantComission(4653.02);
		fact6.setMontantTva(8062.05);
		fact6.setNumeroFacture("DRNN62");
		fact6.setPrixHtva(2681.45);
		fact6.setClient(client1);			
		fact6.setMontantTotal(8665.22);
		
	
		List<Facture> maliste = new ArrayList<Facture>(); 

		maliste.add(fact4); 
		maliste.add(fact5);
		maliste.add(fact6);
			
			
		FactureDao factDao3 = new FactureDao(connection);
			
		try {
			factDao3.insertCollObj(maliste);
			System.out.println("Insertion réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de l'insertion d'une Collection d'objets Facture");
		}
				

		

		
		// Mise à jour d'un objet Facture
		System.out.println("Mise à jour d'un objet");
		
		Facture fact7 = new Facture();
		fact7.setIdFacture(new Integer(2));
		fact7.setDateVente(new Date());
		fact7.setMontantComission(9852.02);
		fact7.setMontantTva(94.05);
		fact7.setNumeroFacture("IIOR556 Update");
		fact7.setPrixHtva(2021.45);
		fact7.setClient(client1);		
		fact7.setMontantTotal(8665.22);
			
		FactureDao factDao4 = new FactureDao(connection);
			
		try {
			factDao4.update(fact7);
			System.out.println("Mise à jour réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la mise à jour");
		}

			
		
		
		
		
		
		// Suppression d'un objet Facture
		System.out.println("Supression d'un objet");
		
		Facture fact8 = new Facture();
		fact8.setIdFacture(5);
					
		FactureDao factDao5 = new FactureDao(connection);
					
		try {
			factDao5.delete(fact8);
			System.out.println("Supression réussie");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la supression de l'objet");
		}
					
				
				
			
			
		// Selection d'un objet Facture
		System.out.println("Sélection d'un objet");
		
		FactureDao factDao6 = new FactureDao(connection);
		Facture fact9 = new Facture();
			
		try {
			fact9=factDao6.find(3);
			System.out.println("Objet sélectionné: "+ fact9.getIdFacture() 
													+" "+ fact9.getMontantTva());
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la selection de l'objet");
		}
			
		
		
		
			
		
		
		
		// Selection de tous les objets Facture
		System.out.println("Sélection de tous les objets");
		
		FactureDao factDao7 = new FactureDao(connection);
		List<Facture> listfact = new ArrayList<Facture>();
			
		try {
			listfact=factDao7.findAll();
			
			StringBuffer chaine = new StringBuffer();
			for (Facture factur:listfact){
				chaine.append(factur.toString()+"\n");
			}
			System.out.println(chaine);
			
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des tous les objets");
		}
		
		
		
		
		
	
		// Sélection des objets Facture entre deux PK
		System.out.println("Sélection d'objets entre deux PK");
		FactureDao factDao8 = new FactureDao(connection);
		List<Facture> listfact2 = new ArrayList<Facture>();
		Integer a= new Integer(2);
		Integer b= new Integer(4);
	
		try {
			listfact2=factDao8.findBetween(a,b);
			
			StringBuffer chaine2 = new StringBuffer();
			for (Facture factur:listfact2){
				chaine2.append(factur.toString()+"\n");
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
