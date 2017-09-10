package smartfactory.accessors;

import java.util.List;

import jade.core.behaviours.DataStore;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.models.Product;

public class ProductBehaviourDataAccessor {

	private DataStore dataStore;

	public ProductBehaviourDataAccessor(DataStore dataStore) {
		this.dataStore = dataStore;
	}

	public void setProduct(Product product) {
		dataStore.put("product", product);
	}

	public Product getProduct() {
		return (Product) dataStore.get("product");
	}

	public void setRequiredService(ServiceDescription service) {
		dataStore.put("requiredService", service);
	}

	public ServiceDescription getRequiredService() {
		return (ServiceDescription) dataStore.get("requiredService");
	}

	public void setAgentsProvidingService(List<DFAgentDescription> agentsDescription) {
		dataStore.put("agentsDescription", agentsDescription);
	}
}
