package outil;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import outilDAO.MyException;
import pojo.Artiste;
import pojoDAO.ArtisteDao;
import composant.PnlOngArtiste;

public class UpdateCombo extends JPanel{
	
	private JPanel panelref=null;
	
	public UpdateCombo(JPanel panelref){
		this.panelref=panelref;
	}

	public void fctupdatecombo(){
		
		// Mise à jour de la combo
		((PnlOngArtiste) panelref).getModelcombosearch().removeAllElements();
		ArtisteDao artupdatemodelcombo = new ArtisteDao(((PnlOngArtiste)panelref).getConnection());
		List<Artiste> list = new ArrayList<Artiste>();
	
		try {
			list=artupdatemodelcombo.findAll();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des tous les objets");
		}
	
		for (Artiste arttmp:list){
			((PnlOngArtiste) panelref).getModelcombosearch().addElement(arttmp);
		}
		((PnlOngArtiste) panelref).getCombosearch().setSelectedIndex(-1);
	
	}
	

}
