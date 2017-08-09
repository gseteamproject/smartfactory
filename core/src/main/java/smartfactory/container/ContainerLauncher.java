package smartfactory.container;

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
		jade.launch(configuration.getCommandLineParameters());
	}
}
