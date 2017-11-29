package smartfactory.dataStores;

import smartfactory.models.Order;
import smartfactory.models.ProductionProvisioning;

public class OrderDataStore extends BaseDataStore {

	public void setOrder(Order order) {
		put("order", order);
	}

	public Order getOrder() {
		return (Order) get("order");
	}

	public void setProductionProvisioning(ProductionProvisioning productionProvisioning) {
		put("productionProvisioning", productionProvisioning);
	}

	public ProductionProvisioning getProductionProvisioning() {
		return (ProductionProvisioning) get("productionProvisioning");
	}

	private static final long serialVersionUID = 1235583692743985234L;
}
