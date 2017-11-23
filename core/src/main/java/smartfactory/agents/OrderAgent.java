package smartfactory.agents;

import smartfactory.behaviours.order.OrderBehaviour;
import smartfactory.dataStores.OrderDataStore;
import smartfactory.models.Order;
import smartfactory.models.ProductionProvisioning;
import smartfactory.platform.JADEPlatform;

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
		addBehaviour(new OrderBehaviour(dataStore));
	}

	@Override
	protected void initializeData() {
		dataStore = new OrderDataStore();
		dataStore.setOrder(createOrder());
		dataStore.setProductionProvisioning(new ProductionProvisioning());
		dataStore.setAgentPlatform(new JADEPlatform(this));
	}

	private static final long serialVersionUID = -5529204273917293075L;
}
