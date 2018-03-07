package smartfactory.agents;

import smartfactory.models.AgentService;
import smartfactory.models.Resource;
import smartfactory.models.Warehouse;
import smartfactory.services.Services;

public class WarehouseAgent extends ResourceAgent {

	private static final long serialVersionUID = -5919274387044865830L;

	@Override
	public Resource createPhysicalResource() {
		return new Warehouse();
	}

	@Override
	protected void setupServices() {
		super.setupServices();
		agentServices.addService(new AgentService(Services.store, agentDataStore));
		agentServices.addService(new AgentService(Services.recognition, agentDataStore));
		agentServices.addService(new AgentService(Services.packing, agentDataStore));
	}
}
