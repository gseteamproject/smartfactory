package smartfactory.agents;

import smartfactory.models.Factory;
import smartfactory.models.Resource;
import smartfactory.services.BlockProductionService;

public class FactoryAgent extends ResourceAgent {

	private static final long serialVersionUID = 4282751471381265727L;

	@Override
	public Resource createResource() {
		return new Factory();
	}

	@Override
	protected void setupServices() {
		super.setupServices();
		agentServices.addService(new BlockProductionService(agentDataStore));
	}
}
