package evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

import pojo.Artiste;
import pojo.Adresse;
import pojo.Pays;
import composant.JtxtfDial;
import composant.PnlOngArtiste;


public class BtSearchArtiste implements ActionListener {

	private JPanel panelref=null;
	private Artiste artselectcombo = null;
	
	public BtSearchArtiste(JPanel panelref){
		this.panelref=panelref;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		if(((PnlOngArtiste) panelref).getCombosearch().getSelectedIndex()!= -1){
		
			artselectcombo = new Artiste();
			artselectcombo = (Artiste)((PnlOngArtiste) panelref).getModelcombosearch().getSelectedItem();
			((PnlOngArtiste) panelref).setArtselect(artselectcombo);
		
			((PnlOngArtiste) panelref).getJtxfnom().setText(artselectcombo.getNom());
			((PnlOngArtiste) panelref).getJtxfprenom().setText(artselectcombo.getPrenom());
			((PnlOngArtiste) panelref).getJtxfemail().setText(artselectcombo.getEmail());
			((PnlOngArtiste) panelref).getJtxftelephone().setText(artselectcombo.getTelephone());
			((PnlOngArtiste) panelref).getJtxfrue().setText(((Adresse)((Artiste)artselectcombo).getAdresse()).getRue());
			((PnlOngArtiste) panelref).getJtxfcodepostal().setText(((Adresse)((Artiste)artselectcombo).getAdresse()).getCodePostal());
			((PnlOngArtiste) panelref).getJtxfnumero().setText(((Adresse)((Artiste)artselectcombo).getAdresse()).getNumero());
			((PnlOngArtiste) panelref).getJtxflocalite().setText(((Adresse)((Artiste)artselectcombo).getAdresse()).getLocalite());
			((PnlOngArtiste) panelref).getJtxfpays().setText(((Pays)((Adresse)((Artiste)artselectcombo).getAdresse()).getPays()).getNom());
			((PnlOngArtiste) panelref).setDrapeau(true);
			((PnlOngArtiste) panelref).getCombosearch().setSelectedIndex(-1);
	
			/*	
				// Tester la valeur du drapeau
				System.out.println(""+((PnlOngArtiste)panelref).isDrapeau());
			 */
		
			/*
				// Tester l'affichage et la récupération d'un nombre dans un JTextField
	
				// Insérer un nombre dans un JTextField
				((PnlOngArtiste) panelref).getJtxfprenom().setText(String.valueOf(1.3));
				//Récupérer un nombre dans un JTextField
				double i;
				String str;
				str=((PnlOngArtiste) panelref).getJtxfprenom().getText().toString().trim();
				i=(double)Double.parseDouble(str); 
	
				System.out.println("Recupérer un 'int' dans un JTextField: " + i);
			 */
		}	
		
		
		
	}// Fin actionPerformed


	public Artiste getArtselectcombo() {
		return artselectcombo;
	}
	public void setArtselectcombo(Artiste artselectcombo) {
		this.artselectcombo = artselectcombo;
	}


}

	