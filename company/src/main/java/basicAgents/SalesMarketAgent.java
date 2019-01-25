package basicAgents;

import common.AgentDataStore;
import common.AgentPlatform;
import jade.core.Agent;
import salesMarketBehaviours.SalesMarketResponder;

public class SalesMarketAgent extends Agent {

	private static final long serialVersionUID = 2003110338808844985L;

	protected AgentDataStore dataStore;

	@Override
	protected void setup() {
		dataStore = new AgentDataStore();
		dataStore.setAgentPlatform(new AgentPlatform(this));

		addBehaviour(new SalesMarketResponder(this, dataStore));
	}
}
