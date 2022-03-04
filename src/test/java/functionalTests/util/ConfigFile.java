/**
 *
 */
package functionalTests.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFile {

//	String result = "";
	InputStream inputStream;

	public Properties OpenConfig () throws IOException {
		Properties prop = new Properties();
		try {

			String propFileName = "config.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

		} catch (Exception e) {
			System.err.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return prop;

	}

	//read get element from config file
	public String GetElementFromConfig(String element) throws Exception {

		String result=null;
		try {
			Properties prop = new Properties();
			prop = this.OpenConfig();

			result = prop.getProperty(element);

		} catch (Exception e) {
			System.err.println("Exception: " + e);
		}
		return result;
	}

}