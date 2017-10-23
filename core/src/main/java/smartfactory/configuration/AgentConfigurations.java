package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

public class AgentConfigurations {

	static final String TAG_AGENTS = "agents";

	public List<AgentConfiguration> agentConfigurations;

	public AgentConfigurations() {
		this.agentConfigurations = new ArrayList<AgentConfiguration>();
	}

	public List<String> getStartupParameters() {
		List<String> parameters = new ArrayList<String>();
		appendAgents(parameters);
		return parameters;
	}

	public void load(Element root) {
		List<Element> agents = root.getChildren(AgentConfiguration.TAG_AGENT);
		for (Element agent : agents) {
			AgentConfiguration agentConfiguration = new AgentConfiguration();
			agentConfiguration.load(agent);
			agentConfigurations.add(agentConfiguration);
		}
	}

	void appendAgents(List<String> parameters) {
		String parameter = "";
		for (AgentConfiguration agentConfiguration : agentConfigurations) {
			parameter += agentConfiguration.getStartupParameters() + ";";
		}
		parameters.add(parameter);
	}
}
