package evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import outil.UpdateCombo;
import outilDAO.MyException;
import pojo.Artiste;
import pojo.Adresse;
import pojo.Pays;
import pojoDAO.AdresseDao;
import pojoDAO.ArtisteDao;
import pojoDAO.PaysDao;
import composant.JtxtfDial;
import composant.PnlOngArtiste;


public class BtSaveArtiste implements ActionListener {

	private JPanel panelref=null;
	
	public BtSaveArtiste(JPanel panelref){
		this.panelref=panelref;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	
		if (((PnlOngArtiste) panelref).getJtxfnom().getText().toString().trim().isEmpty()==true
				|| ((PnlOngArtiste) panelref).getJtxfprenom().getText().toString().trim().isEmpty()==true
				|| ((PnlOngArtiste) panelref).getJtxftelephone().getText().toString().trim().isEmpty()==true
				|| ((PnlOngArtiste) panelref).getJtxfemail().getText().toString().trim().isEmpty()==true
				|| ((PnlOngArtiste) panelref).getJtxfrue().getText().toString().trim().isEmpty()==true
				|| ((PnlOngArtiste) panelref).getJtxfcodepostal().getText().toString().trim().isEmpty()==true
				|| ((PnlOngArtiste) panelref).getJtxfnumero().getText().toString().trim().isEmpty()==true
				|| ((PnlOngArtiste) panelref).getJtxflocalite().getText().toString().trim().isEmpty()==true
				|| ((PnlOngArtiste) panelref).getJtxfpays().getText().toString().trim().isEmpty()==true){
		
			JOptionPane.showMessageDialog(null, "Tous les champs n'ont pas été rempli");
		
		}else{
			if(((PnlOngArtiste) panelref).isDrapeau()==true){
					
				// Effectuer un update
				Pays paysupdate = new Pays();
				paysupdate.setNom(((PnlOngArtiste) panelref).getJtxfpays().getText().toString().trim());
				paysupdate.setIdPays(((Pays)((Adresse)((Artiste)((PnlOngArtiste) panelref).getArtselect()).getAdresse()).getPays()).getIdPays());
					
				PaysDao paysupdatedao = new PaysDao(((PnlOngArtiste)panelref).getConnection());
					
				try {
					paysupdatedao.updatePerso(paysupdate);
					//System.out.println("Mise à jour réussie");
				} catch (MyException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("Echec de la mise à jour");
				}
				
				Adresse adupdate = new Adresse();
				adupdate.setIdAdresse(((Adresse)((Artiste)((PnlOngArtiste) panelref).getArtselect()).getAdresse()).getIdAdresse());
				adupdate.setCodePostal(((PnlOngArtiste) panelref).getJtxfcodepostal().getText().toString().trim());
				adupdate.setLocalite(((PnlOngArtiste) panelref).getJtxflocalite().getText().toString().trim());
				adupdate.setNumero(((PnlOngArtiste) panelref).getJtxfnumero().getText().toString().trim());
				adupdate.setRue(((PnlOngArtiste) panelref).getJtxfrue().getText().toString().trim());	
					
				AdresseDao adDaoupdate = new AdresseDao(((PnlOngArtiste)panelref).getConnection());
					
				try {
					adDaoupdate.updatePerso(adupdate);
					//System.out.println("Mise à jour réussie");
				} catch (MyException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("Echec de la mise à jour");
				}
				
				Artiste artupdate = new Artiste();
				artupdate.setIdArtiste(((Artiste)((PnlOngArtiste) panelref).getArtselect()).getIdArtiste());
				artupdate.setNom(((PnlOngArtiste) panelref).getJtxfnom().getText().toString().trim());
				artupdate.setPrenom(((PnlOngArtiste) panelref).getJtxfprenom().getText().toString().trim());
				artupdate.setEmail(((PnlOngArtiste) panelref).getJtxftelephone().getText().toString().trim());
				artupdate.setTelephone(((PnlOngArtiste) panelref).getJtxftelephone().getText().toString().trim());
						
							
				ArtisteDao artDaoupdate = new ArtisteDao(((PnlOngArtiste)panelref).getConnection());
							
				try {
					artDaoupdate.updatePerso(artupdate);
					System.out.println("Mise à jour réussie");
				} catch (MyException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("Echec de la mise à jour");
				}

				// Mise à jour de la combo
				UpdateCombo fctupdate = new UpdateCombo(panelref);
				fctupdate.fctupdatecombo();
				
				
				JOptionPane.showMessageDialog(null, "La mise à jour a été effectuée");
				
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
				
				
			}else{
				
				// Effectuer un insert
				Pays paysartiste = new Pays();
				paysartiste.setIdPays(new Integer(0));
				paysartiste.setNom(((PnlOngArtiste) panelref).getJtxfpays().getText().toString().trim());
				
				Integer valpkpays=0;		
				PaysDao paysDao = new PaysDao(((PnlOngArtiste)panelref).getConnection());
							
				try {
					valpkpays=paysDao.insertPerso(paysartiste);
					paysartiste.setIdPays(valpkpays);
					//System.out.println("idPays " + paysartiste.getIdPays());
				} catch (MyException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("Echec de l'insertion");
				}

				Adresse adartiste = new Adresse();
				adartiste.setIdAdresse(new Integer(0));
				adartiste.setCodePostal(((PnlOngArtiste) panelref).getJtxfcodepostal().getText().toString().trim());
				adartiste.setNumero(((PnlOngArtiste) panelref).getJtxfnumero().getText().toString().trim());
				adartiste.setLocalite(((PnlOngArtiste) panelref).getJtxflocalite().getText().toString().trim());
				adartiste.setRue(((PnlOngArtiste) panelref).getJtxfrue().getText().toString().trim());
				adartiste.setPays(paysartiste);
				
				Integer valpkadresse = 0;
				AdresseDao adDao = new AdresseDao(((PnlOngArtiste)panelref).getConnection());
							
				try {
					valpkadresse=adDao.insertPerso(adartiste);
					adartiste.setIdAdresse(valpkadresse);
					//System.out.println("idAdresse: " + adartiste.getIdAdresse());
				} catch (MyException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("Echec de l'insertion");
				}

				
				Artiste artiste = new Artiste();
				artiste.setIdArtiste(new Integer(0));
				artiste.setNom(((PnlOngArtiste) panelref).getJtxfnom().getText().toString().trim());
				artiste.setPrenom(((PnlOngArtiste) panelref).getJtxfprenom().getText().toString().trim());
				artiste.setEmail(((PnlOngArtiste) panelref).getJtxfemail().getText().toString().trim());
				artiste.setTelephone(((PnlOngArtiste) panelref).getJtxftelephone().getText().toString().trim());
				artiste.setAdresse(adartiste);
				
				Integer valpkartiste=0;		
				ArtisteDao artDao = new ArtisteDao(((PnlOngArtiste)panelref).getConnection());
							
				try {
					valpkartiste=artDao.insertPerso(artiste);
					artiste.setIdArtiste(valpkartiste);
					//System.out.println("idArtiste: " + artiste.getIdArtiste());
				} catch (MyException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("Echec de l'insertion");
				}

				
				//Mise à jour de la combo
				UpdateCombo fctupdate2 = new UpdateCombo(panelref);
				fctupdate2.fctupdatecombo();
				
				JOptionPane.showMessageDialog(null, "Votre enregistrement a été effectué");
			
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
				
				
				
				
			}
			
		}
		
		
		
	}// Fin actionPerformed


		
	
}

	