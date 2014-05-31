package fenetres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;



















import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import composant.BtAcc;
import evt.OpenDialog;




public class Accueil extends JFrame {
	
	//JPanel Conteneur
	private JPanel panelconteneur = null;
	// JPanel Boutons
	private JPanel panelbt;
	// JButton accueil	
	private JButton btoeuv;
	private JButton btpub;
	private JButton btexpo;
	private JButton btfact;

	
	public Accueil(){
		super();
		construireFenetre();		
	}

	private void construireFenetre() {
		// TODO Auto-generated method stub

			// Design JFrame
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			// Caractéristiques de la JFrame
			ImageIcon img = new ImageIcon(Accueil.class.getResource("../images/Power.jpg"));	
			this.setIconImage(img.getImage());
		 	this.setTitle("Accueil"); 
		 	this.setResizable(false);
		 	BorderLayout bFenetre1 = new BorderLayout(50,50);
			this.setLayout(bFenetre1);	
		 	this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		 	
		 	this.addWindowListener(new WindowAdapter(){
				 public void windowClosing (WindowEvent evt){
					 int retourJOptionPane = JOptionPane.showConfirmDialog (null, 
							 "Voulez-vous quitter le programme ?","Demande de confirmation", 
							 										JOptionPane.YES_NO_OPTION );
					 if (retourJOptionPane==0){
							 System.exit(0);
					 } 
				 }	 
			 });
		 	
		 	//JPanel conteneur
		 	panelconteneur = new JPanel(); 
		 	panelconteneur.setPreferredSize(new Dimension (400,250));
		 	this.getContentPane().add(panelconteneur, BorderLayout.CENTER);
		 	
		 	// JPanel boutons
		 	panelbt = new JPanel();
		 	BoxLayout box = new BoxLayout(panelbt,BoxLayout.Y_AXIS);
			panelbt.setLayout(box);
		 	
		 	// Mise en forme du panel
		 	panelbt.add(Box.createVerticalStrut(10));
		 	panelconteneur.add(panelbt, new FlowLayout (FlowLayout.CENTER));
		 	
		 	// Mise en forme
		 	panelbt.add(Box.createVerticalStrut(10));
		 	// JButton
		 	btoeuv = new BtAcc ("Oeuvres");
			panelbt.add(btoeuv);
			btoeuv.addActionListener(new OpenDialog(this));	
	
			// Mise en forme
			panelbt.add(Box.createVerticalStrut(10));
			// JButton
			btpub = new BtAcc("Publicité");
			panelbt.add(btpub);
			btpub.addActionListener(new OpenDialog(this));	
				
			// Mise en forme
			panelbt.add(Box.createVerticalStrut(10));
			// JButton
			btexpo = new BtAcc("Expositions");
			panelbt.add(btexpo);
			btexpo.addActionListener(new OpenDialog(this));	
			
			
			// Mise en forme
			panelbt.add(Box.createVerticalStrut(10));
			// JButton
			btfact = new BtAcc("Factures");
			panelbt.add(btfact);
			btfact.addActionListener(new OpenDialog(this));	
	
			
			
			
			
			this.pack();
			this.setLocationRelativeTo(null);
	
	}
	
}	
	
	
	
	
	
	


	
	
	

	
	
	
	


