package basicAgents;

import common.AgentDataStore;
import common.AgentPlatform;
import jade.core.Agent;
import procurementMarketBehaviours.ProcurementMarketResponder;

public class ProcurementMarketAgent extends Agent {

	private static final long serialVersionUID = -7418692714860762106L;

	protected AgentDataStore dataStore;

	@Override
	protected void setup() {
		dataStore = new AgentDataStore();
		dataStore.setAgentPlatform(new AgentPlatform(this));
		dataStore.setAgentName(getLocalName());

		addBehaviour(new ProcurementMarketResponder(this, dataStore));
	}
}
