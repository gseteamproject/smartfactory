package smartfactory.behaviours.machine;

import jade.core.behaviours.OneShotBehaviour;
import smartfactory.dataStores.MachineDataStore;
import smartfactory.interactors.machine.Decision;

public class DecisionBehaviour extends OneShotBehaviour {

	ServiceProvisioningResponderBehaviour interactionBehaviour;

	Decision interactor;

	public DecisionBehaviour(ServiceProvisioningResponderBehaviour interactionBehaviour, MachineDataStore machineDataStore) {
		this.interactionBehaviour = interactionBehaviour;
		this.interactor = new Decision(machineDataStore);
	}

	@Override
	public void action() {
		interactionBehaviour.setResponse(interactor.execute(interactionBehaviour.getRequest()));
	}

	private static final long serialVersionUID = -7477532730880615646L;
}
