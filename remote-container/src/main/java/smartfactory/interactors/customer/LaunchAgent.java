package smartfactory.interactors.customer;

import smartfactory.configuration.AgentConfiguration;
import smartfactory.dataStores.CustomerDataStore;
import smartfactory.interactors.OneShotInteractor;

public class LaunchAgent extends CustomerInteractor implements OneShotInteractor {

	public LaunchAgent(CustomerDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		AgentConfiguration agentConfiguration = dataStore.getAgentConfiguration();
		dataStore.getAgentPlatform().startAgent(agentConfiguration.name, agentConfiguration.className);
	}

	@Override
	public int next() {
		return 0;
	}
}
