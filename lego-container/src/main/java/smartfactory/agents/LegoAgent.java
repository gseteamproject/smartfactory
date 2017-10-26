package smartfactory.agents;

import smartfactory.models.Lego;
import smartfactory.models.Machine;

public class LegoAgent extends MachineAgent {

	@Override
	public Machine createMachine() {
		return new Lego();
	}

	private static final long serialVersionUID = 8580835111885945247L;
}
