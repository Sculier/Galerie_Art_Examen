package composant;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;

public class LabelDial extends JLabel {
	
	public LabelDial(String nom){
		super(nom);
		build();
	}
	
	private void build() {
		
		Dimension dimlabel = new Dimension (220,25);
		this.setFont(new Font ("Verdana", Font.BOLD, 11));
		this.setPreferredSize(dimlabel);
		
	}
	

}
