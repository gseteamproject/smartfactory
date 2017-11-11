package smartfactory.behaviours.machine;

import jade.core.behaviours.TickerBehaviour;
import smartfactory.dataStores.MachineDataStore;
import smartfactory.interactors.machine.StatusInteractor;

public class StatusBehaviour extends TickerBehaviour {

	StatusInteractor interactor;

	public StatusBehaviour(ActivityResponderBehaviour interactionBehaviour, MachineDataStore machineDataStore) {
		super(interactionBehaviour.getAgent(), 500);
		this.interactor = new StatusInteractor(machineDataStore);
	}

	@Override
	protected void onTick() {
		interactor.execute();
	}

	private static final long serialVersionUID = 8310293249196722957L;
}
