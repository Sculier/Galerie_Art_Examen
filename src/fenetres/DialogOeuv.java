package fenetres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import composant.JtxtfDial;
import composant.LabelDial;
import composant.PnlOngArtiste;
import composant.PnlOngOeuv;



public class DialogOeuv extends JDialog {
	
	private JFrame frame = null;
	
	public DialogOeuv (JFrame frame, String string, boolean b){
		
		super(frame, string, b);
		this.frame = frame;
		build();
		
	}
	
	private void build(){
	
		// Icone JFrame
		ImageIcon img = new ImageIcon(Accueil.class.getResource("../images/Power.jpg"));	
		this.setIconImage(img.getImage());
		// JFrame
		this.setResizable(false);
	
		
		// Conteneur onglet
		JTabbedPane ongletconteneur = new JTabbedPane();
		ongletconteneur.setFont(new Font ("Verdana", Font.PLAIN, 13));
		ongletconteneur.setPreferredSize(new Dimension(1325,600));
		this.getContentPane().add(ongletconteneur, BorderLayout.CENTER);
		
	
		// Onglets
		PnlOngArtiste ongletArtiste = new PnlOngArtiste(this, new BorderLayout());
		ongletconteneur.add("Gérer les artistes",ongletArtiste);
		
		PnlOngOeuv ongletOeuvre = new PnlOngOeuv(this, new BorderLayout());
		ongletconteneur.add("Gérer les oeuvres",ongletOeuvre);
			
		
		// JFrame
		this.pack();
		this.setLocationRelativeTo(null);
		
		
		
	}
}	

