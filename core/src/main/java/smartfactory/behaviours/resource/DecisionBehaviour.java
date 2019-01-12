package smartfactory.behaviours.resource;

import jade.core.behaviours.OneShotBehaviour;
import smartfactory.behaviours.base.ServiceProvisioningResponderBehaviour;
import smartfactory.interactors.resource.Decision;
import smartfactory.utility.AgentDataStore;

public class DecisionBehaviour extends OneShotBehaviour {

	ServiceProvisioningResponderBehaviour interactionBehaviour;

	Decision interactor;

	public DecisionBehaviour(ServiceProvisioningResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		this.interactionBehaviour = interactionBehaviour;
		this.interactor = new Decision(dataStore);
	}

	@Override
	public void action() {
		interactionBehaviour.setResponse(interactor.execute(interactionBehaviour.getRequest()));
	}

	private static final long serialVersionUID = -7477532730880615646L;
}
