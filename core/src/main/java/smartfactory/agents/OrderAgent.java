package smartfactory.agents;

import smartfactory.behaviours.order.OrderBehaviour;
import smartfactory.models.Order;

public class OrderAgent extends SmartFactoryAgent {

	private static final long serialVersionUID = -5529204273917293075L;

	public static String getUniqueName() {
		return "order-" + Long.toString(System.currentTimeMillis());
	}

	private Order order;

	public Order createOrder() {
		return new Order();
	}

	@Override
	protected void initializeBehaviours() {
		addBehaviour(new OrderBehaviour());
	}

	@Override
	protected void initializeData() {
		order = createOrder();
	}
}
