package smartfactory.agents;

import smartfactory.services.Services;

public class WarehouseAgent extends MachineAgent {

	@Override
	public String[] getAgentServices() {
		return new String[] { Services.store, Services.recognition };
	}

	private static final long serialVersionUID = -5919274387044865830L;
}
