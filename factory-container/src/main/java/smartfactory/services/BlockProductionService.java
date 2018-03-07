package smartfactory.services;

import smartfactory.agents.BlockProcessAgent;
import smartfactory.utility.AgentDataStore;

public class BlockProductionService extends LaunchProcessService {

	public BlockProductionService(AgentDataStore agentDataStore) {
		super(Services.block_production, agentDataStore, BlockProcessAgent.class);
	}
}
