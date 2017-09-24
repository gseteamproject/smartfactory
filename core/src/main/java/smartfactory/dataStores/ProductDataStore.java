package smartfactory.dataStores;

import jade.core.behaviours.DataStore;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import smartfactory.models.Order;
import smartfactory.models.Product;

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

	public void setAgentProvidingService(DFAgentDescription agentDescription) {
		put("agentDescription", agentDescription);
	}

	public DFAgentDescription getAgentProvidingService() {
		return (DFAgentDescription) get("agentDescription");
	}

	public void setOrder(Order order) {
		put("order", order);
	}

	public Order getOrder() {
		return (Order) get("order");
	}
}
