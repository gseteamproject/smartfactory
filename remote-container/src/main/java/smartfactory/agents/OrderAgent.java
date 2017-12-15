package smartfactory.agents;

import smartfactory.models.Order;
import smartfactory.models.Product;

public class OrderAgent extends ProductAgent {

	public static String getUniqueName() {
		return "order-" + Long.toString(System.currentTimeMillis());
	}

	@Override
	public Product createProduct() {
		return new Order();
	}

	private static final long serialVersionUID = -5529204273917293075L;
}
