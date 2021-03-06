package smartfactory.serviceProvisioning.behaviours;

import jade.core.behaviours.OneShotBehaviour;
import smartfactory.serviceProvisioning.interactors.ExecutionStart;
import smartfactory.utility.AgentDataStore;

public class ExecutionStartBehaviour extends OneShotBehaviour {

	ServiceProvisioningResponderBehaviour interactionBehaviour;

	ExecutionStart interactor;

	public ExecutionStartBehaviour(ServiceProvisioningResponderBehaviour interactionBehaviour,
			AgentDataStore agentDataStore) {
		this.interactionBehaviour = interactionBehaviour;
		this.interactor = new ExecutionStart(agentDataStore);
	}

	@Override
	public void action() {
		interactor.execute(interactionBehaviour.getRequest());
	}

	private static final long serialVersionUID = -3500469822678572098L;
}
