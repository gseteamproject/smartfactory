package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;
import org.jdom2.Element;

public class Configuration {

	private ContainerConfiguration containerConfiguration;

	List<AgentConfiguration> agentConfigurations = new ArrayList<AgentConfiguration>();

	public Configuration() {
		this(new ContainerConfiguration());
	}

	public Configuration(ContainerConfiguration containerConfiguration) {
		this.containerConfiguration = containerConfiguration;
	}

	public void load(Element root) {
		loadContainerConfiguration(root);
		loadAgentConfigurations(root);
	}

	void loadContainerConfiguration(Element root) {
		containerConfiguration.load(root.getChild(ConfigurationTag.CONTAINER));
	}

	void loadAgentConfigurations(Element root) {
		Element agents = root.getChild(ConfigurationTag.AGENTS);
		for (Element agent : agents.getChildren(ConfigurationTag.AGENT)) {
			AgentConfiguration agentConfiguration = new AgentConfiguration();
			agentConfiguration.load(agent);
			agentConfigurations.add(agentConfiguration);
		}
	}

	public ContainerConfiguration getContainerConfiguration() {
		return containerConfiguration;
	}

	public List<AgentConfiguration> getAgentConfigurations() {
		return agentConfigurations;
	}
}
