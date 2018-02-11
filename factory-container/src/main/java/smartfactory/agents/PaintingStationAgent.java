package smartfactory.agents;

import smartfactory.models.Resource;
import smartfactory.models.PaintingStation;

public class PaintingStationAgent extends ResourceAgent {

	@Override
	public Resource createResource() {
		return new PaintingStation();
	}

	private static final long serialVersionUID = 5122917367234156798L;
}
