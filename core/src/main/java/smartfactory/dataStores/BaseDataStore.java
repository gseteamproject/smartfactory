package smartfactory.dataStores;

import jade.core.behaviours.DataStore;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.platform.AgentPlatform;

public class BaseDataStore extends DataStore {

	public AgentPlatform getAgentPlatform() {
		return (AgentPlatform) get("agentPlatform");
	}

	public void setAgentPlatform(AgentPlatform agentPlatform) {
		put("agentPlatform", agentPlatform);
	}

	public void setSubAgentConfiguration(AgentConfiguration agentConfiguration) {
		put("subAgentConfiguration", agentConfiguration);
	}

	public AgentConfiguration getSubAgentConfiguration() {
		return (AgentConfiguration) get("subAgentConfiguration");
	}

	private static final long serialVersionUID = 4398092789071233362L;
}
