package smartfactory.behaviours.machine;

import jade.core.behaviours.WakerBehaviour;
import smartfactory.dataStores.MachineDataStore;
import smartfactory.interactors.machine.DeadlineInteractor;
import smartfactory.models.Machine;

public class DeadlineBehaviour extends WakerBehaviour {

	ActivityResponderBehaviour interactionBehaviour;

	DeadlineInteractor interactor;

	public DeadlineBehaviour(ActivityResponderBehaviour interactionBehaviour, MachineDataStore machineDataStore) {
		super(interactionBehaviour.getAgent(), Machine.DURATION_LIMIT * 1000);
		this.interactionBehaviour = interactionBehaviour;
		this.interactor = new DeadlineInteractor(machineDataStore);
	}

	@Override
	protected void onWake() {
		interactionBehaviour.setResult(interactor.execute(interactionBehaviour.getRequest()));
	}

	private static final long serialVersionUID = 9050743659839198854L;
}
