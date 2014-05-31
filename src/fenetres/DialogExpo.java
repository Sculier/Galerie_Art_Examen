package fenetres;

import java.awt.BorderLayout;
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
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class DialogExpo extends JDialog {
	
	
	private JFrame frame = null;
	
	public DialogExpo (JFrame frame, String string, boolean b){
		
		super(frame, string, b);
		this.frame = frame;
		build();
		
	}
	
	private void build(){
		ImageIcon img = new ImageIcon(Accueil.class.getResource("../images/Power.jpg"));	
		this.setIconImage(img.getImage());
	
		this.setSize(800,500);
		this.setLocationRelativeTo(null);
		
		
		
	}
}	

