package smartfactory.behaviours.resource;

import jade.core.behaviours.SimpleBehaviour;
import smartfactory.interactors.resource.Work;
import smartfactory.utility.AgentDataStore;

public class WorkBehaviour extends SimpleBehaviour {

	ServiceProvisioningResponderBehaviour interactionBehaviour;

	Work interactor;

	public WorkBehaviour(ServiceProvisioningResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		this.interactionBehaviour = interactionBehaviour;
		this.interactor = new Work(dataStore);
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
