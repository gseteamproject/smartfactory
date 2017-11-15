package smartfactory.agents;

import smartfactory.models.CleaningStation;
import smartfactory.models.Machine;

public class CleaningStationAgent extends MachineAgent {

	@Override
	public Machine createMachine() {
		return new CleaningStation();
	}

	private static final long serialVersionUID = 7802182814289213994L;
}
