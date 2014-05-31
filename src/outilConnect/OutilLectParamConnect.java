package outilConnect;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class OutilLectParamConnect {

	
	public static Properties recupererProperties (String chemin) throws FileNotFoundException, IOException{
		
		File file = new File (chemin);
		Properties props = new Properties();
		FileReader fr = null; 
		
		// Pour lire dans le fichier Properties
		fr = new FileReader(file);
		props.load(fr);
		fr.close();		
		return props;
		
		
	}
	
	
	
	
	
	
}
