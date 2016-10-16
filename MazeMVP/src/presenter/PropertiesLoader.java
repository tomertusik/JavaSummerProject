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
	public PropertiesLoader() 
	{
		XMLDecoder decoder = new XMLDecoder(getClass().getClassLoader().getResourceAsStream("properties.xml"));
		properties = (Properties)decoder.readObject();
		decoder.close();
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

	/**
	 * Sets the properties from file
	 * @param file
	 */
	public void setProperties(String file) {
		try {
			XMLDecoder decoder = new XMLDecoder(new FileInputStream(file));
			properties = (Properties) decoder.readObject();
			decoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
}
