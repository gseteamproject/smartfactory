package smartfactory.dataStores;

import jade.core.behaviours.DataStore;
import smartfactory.platform.AgentPlatform;

public class SmartFactoryDataStore extends DataStore {

	public AgentPlatform getAgentPlatform() {
		return (AgentPlatform) get("agentPlatform");
	}

	public void setAgentPlatform(AgentPlatform agentPlatform) {
		put("agentPlatform", agentPlatform);
	}

	private static final long serialVersionUID = 4398092789071233362L;
}
