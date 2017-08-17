package smartfactory.container;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.Boot;
import smartfactory.configuration.Configuration;

public class ContainerLauncher {

	private Configuration configuration;

	private JADEPlatform jade;

	public ContainerLauncher() {
		configuration = new Configuration();

		jade = new JADEPlatform() {
			@Override
			public void launch(String[] args) {
				Boot.main(args);
			}
		};
	}

	public ContainerLauncher(JADEPlatform jade, Configuration configuration) {
		this.configuration = configuration;
		this.jade = jade;
	}

	public void start() {
		configuration.load();
		logger.info("configuration loaded");
		jade.launch(configuration.getStartupParameters());
	}

	final Logger logger = LoggerFactory.getLogger(ContainerLauncher.class);
}
