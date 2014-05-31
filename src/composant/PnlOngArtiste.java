package composant;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import outilConnect.OutilLectParamConnect;
import outilConnect.Singleton;
import outilDAO.MyException;
import pojo.Adresse;
import pojo.Artiste;
import pojo.Categorie;
import pojo.Pays;
import pojoDAO.ArtisteDao;
import evt.BtDeleteArtiste;
import evt.BtNullSearchArtiste;
import evt.BtSaveArtiste;
import evt.BtSearchArtiste;
import evt.OpenDialog;

public class PnlOngArtiste extends JPanel {
	
	private Artiste artselect = new Artiste();
	private JDialog dialref=null;
	private JButton btsearch=null;
	private JButton btnull = null;
	private JButton btenr = null;
	private JButton btsupr = null;
	private DefaultComboBoxModel<Artiste> modelcombosearch = null;
	private JComboBox combosearch = null;
	private List<Artiste> listartiste = null;
	private JtxtfDial jtxfnom = null;
	private JtxtfDial jtxfemail= null;
	private JtxtfDial jtxfrue= null;
	private JtxtfDial jtxfcodepostal=null;
	private JtxtfDial jtxfpays=null;
	private JtxtfDial jtxfprenom =null;
	private JtxtfDial jtxftelephone =null;
	private JtxtfDial jtxfnumero =null;
	private JtxtfDial jtxflocalite =null;
	private boolean drapeau= false; 
	private Connection connection = null;
	

	
	public PnlOngArtiste(JDialog dialref, BorderLayout borderLayout){
		super();
		this.dialref=dialref;
		build();
	}
	
	private void build() {
	
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
		
		
		connection = Singleton.getInstance(props);
		
		
		this.setLayout(new BorderLayout(20,20));
		
		
	 	JPanel panelmain = new JPanel(new BorderLayout(0,0));
	 	panelmain.setPreferredSize(new Dimension(1200,1200));

	 	ScrollOng scrollPane = new ScrollOng(new Dimension(2000,2000));
	 	scrollPane.add(panelmain, FlowLayout.CENTER);
	 	scrollPane.setViewportView(panelmain);
		this.add(scrollPane, BorderLayout.CENTER);					

										// PARTIE NORD
		JPanel panelcontnord = new JPanel(new BorderLayout(20,20));
		panelmain.add(panelcontnord, BorderLayout.NORTH);
		
		PnlEntete paneltitre = new PnlEntete("Gérer les artistes");
		panelcontnord.add(paneltitre, BorderLayout.NORTH);
		
		paneltitre.add(Box.createVerticalStrut(80));
		
		
		
									// PARTIE CENTRALE
		JPanel paneldsnord = new JPanel();
		//paneldsnord.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelcontnord.add(paneldsnord, BorderLayout.WEST);		
		paneldsnord.add(Box.createHorizontalStrut(50));
		
		
		JLabel labelsearch = new JLabel("Rechercher par le nom de l'artiste :");
		labelsearch.setPreferredSize(new Dimension(260,40));
		labelsearch.setFont(new Font ("Verdana", Font.PLAIN, 11));
		paneldsnord.add(labelsearch);
		
		
		listartiste = new ArrayList<Artiste>();
		ArtisteDao artDao = new ArtisteDao(connection);
			
		try {
			listartiste=artDao.findAll();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Echec de la sélection des tous les objets");
		}
		
		modelcombosearch = new DefaultComboBoxModel<Artiste>();
		combosearch = new JComboBox(modelcombosearch);
		combosearch.setPreferredSize(new Dimension(300,25));
		paneldsnord.add(combosearch);
		
		paneldsnord.add(Box.createHorizontalStrut(30));
		
		for (Artiste arti:listartiste){
			modelcombosearch.addElement(arti);
		}		
		combosearch.setSelectedIndex(-1);
		
		
		btsearch = new JButton("Rechercher");
		btsearch.setPreferredSize(new Dimension(150,25));
		btsearch.addActionListener(new BtSearchArtiste(this));
		paneldsnord.add(btsearch);
		
		
		JPanel panelcontcent = new JPanel (new BorderLayout(40,40));
		panelmain.add(panelcontcent, BorderLayout.CENTER);
		
		
		JPanel panelcent = new JPanel();
		panelcent.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelcontcent.add(panelcent, BorderLayout.NORTH);

		
		GridBagLayout gbl = new GridBagLayout();
		JPanel jp = new JPanel();
		panelcent.add(jp);
		jp.setLayout(gbl);
		jp.add(Box.createVerticalStrut(50));
		jp.add(Box.createHorizontalStrut(30));
		

		TitledBorder titre = BorderFactory.createTitledBorder
				(BorderFactory.createMatteBorder (1, 1, 3 ,3, Color.DARK_GRAY), 
												"Les caractéristiques de l'artiste",
												TitledBorder.LEFT, TitledBorder.ABOVE_TOP);
		titre.setTitleFont(new Font ("Verdana", Font.ITALIC+Font.BOLD, 11));	
		jp.setBorder(titre);		
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,50, 0, 0);		
				
		// Colonne 1 JLabel
		LabelDial labelnom = new LabelDial ("Nom");
		gbc.gridx = 0;
		gbc.gridy = 0;
		jp.add(labelnom,gbc);
				
		LabelDial labelemail = new LabelDial ("Email");
		gbc.gridx = 0;
		gbc.gridy = 1;
		jp.add(labelemail,gbc);
				
		LabelDial labeladresse = new LabelDial ("Adresse");
		gbc.gridx = 0;
		gbc.gridy = 2;
		jp.add(labeladresse,gbc);
				
		LabelDial labelrue = new LabelDial ("Rue");
		gbc.gridx = 0;
		gbc.gridy = 3;
		jp.add(labelrue,gbc);
				
		LabelDial labelcodepostal = new LabelDial ("Code postal");
		gbc.gridx = 0;
		gbc.gridy = 4;
		jp.add(labelcodepostal,gbc);
				
		LabelDial labelpays = new LabelDial ("Pays");
		gbc.gridx = 0;
		gbc.gridy = 5;
		jp.add(labelpays,gbc);
			
				
		// Colonne 1 JTextfield
		jtxfnom = new JtxtfDial();
		gbc.gridx = 1;
		gbc.gridy = 0;
		jp.add(jtxfnom,gbc);
				
		jtxfemail= new JtxtfDial();
		gbc.gridx = 1;
		gbc.gridy = 1;
		jp.add(jtxfemail,gbc);
			
		jtxfrue= new JtxtfDial();
		gbc.gridx = 1;
		gbc.gridy = 3;
		jp.add(jtxfrue,gbc);
				
		jtxfcodepostal= new JtxtfDial();
		gbc.gridx = 1;
		gbc.gridy = 4;
		jp.add(jtxfcodepostal,gbc);
				
		jtxfpays= new JtxtfDial();
		gbc.gridx = 1;
		gbc.gridy = 5;
		jp.add(jtxfpays,gbc);

		
		// Label pour mise en forme
		JLabel emptylb = new JLabel();
		emptylb.setPreferredSize(new Dimension(0,20));
		gbc.gridx = 1;
		gbc.gridy = 6;
		jp.add(emptylb,gbc);

				
		// Colonne 2 JLabel
		LabelDial labelprenom = new LabelDial ("Prénom");
		gbc.gridx = 2;
		gbc.gridy = 0;
		jp.add(labelprenom,gbc);
				
		LabelDial labeltelephone = new LabelDial ("Téléphone");
		gbc.gridx = 2;
		gbc.gridy = 1;
		jp.add(labeltelephone,gbc);
				
		LabelDial labelnumero = new LabelDial ("Numéro");
		gbc.gridx = 2;
		gbc.gridy = 3;
		jp.add(labelnumero,gbc);
						
		LabelDial labellocalite = new LabelDial ("Localité");
		gbc.gridx = 2;
		gbc.gridy = 4;
		jp.add(labellocalite,gbc);
				
				
		// Colonne 2 JTextField
		jtxfprenom = new JtxtfDial();
		gbc.gridx = 3;
		gbc.gridy = 0;
		jp.add(jtxfprenom,gbc);
			
				
		jtxftelephone = new JtxtfDial();
		gbc.gridx = 3;
		gbc.gridy = 1;
		jp.add(jtxftelephone,gbc);
					
		jtxfnumero = new JtxtfDial();
		gbc.gridx = 3;
		gbc.gridy = 3;
		jp.add(jtxfnumero,gbc);
				
			
		jtxflocalite = new JtxtfDial();
		gbc.gridx = 3;
		gbc.gridy = 4;
		jp.add(jtxflocalite,gbc);
		
		jp.add(Box.createVerticalStrut(50));
		jp.add(Box.createHorizontalStrut(30));
		
		
			
									// PARTIE SUD
		// Garder la possibilité de garder de l'espace dans le sud du "panelconbt"
		JPanel panelcontsud = new JPanel(new BorderLayout());
		panelcontcent.add(panelcontsud, BorderLayout.EAST);
		
		
		JPanel panelbt = new JPanel();
		panelbt.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelcontsud.add(panelbt, BorderLayout.NORTH);
		
		btnull = new JButton("Annuler");
		btnull.setPreferredSize(new Dimension(100,40));
		btnull.addActionListener(new BtNullSearchArtiste(this));
		panelbt.add(btnull);
		
		btsupr = new JButton("Supprimer");
		btsupr.setPreferredSize(new Dimension(100,40));
		btsupr.addActionListener(new BtDeleteArtiste(this));
		panelbt.add(btsupr);
			
		btenr = new JButton("Enregistrer");
		btenr.setPreferredSize(new Dimension(100,40));
		btenr.addActionListener(new BtSaveArtiste(this));
		panelbt.add(btenr);
	
		panelbt.add(Box.createHorizontalStrut(40));
	
	
	}

	
	
	public JtxtfDial getJtxfnom() {
		return jtxfnom;
	}
	public void setJtxfnom(JtxtfDial jtxfnom) {
		this.jtxfnom = jtxfnom;
	}
	public JtxtfDial getJtxfemail() {
		return jtxfemail;
	}
	public void setJtxfemail(JtxtfDial jtxfemail) {
		this.jtxfemail = jtxfemail;
	}
	public JtxtfDial getJtxfrue() {
		return jtxfrue;
	}
	public void setJtxfrue(JtxtfDial jtxfrue) {
		this.jtxfrue = jtxfrue;
	}
	public JtxtfDial getJtxfcodepostal() {
		return jtxfcodepostal;
	}
	public void setJtxfcodepostal(JtxtfDial jtxfcodepostal) {
		this.jtxfcodepostal = jtxfcodepostal;
	}
	public JtxtfDial getJtxfpays() {
		return jtxfpays;
	}
	public void setJtxfpays(JtxtfDial jtxfpays) {
		this.jtxfpays = jtxfpays;
	}
	public JtxtfDial getJtxfprenom() {
		return jtxfprenom;
	}
	public void setJtxfprenom(JtxtfDial jtxfprenom) {
		this.jtxfprenom = jtxfprenom;
	}
	public JtxtfDial getJtxftelephone() {
		return jtxftelephone;
	}
	public void setJtxftelephone(JtxtfDial jtxftelephone) {
		this.jtxftelephone = jtxftelephone;
	}
	public JtxtfDial getJtxfnumero() {
		return jtxfnumero;
	}
	public void setJtxfnumero(JtxtfDial jtxfnumero) {
		this.jtxfnumero = jtxfnumero;
	}
	public JtxtfDial getJtxflocalite() {
		return jtxflocalite;
	}
	public void setJtxflocalite(JtxtfDial jtxflocalite) {
		this.jtxflocalite = jtxflocalite;
	}
	public boolean isDrapeau() {
		return drapeau;
	}
	public void setDrapeau(boolean drapeau) {
		this.drapeau = drapeau;
	}
	public DefaultComboBoxModel<Artiste> getModelcombosearch() {
		return modelcombosearch;
	}
	public void setModelcombosearch(DefaultComboBoxModel<Artiste> modelcombosearch) {
		this.modelcombosearch = modelcombosearch;
	}
	public Artiste getArtselect() {
		return artselect;
	}
	public void setArtselect(Artiste artselect) {
		this.artselect = artselect;
	}
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public JComboBox getCombosearch() {
		return combosearch;
	}
	public void setCombosearch(JComboBox combosearch) {
		this.combosearch = combosearch;
	}

	
	
}
