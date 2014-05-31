package main;

import javax.swing.JFrame;

import outilConnect.Singleton;
import fenetres.Accueil;


public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		JFrame accueil = new Accueil();
		accueil.setVisible(true);	
			
		// Fermer la connection
		Singleton.shutdown();
	}
}

