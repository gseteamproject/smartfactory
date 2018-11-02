package smartfactory.interactors.base;

import smartfactory.configuration.AgentConfiguration;
import smartfactory.interactors.OneShotInteractor;
import smartfactory.utility.AgentDataStore;

public class LaunchAgent extends OneShotInteractor {

	public LaunchAgent(AgentDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public void execute() {
		AgentConfiguration agentConfiguration = agentDataStore.getSubAgentConfiguration();
		agentDataStore.getAgentPlatform().startAgent(agentConfiguration);
	}

	@Override
	public int next() {
		return 0;
	}
}
