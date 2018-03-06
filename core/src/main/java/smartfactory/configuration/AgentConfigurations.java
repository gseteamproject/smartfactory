package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

public class AgentConfigurations {

	List<AgentConfiguration> agentConfigurations;

	public AgentConfigurations() {
		this.agentConfigurations = new ArrayList<AgentConfiguration>();
	}

	public List<AgentConfiguration> asList() {
		return agentConfigurations;
	}

	public void load(Element root) {
		for (Element agent : root.getChildren(Tag.AGENT)) {
			AgentConfiguration agentConfiguration = new AgentConfiguration();
			agentConfiguration.load(agent);
			agentConfigurations.add(agentConfiguration);
		}
	}
}
