package smartfactory.dataStores;

import jade.core.behaviours.DataStore;
import smartfactory.models.ServiceProvisioning;
import smartfactory.models.Product;
import smartfactory.platform.AgentPlatform;

public class ProductDataStore extends DataStore {

	public void setProduct(Product product) {
		put("product", product);
	}

	public Product getProduct() {
		return (Product) get("product");
	}

	public void setServiceProvisioning(ServiceProvisioning serviceProvisioning) {
		put("service-provisioning", serviceProvisioning);
	}

	public ServiceProvisioning getServiceProvisioning() {
		return (ServiceProvisioning) get("service-provisioning");
	}

	public AgentPlatform getAgentPlatform() {
		return (AgentPlatform) get("agentPlatform");
	}

	public void setAgentPlatform(AgentPlatform agentPlatform) {
		put("agentPlatform", agentPlatform);
	}

	private static final long serialVersionUID = 6575511248639460129L;
}
