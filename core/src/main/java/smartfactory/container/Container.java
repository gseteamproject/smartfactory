package smartfactory.container;

import org.slf4j.LoggerFactory;

public class Container {

	public static void main(String[] args) {
		try {
			new ContainerLauncher().start();
		} catch (Exception e) {
			LoggerFactory.getLogger(Container.class).error("", e);
		}
	}
}
