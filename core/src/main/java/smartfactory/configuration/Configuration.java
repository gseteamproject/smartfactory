package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;
import org.jdom2.Element;
import smartfactory.utility.XMLFile;

public class Configuration {

	private PlatformConfiguration platformConfiguration;

	private ContainerConfiguration containerConfiguration;

	private AgentConfigurations agentConfigurations;

	private XMLFile configurationFile;

	public Configuration() {
		platformConfiguration = new PlatformConfiguration();
		containerConfiguration = new ContainerConfiguration();
		agentConfigurations = new AgentConfigurations();
		configurationFile = new XMLFile("configuration.xml");
	}

	public Configuration(PlatformConfiguration platform, ContainerConfiguration container, AgentConfigurations agents,
			XMLFile configurationFile) {
		this.platformConfiguration = platform;
		this.containerConfiguration = container;
		this.agentConfigurations = agents;
		this.configurationFile = configurationFile;
	}

	public String[] getStartupParameters() {
		List<String> parameters = new ArrayList<String>();
		parameters.addAll(platformConfiguration.getStartupParameters());
		parameters.addAll(containerConfiguration.getStartupParameters());
		parameters.addAll(agentConfigurations.getStartupParameters());
		return parameters.toArray(new String[parameters.size()]);
	}

	public void load() {
		Element root = configurationFile.getRootElement();
		platformConfiguration.load(root.getChild(PlatformConfiguration.TAG_PLATFORM));
		containerConfiguration.load(root.getChild(ContainerConfiguration.TAG_CONTAINER));
		agentConfigurations.load(root.getChild(AgentConfigurations.TAG_AGENTS));
	}
}
