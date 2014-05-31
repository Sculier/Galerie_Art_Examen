package composant;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class ScrollOng extends JScrollPane {
	
	private Dimension dim=null;
	
	public ScrollOng(Dimension dim){
		build();
	}
	
	private void build() {
		this.setPreferredSize(dim);
		this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	

}
