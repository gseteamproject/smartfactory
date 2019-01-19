package basicAgents;

import java.util.ArrayList;
import basicClasses.Order;
import interactors.OrderDataStore;
import jade.core.Agent;
import sellingBehaviours.SellingResponder;

public class SellingAgent extends Agent {

	private static final long serialVersionUID = 7150875080288668056L;

	protected OrderDataStore dataStore;

	@Override
	protected void setup() {
		dataStore = new OrderDataStore();
		dataStore.setIsTaken(false);
		dataStore.setIsInWarehouse(false);
		dataStore.setProductionQueue(new ArrayList<Order>());

		addBehaviour(new SellingResponder(this, dataStore));
	}
}
