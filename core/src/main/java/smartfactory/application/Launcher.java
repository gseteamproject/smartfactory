package smartfactory.application;

import org.slf4j.LoggerFactory;

import smartfactory.configuration.Configuration;
import smartfactory.container.Container;
import smartfactory.platform.JADEPlatform;

public class Launcher {

	public static void main(String[] args) {
		try {
			Configuration configuration = new Configuration();
			configuration.load();

			new Container(new JADEPlatform()).launch(configuration);
		} catch (Exception e) {
			LoggerFactory.getLogger(Launcher.class).error("", e);
		}
	}
}
