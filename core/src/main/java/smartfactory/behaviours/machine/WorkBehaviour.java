package smartfactory.behaviours.machine;

import jade.core.behaviours.SimpleBehaviour;
import smartfactory.dataStores.MachineDataStore;
import smartfactory.interactors.machine.Work;

public class WorkBehaviour extends SimpleBehaviour {

	ServiceProvisioningResponderBehaviour interactionBehaviour;

	Work interactor;

	public WorkBehaviour(ServiceProvisioningResponderBehaviour interactionBehaviour, MachineDataStore machineDataStore) {
		this.interactionBehaviour = interactionBehaviour;
		this.interactor = new Work(machineDataStore);
	}

	@Override
	public void action() {
		interactionBehaviour.setResult(interactor.execute(interactionBehaviour.getRequest()));
	}

	@Override
	public boolean done() {
		return interactor.done();
	}

	private static final long serialVersionUID = -3500469822678572098L;
}
