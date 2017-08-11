package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

public class AgentConfigurations {

	public List<AgentConfiguration> agentConfigurations;

	public AgentConfigurations() {
		this.agentConfigurations = new ArrayList<AgentConfiguration>();
	}

	public String getStartupParameters() {
		String parameters = "";
		for (AgentConfiguration agentConfiguration : agentConfigurations) {
			parameters += agentConfiguration.getStartupParameters() + ";";
		}
		return parameters;
	}

	public void load(Element root) {
		List<Element> agents = root.getChildren("agent");
		for (Element agent : agents) {
			AgentConfiguration agentConfiguration = new AgentConfiguration();
			agentConfiguration.load(agent);
			agentConfigurations.add(agentConfiguration);
		}
	}
}
