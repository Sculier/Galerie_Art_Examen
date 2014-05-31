package composant;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;

public class BtAcc extends JButton {
	
	public BtAcc(String nom){
		super(nom);
		build();
	}
	
	private void build() {
		
		this.add(Box.createVerticalStrut(30));
		this.setFont(new Font ("Verdana", Font.BOLD, 14));
		this.setPreferredSize(new Dimension(200,40));
		this.add(Box.createVerticalStrut(30));
		
	}
	

}
