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


public class BtNullSearchArtiste implements ActionListener {

	private JPanel panelref=null;
	
	public BtNullSearchArtiste(JPanel panelref){
		this.panelref=panelref;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		
		((PnlOngArtiste) panelref).getJtxfnom().setText(null);
		((PnlOngArtiste) panelref).getJtxfprenom().setText(null);
		((PnlOngArtiste) panelref).getJtxfemail().setText(null);
		((PnlOngArtiste) panelref).getJtxftelephone().setText(null);
		((PnlOngArtiste) panelref).getJtxfrue().setText(null);
		((PnlOngArtiste) panelref).getJtxfcodepostal().setText(null);
		((PnlOngArtiste) panelref).getJtxfnumero().setText(null);
		((PnlOngArtiste) panelref).getJtxflocalite().setText(null);
		((PnlOngArtiste) panelref).getJtxfpays().setText(null);
		((PnlOngArtiste) panelref).setDrapeau(false);
		//Tester la valeur du drapeau
		//System.out.println(""+((PnlOngArtiste)panelref).isDrapeau());
		((PnlOngArtiste) panelref).getCombosearch().setSelectedIndex(-1);
		
	}// Fin actionPerformed


		
	
}

	