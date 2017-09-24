package smartfactory.dataStores;

import jade.core.behaviours.DataStore;
import smartfactory.models.Order;
import smartfactory.models.Product;
import smartfactory.platform.AgentPlatform;

public class ProductDataStore extends DataStore {

	private static final long serialVersionUID = 6575511248639460129L;

	public ProductDataStore() {
		super();
	}

	public void setProduct(Product product) {
		put("product", product);
	}

	public Product getProduct() {
		return (Product) get("product");
	}

	public void setOrder(Order order) {
		put("order", order);
	}

	public Order getOrder() {
		return (Order) get("order");
	}

	public AgentPlatform getAgentPlatform() {
		return (AgentPlatform) get("agentPlatform");
	}

	public void setAgentPlatform(AgentPlatform agentPlatform) {
		put("agentPlatform", agentPlatform);
	}
}
