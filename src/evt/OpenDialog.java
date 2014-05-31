package evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import fenetres.DialogExpo;
import fenetres.DialogFact;
import fenetres.DialogOeuv;
import fenetres.DialogPub;

public class OpenDialog implements ActionListener {

	
	private JFrame frame = null;
	private JDialog dialog =null;
	
	public OpenDialog(JFrame frame){
		this.frame = frame;	
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		Object obj = arg0.getSource();
		
		if(obj instanceof JButton && ((JButton)obj).getText().equals("Oeuvres")){
			dialog = new DialogOeuv(frame, "Les oeuvres", true);
			dialog.setVisible(true);
		}
		if(obj instanceof JButton && ((JButton)obj).getText().equals("Publicité")){
			dialog = new DialogPub(frame,"Les publicités",true);
			dialog.setVisible(true);
		}
		if(obj instanceof JButton && ((JButton)obj).getText().equals("Expositions")){
			dialog = new DialogExpo(frame, "Les expositions", true);
			dialog.setVisible(true);
		}
		if(obj instanceof JButton && ((JButton)obj).getText().equals("Factures")){
			dialog = new DialogFact(frame, "Les factures", true);
			dialog.setVisible(true);
		}
		
	}// Fin actionPerformed


	
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public JDialog getDialog() {
		return dialog;
	}
	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}

	
}
