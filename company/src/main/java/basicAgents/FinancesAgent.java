package basicAgents;

import financesBehaviours.FinancesResponder;
import interactors.OrderDataStore;
import jade.core.Agent;

public class FinancesAgent extends Agent {

	private static final long serialVersionUID = 4773016963292343207L;

	protected OrderDataStore dataStore;

	@Override
	protected void setup() {
		dataStore = new OrderDataStore();

		addBehaviour(new FinancesResponder(this, dataStore));
	}
}
