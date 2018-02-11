package smartfactory.agents;

import smartfactory.models.CleaningStation;
import smartfactory.models.Resource;

public class CleaningStationAgent extends ResourceAgent {

	@Override
	public Resource createResource() {
		return new CleaningStation();
	}

	private static final long serialVersionUID = 7802182814289213994L;
}
