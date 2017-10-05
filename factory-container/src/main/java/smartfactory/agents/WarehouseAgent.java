package smartfactory.agents;

public class WarehouseAgent extends MachineAgent {

	@Override
	public String[] getAgentServices() {
		return new String[] { "store", "recognition" };
	}

	private static final long serialVersionUID = -5919274387044865830L;
}
