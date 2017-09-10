package smartfactory.container;

import smartfactory.configuration.Configuration;
import smartfactory.platform.AgentPlatform;
import smartfactory.platform.JADEPlatform;

public class ContainerLauncher {

	private Configuration configuration;

	private AgentPlatform jade;

	public ContainerLauncher() {
		this(new JADEPlatform(), new Configuration());
	}

	public ContainerLauncher(AgentPlatform jade, Configuration configuration) {
		this.configuration = configuration;
		this.jade = jade;
	}

	public void start() {
		configuration.load();
		jade.launch(configuration.getStartupParameters());
	}
}
