package basicAgents;

import interactors.OrderDataStore;
import jade.core.Agent;
import procurementMarketBehaviours.ProcurementMarketResponder;

public class ProcurementMarketAgent extends Agent {

	private static final long serialVersionUID = -7418692714860762106L;

	protected OrderDataStore dataStore;

	@Override
	protected void setup() {
		dataStore = new OrderDataStore();

		addBehaviour(new ProcurementMarketResponder(this, dataStore));
	}
}
