package smartfactory.application;

import org.slf4j.LoggerFactory;

import smartfactory.configuration.Configuration;
import smartfactory.container.Container;
import smartfactory.platform.JADEPlatform;
import smartfactory.utility.XMLFile;

public class Launcher {

	public static void main(String[] args) {
		try {
			XMLFile xmlFile = new XMLFile("configuration.xml");
			Configuration configuration = new Configuration();
			JADEPlatform jade = new JADEPlatform();

			configuration.load(xmlFile.getRootElement());
			new Container(jade).launch(configuration);
		} catch (Exception e) {
			LoggerFactory.getLogger(Launcher.class).error("", e);
		}
	}
}
