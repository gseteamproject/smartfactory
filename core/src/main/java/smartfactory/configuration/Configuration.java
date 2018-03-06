package smartfactory.configuration;

import java.util.List;
import org.jdom2.Element;

public class Configuration {

	private ContainerConfiguration containerConfiguration;

	private AgentConfigurations agentConfigurations;

	public Configuration() {
		this(new ContainerConfiguration(), new AgentConfigurations());
	}

	public Configuration(ContainerConfiguration containerConfiguration, AgentConfigurations agentConfigurations) {
		this.containerConfiguration = containerConfiguration;
		this.agentConfigurations = agentConfigurations;
	}

	public void load(Element root) {
		containerConfiguration.load(root.getChild(ConfigurationTag.CONTAINER));
		agentConfigurations.load(root.getChild(ConfigurationTag.AGENTS));
	}

	public ContainerConfiguration getContainerConfiguration() {
		return containerConfiguration;
	}

	public List<AgentConfiguration> getAgentConfigurations() {
		return agentConfigurations.asList();
	}
}
