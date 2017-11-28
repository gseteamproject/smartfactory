package smartfactory.dataStores;

import smartfactory.configuration.AgentConfiguration;

public class CustomerDataStore extends SmartFactoryDataStore {

	private static final long serialVersionUID = -6038149266088343332L;

	public void setAgentConfiguration(AgentConfiguration agentConfiguration) {
		put("agentConfiguration", agentConfiguration);
	}

	public AgentConfiguration getAgentConfiguration() {
		return (AgentConfiguration) get("agentConfiguration");
	}
}
