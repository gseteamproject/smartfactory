package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;
import org.jdom2.Element;
import smartfactory.utility.XMLFile;

public class Configuration {

	private ContainerConfiguration containerConfiguration;

	private AgentConfigurations agentConfigurations;

	private XMLFile configurationFile;

	public Configuration() {
		containerConfiguration = new ContainerConfiguration();
		agentConfigurations = new AgentConfigurations();
		configurationFile = new XMLFile("configuration.xml");
	}

	public Configuration(ContainerConfiguration container, AgentConfigurations agents, XMLFile configurationFile) {
		this.containerConfiguration = container;
		this.agentConfigurations = agents;
		this.configurationFile = configurationFile;
	}

	public String[] getStartupParameters() {
		List<String> parameters = new ArrayList<String>();
		parameters.addAll(containerConfiguration.getStartupParameters());
		parameters.addAll(agentConfigurations.getStartupParameters());
		return parameters.toArray(new String[parameters.size()]);
	}

	public void load() {
		Element root = configurationFile.getRootElement();
		containerConfiguration.load(root.getChild(ContainerConfiguration.TAG_CONTAINER));
		agentConfigurations.load(root.getChild(AgentConfigurations.TAG_AGENTS));
	}

	public ContainerConfiguration getContainerConfiguration() {
		return containerConfiguration;
	}

	public List<AgentConfiguration> getAgentConfigurations() {
		return agentConfigurations.agentConfigurations;
	}
}
