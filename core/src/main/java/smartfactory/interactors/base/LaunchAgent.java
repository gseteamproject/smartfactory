package smartfactory.interactors.base;

import smartfactory.configuration.AgentConfiguration;
import smartfactory.dataStores.BaseDataStore;
import smartfactory.interactors.OneShotInteractor;

public class LaunchAgent extends BaseInteractor implements OneShotInteractor {

	public LaunchAgent(BaseDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		AgentConfiguration agentConfiguration = dataStore.getSubAgentConfiguration();
		dataStore.getAgentPlatform().startAgent(agentConfiguration.name, agentConfiguration.className);
	}

	@Override
	public int next() {
		return 0;
	}
}
