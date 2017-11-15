package smartfactory.agents;

import smartfactory.models.Machine;
import smartfactory.models.PaintingStation;

public class PaintingStationAgent extends MachineAgent {

	@Override
	public Machine createMachine() {
		return new PaintingStation();
	}

	private static final long serialVersionUID = 5122917367234156798L;
}
