package smartfactory.agents;

import smartfactory.models.Machine;
import smartfactory.models.Warehouse;

public class WarehouseAgent extends MachineAgent {

	@Override
	public Machine createMachine() {
		return new Warehouse();
	}

	private static final long serialVersionUID = -5919274387044865830L;
}
