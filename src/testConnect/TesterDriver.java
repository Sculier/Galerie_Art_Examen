package testConnect;

import javax.swing.JOptionPane;

public class TesterDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			System.out.println("Driver reconnu");
			JOptionPane.showMessageDialog(null,"Driver reconnu");
		}
		catch (ClassNotFoundException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Driver non reconnu");
		}
	
	}

}
