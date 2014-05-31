package sauvegardeParamConnect;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class OutilSauvegardeParamConnect {

	public static void sauveProperties (Properties props, String chemin) throws IOException{
		

		File file = new File (chemin);
		FileWriter fw = null; 
		fw = new FileWriter(file);
		// Pour écrire dans le fichier "Properties"
		props.store(fw, "Configuration Properties");
		fw.close();		
		
		
		
	}
	
	
}
