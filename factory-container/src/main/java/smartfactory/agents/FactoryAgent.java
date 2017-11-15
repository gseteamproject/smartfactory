package smartfactory.agents;

import smartfactory.models.Factory;
import smartfactory.models.Machine;

public class FactoryAgent extends MachineAgent {

	@Override
	public Machine createMachine() {
		return new Factory();
	}

	private static final long serialVersionUID = 4282751471381265727L;
}
