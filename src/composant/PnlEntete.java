package composant;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PnlEntete extends JPanel {
	
	private String titrelabel;
	
	public PnlEntete(String titrelabel){
		super();
		this.titrelabel=titrelabel;
		build();
	}
	
	private void build() {
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel labeltitre = new JLabel(titrelabel);
		labeltitre.setPreferredSize(new Dimension(400, 40));
		labeltitre.setFont(new Font("Verdana", Font.PLAIN, 20));
		labeltitre.setHorizontalAlignment(JLabel.CENTER);
		labeltitre.setBackground(Color.decode("#BBD2E1"));
		labeltitre.setForeground(Color.BLACK);
		labeltitre.setOpaque(true);
		labeltitre.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		this.add(labeltitre);
		
	}
	

}
