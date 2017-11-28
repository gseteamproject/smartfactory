package smartfactory.behaviours.machine;

import jade.core.behaviours.TickerBehaviour;
import smartfactory.dataStores.MachineDataStore;
import smartfactory.interactors.machine.Status;

public class StatusBehaviour extends TickerBehaviour {

	Status interactor;

	public StatusBehaviour(ServiceProvisioningResponderBehaviour interactionBehaviour, MachineDataStore dataStore) {
		super(interactionBehaviour.getAgent(), 500);
		this.interactor = new Status(dataStore);
	}

	@Override
	protected void onTick() {
		interactor.execute();
	}

	private static final long serialVersionUID = 8310293249196722957L;
}
