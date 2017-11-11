package smartfactory.behaviours.machine;

import jade.core.behaviours.OneShotBehaviour;
import smartfactory.dataStores.MachineDataStore;
import smartfactory.interactors.machine.DecisionInteractor;

public class DecisionBehaviour extends OneShotBehaviour {

	ActivityResponderBehaviour interactionBehaviour;

	DecisionInteractor interactor;

	public DecisionBehaviour(ActivityResponderBehaviour interactionBehaviour, MachineDataStore machineDataStore) {
		this.interactionBehaviour = interactionBehaviour;
		this.interactor = new DecisionInteractor(machineDataStore);
	}

	@Override
	public void action() {
		interactionBehaviour.setResponse(interactor.execute(interactionBehaviour.getRequest()));
	}

	private static final long serialVersionUID = -7477532730880615646L;
}
