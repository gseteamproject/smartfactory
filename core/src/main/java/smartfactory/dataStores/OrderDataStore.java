package smartfactory.dataStores;

import smartfactory.models.Order;

public class OrderDataStore extends SmartFactoryDataStore {

	public void setOrder(Order order) {
		put("order", order);
	}

	public Order getOrder() {
		return (Order) get("order");
	}

	private static final long serialVersionUID = 1235583692743985234L;
}
