package basicAgents;

import interactors.OrderDataStore;
import jade.core.Agent;
import salesMarketBehaviours.SalesMarketResponder;

public class SalesMarketAgent extends Agent {

	private static final long serialVersionUID = 2003110338808844985L;

	protected OrderDataStore dataStore;

	@Override
	protected void setup() {
		dataStore = new OrderDataStore();

		addBehaviour(new SalesMarketResponder(this, dataStore));
	}
}
