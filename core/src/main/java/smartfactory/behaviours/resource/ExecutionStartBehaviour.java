package smartfactory.behaviours.resource;

import jade.core.behaviours.SimpleBehaviour;
import smartfactory.interactors.resource.ExecutionStart;
import smartfactory.utility.AgentDataStore;

public class ExecutionStartBehaviour extends SimpleBehaviour {

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

	@Override
	public boolean done() {
		return interactor.done();
	}

	private static final long serialVersionUID = -3500469822678572098L;
}
