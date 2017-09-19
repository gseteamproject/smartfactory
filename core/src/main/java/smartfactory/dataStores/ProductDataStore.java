package smartfactory.dataStores;

import java.util.List;

import jade.core.behaviours.DataStore;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
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

	public void setAgentsProvidingService(List<DFAgentDescription> agentsDescription) {
		put("agentsDescription", agentsDescription);
	}

	@SuppressWarnings("unchecked")
	public List<DFAgentDescription> getAgentsProvidingService() {
		return (List<DFAgentDescription>) get("agentsDescription");
	}

	public void setRequiredServiceName(String serviceName) {
		put("requiredServiceName", serviceName);
	}

	public String getRequiredServiceName() {
		return (String) get("requiredServiceName");
	}

	public void setAgentProvidingService(DFAgentDescription agentDescription) {
		put("agentDescription", agentDescription);
	}

	public DFAgentDescription getAgentProvidingService() {
		return (DFAgentDescription) get("agentDescription");
	}
}
