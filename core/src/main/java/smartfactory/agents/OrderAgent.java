package smartfactory.agents;

import smartfactory.behaviours.order.OrderBehaviour;
import smartfactory.dataStores.OrderDataStore;
import smartfactory.models.Order;

public class OrderAgent extends BaseAgent {

	public static String getUniqueName() {
		return "order-" + Long.toString(System.currentTimeMillis());
	}

	private OrderDataStore dataStore;

	public Order createOrder() {
		return new Order();
	}

	@Override
	protected void initializeBehaviours() {
		addBehaviour(new OrderBehaviour());
	}

	@Override
	protected void initializeData() {
		dataStore = new OrderDataStore();
		dataStore.setOrder(createOrder());
	}

	private static final long serialVersionUID = -5529204273917293075L;
}
