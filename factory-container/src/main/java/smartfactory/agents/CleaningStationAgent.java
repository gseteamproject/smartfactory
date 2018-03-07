package smartfactory.agents;

import smartfactory.models.CleaningStation;
import smartfactory.models.Resource;
import smartfactory.services.CleaningService;

public class CleaningStationAgent extends ResourceAgent {

	private static final long serialVersionUID = 7802182814289213994L;

	@Override
	public Resource createPhysicalResource() {
		return new CleaningStation();
	}

	@Override
	protected void setupServices() {
		super.setupServices();
		agentServices.addService(new CleaningService(agentDataStore));
	}
}
