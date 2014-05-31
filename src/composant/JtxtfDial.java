package composant;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JtxtfDial extends JTextField {
	
	public JtxtfDial(){
		super();
		build();
	}
	
	private void build() {
		
		Dimension dimjtxf = new Dimension (300,25);
		//this.setFont(new Font ("Verdana", Font.BOLD, 14));
		this.setBorder(BorderFactory.createLineBorder(Color.decode("#CECECE"), 1, false));
		this.setPreferredSize(dimjtxf);
		
	}
	

}
