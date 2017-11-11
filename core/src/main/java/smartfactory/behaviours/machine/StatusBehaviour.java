package smartfactory.behaviours.machine;

import jade.core.behaviours.TickerBehaviour;
import smartfactory.dataStores.MachineDataStore;

public class StatusBehaviour extends TickerBehaviour {

	MachineDataStore dataStore;

	public StatusBehaviour(ActivityResponderBehaviour interactionBehaviour, MachineDataStore machineDataStore) {
		super(interactionBehaviour.getAgent(), 500);
		this.dataStore = machineDataStore;
	}

	@Override
	protected void onTick() {
		dataStore.getMachine().getStatus();
	}

	private static final long serialVersionUID = 8310293249196722957L;
}
