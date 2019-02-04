package basicAgents;

import common.AgentDataStore;
import common.AgentPlatform;
import financesBehaviours.FinancesResponder;
import jade.core.Agent;

public class FinancesAgent extends Agent {

	private static final long serialVersionUID = 4773016963292343207L;

	protected AgentDataStore dataStore;

	@Override
	protected void setup() {
		dataStore = new AgentDataStore();
		dataStore.setAgentPlatform(new AgentPlatform(this));
		dataStore.setAgentName(getLocalName());

		addBehaviour(new FinancesResponder(this, dataStore));
	}
}
