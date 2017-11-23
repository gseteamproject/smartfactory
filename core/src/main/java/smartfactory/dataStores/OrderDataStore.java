package smartfactory.dataStores;

import smartfactory.models.Order;
import smartfactory.models.ProductionProvisioning;

public class OrderDataStore extends SmartFactoryDataStore {

	public void setOrder(Order order) {
		put("order", order);
	}

	public Order getOrder() {
		return (Order) get("order");
	}

	public void setProductionProvisioning(ProductionProvisioning productionProvisioning) {
		// TODO Auto-generated method stub
	}

	private static final long serialVersionUID = 1235583692743985234L;
}
