package smartfactory.behaviours.machine;

import jade.core.behaviours.WakerBehaviour;
import smartfactory.dataStores.MachineDataStore;
import smartfactory.interactors.machine.Deadline;
import smartfactory.models.Machine;

public class DeadlineBehaviour extends WakerBehaviour {

	ServiceProvisioningResponderBehaviour interactionBehaviour;

	Deadline interactor;

	public DeadlineBehaviour(ServiceProvisioningResponderBehaviour interactionBehaviour, MachineDataStore machineDataStore) {
		super(interactionBehaviour.getAgent(), Machine.DURATION_LIMIT * 1000);
		this.interactionBehaviour = interactionBehaviour;
		this.interactor = new Deadline(machineDataStore);
	}

	@Override
	protected void onWake() {
		interactionBehaviour.setResult(interactor.execute(interactionBehaviour.getRequest()));
	}

	private static final long serialVersionUID = 9050743659839198854L;
}
