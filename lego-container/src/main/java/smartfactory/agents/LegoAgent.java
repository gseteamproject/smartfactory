package smartfactory.agents;

import smartfactory.models.AgentService;
import smartfactory.models.Lego;
import smartfactory.models.Resource;
import smartfactory.services.Services;

public class LegoAgent extends ResourceAgent {

	@Override
	public Resource createPhysicalResource() {
		return new Lego();
	}

	@Override
	protected void setupServices() {
		super.setupServices();
		agentDataStore.getAgentServices().addService(new AgentService(Services.cleaning_worker, agentDataStore));
		agentDataStore.getAgentServices().addService(new AgentService(Services.painting_worker, agentDataStore));
		agentDataStore.getAgentServices().addService(new AgentService(Services.transport, agentDataStore));
	}

	private static final long serialVersionUID = 8580835111885945247L;
}
