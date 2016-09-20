package presenter;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Load the properties from the xml file "properties" at the start of the program
 * @author Tomer, Gilad
 *
 */
public class PropertiesLoader {
	private static PropertiesLoader instance;
	private Properties properties;
	
	/**
	 * Get the properties list
	 * @return
	 */
	public Properties getProperties() {
		return properties;
	}
	
	/**
	 * CTOR
	 */
	private PropertiesLoader() 
	{
		try {
			XMLDecoder decoder = new XMLDecoder(new FileInputStream("properties.xml"));
			properties = (Properties)decoder.readObject();
			decoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the instance of the properties loader
	 * @return
	 */
	public static PropertiesLoader getInstance() {
		if (instance == null) 
			instance = new PropertiesLoader();
		return instance;
	}
	
	
}
