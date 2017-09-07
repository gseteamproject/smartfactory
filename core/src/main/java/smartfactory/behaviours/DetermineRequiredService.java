package smartfactory.behaviours;

import jade.core.Agent;
import jade.core.behaviours.DataStore;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.models.Product;

public class DetermineRequiredService extends OneShotBehaviour {

	public DetermineRequiredService(Agent agent, DataStore dataStore) {
		super(agent);
		setDataStore(dataStore);
	}

	@Override
	public void action() {
		ServiceDescription requiredService = new ServiceDescription();
		requiredService.setName(getProduct().getRequiredService());

		setRequiredService(requiredService);
	}

	public Product getProduct() {
		return (Product) getDataStore().get("product");
	}

	public void setRequiredService(ServiceDescription service) {
		getDataStore().put("requiredService", service);
	}

	private static final long serialVersionUID = -2422289734697182917L;
}
