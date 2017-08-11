package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;
import org.jdom2.Element;
import smartfactory.utility.XMLFile;

public class Configuration {

	private PlatformConfiguration platform;

	private AgentConfigurations agents;

	private XMLFile configurationFile;

	public Configuration() {
		platform = new PlatformConfiguration();
		agents = new AgentConfigurations();
		configurationFile = new XMLFile("configuration.xml");
	}

	public Configuration(PlatformConfiguration platform, AgentConfigurations agents, XMLFile configurationFile) {
		this.platform = platform;
		this.agents = agents;
		this.configurationFile = configurationFile;
	}

	public String[] getStartupParameters() {
		List<String> parameters = new ArrayList<String>();
		parameters.addAll(platform.getStartupParameters());
		parameters.add(agents.getStartupParameters());
		return parameters.toArray(new String[parameters.size()]);
	}

	public void load() {
		Element root = configurationFile.getRootElement();
		platform.load(root.getChild("jade-platform"));
		agents.load(root.getChild("agents"));
	}
}
