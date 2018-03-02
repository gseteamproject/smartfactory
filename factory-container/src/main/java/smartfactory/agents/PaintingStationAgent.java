package smartfactory.agents;

import smartfactory.models.Resource;
import smartfactory.services.PaintingService;
import smartfactory.models.PaintingStation;

public class PaintingStationAgent extends ResourceAgent {

	private static final long serialVersionUID = 5122917367234156798L;

	@Override
	public Resource createResource() {
		return new PaintingStation();
	}

	@Override
	protected void setupServices() {
		super.setupServices();
		agentServices.addService(new PaintingService(agentDataStore));
	}
}
