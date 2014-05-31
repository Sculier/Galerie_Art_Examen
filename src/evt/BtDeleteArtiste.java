package evt;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import outil.UpdateCombo;
import outilDAO.MyException;
import pojo.Artiste;
import pojo.Adresse;
import pojo.Pays;
import pojoDAO.ArtisteDao;
import composant.JtxtfDial;
import composant.PnlOngArtiste;


public class BtDeleteArtiste implements ActionListener {

	private JPanel panelref=null;
	
	public BtDeleteArtiste(JPanel panelref){
		this.panelref=panelref;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		if(((PnlOngArtiste)panelref).isDrapeau()==true){
			int retourJOptionPane = JOptionPane.showConfirmDialog (null, 
					"Voulez-vous supprimer cet artiste?","Demande de confirmation", 
				 										JOptionPane.YES_NO_OPTION );
			if (retourJOptionPane==0){
					
					// Objet sélectionné pour supression
					//((PnlOngArtiste) panelref).getArtselect();
					ArtisteDao artDao = new ArtisteDao(((PnlOngArtiste)panelref).getConnection());
					try {
						artDao.delete(((PnlOngArtiste) panelref).getArtselect());
						System.out.println("Supression réussie");
					} catch (MyException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						System.out.println("Echec de la supression de l'objet");
					}
					
					// Mise à jour de la combo
					UpdateCombo fctupdate = new UpdateCombo(panelref);
					fctupdate.fctupdatecombo();
					
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
					
					JOptionPane.showMessageDialog(null, "Votre suppression a été effectuée");
			} 
		}else{}
		
		
	}// Fin actionPerformed


		
	
}

	