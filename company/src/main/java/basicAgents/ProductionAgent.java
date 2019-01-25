package basicAgents;

import common.AgentDataStore;
import common.AgentPlatform;
import jade.core.Agent;
import productionBehaviours.ProductionResponder;

public class ProductionAgent extends Agent {

	private static final long serialVersionUID = 9064413910591040008L;

	protected AgentDataStore dataStore;

	@Override
	protected void setup() {
		dataStore = new AgentDataStore();
		dataStore.setAgentPlatform(new AgentPlatform(this));
		dataStore.setIsProduced(false);

		addBehaviour(new ProductionResponder(this, dataStore));
	}
}
