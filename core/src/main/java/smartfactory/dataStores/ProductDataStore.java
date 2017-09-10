package smartfactory.dataStores;

import java.util.List;

import jade.core.behaviours.DataStore;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
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

	public void setRequiredService(ServiceDescription service) {
		put("requiredService", service);
	}

	public ServiceDescription getRequiredService() {
		return (ServiceDescription) get("requiredService");
	}

	public void setAgentsProvidingService(List<DFAgentDescription> agentsDescription) {
		put("agentsDescription", agentsDescription);
	}
}
