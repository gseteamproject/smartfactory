package smartfactory.services;

import smartfactory.agents.CleaningProcessAgent;
import smartfactory.utility.AgentDataStore;

public class CleaningService extends LaunchProcessService {

	public CleaningService(AgentDataStore agentDataStore) {
		super(Services.cleaning, agentDataStore, CleaningProcessAgent.class);
	}
}
