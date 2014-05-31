package sauvegardeParamConnect;

import java.io.IOException;
import java.util.Properties;

public class EcrireParamConnectProp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Properties props = new Properties();
		String chemin = "fichiers/Config.properties";
		
		props.put("driver","com.mysql.jdbc.Driver");
		props.put("url","jdbc:mysql://localhost:3306/galerieart09");
		props.put("admin","root");
		props.put("password","");
		
		
		try {
		
			OutilSauvegardeParamConnect.sauveProperties(props, chemin);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
