package smartfactory.services;

import smartfactory.agents.PaintingProcessAgent;
import smartfactory.utility.AgentDataStore;

public class PaintingService extends LaunchProcessService {

	public PaintingService(AgentDataStore agentDataStore) {
		super(Services.painting, agentDataStore, PaintingProcessAgent.class);
	}
}
