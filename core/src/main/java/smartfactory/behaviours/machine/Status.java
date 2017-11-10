package smartfactory.behaviours.machine;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import smartfactory.dataStores.MachineDataStore;

public class Status extends TickerBehaviour {

	MachineDataStore dataStore;

	public Status(Agent a, MachineDataStore machineDataStore) {
		super(a, 500);
		this.dataStore = machineDataStore;
	}

	@Override
	protected void onTick() {
		dataStore.getMachine().getStatus();
	}

	private static final long serialVersionUID = 8310293249196722957L;
}
