package basicAgents;

import interactors.OrderDataStore;
import jade.core.Agent;
import productionBehaviours.ProductionResponder;

public class ProductionAgent extends Agent {

	private static final long serialVersionUID = 9064413910591040008L;

	protected OrderDataStore dataStore;

	@Override
	protected void setup() {
		dataStore = new OrderDataStore();
		dataStore.setIsProduced(false);

		addBehaviour(new ProductionResponder(this, dataStore));
	}
}
