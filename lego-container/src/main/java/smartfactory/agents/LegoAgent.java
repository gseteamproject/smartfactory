package smartfactory.agents;

import smartfactory.services.Services;

public class LegoAgent extends MachineAgent {

	@Override
	public String[] getAgentServices() {
		return new String[] { Services.cleaning };
	}

	private static final long serialVersionUID = 8580835111885945247L;
}
