package smartfactory.behaviours.resource;

import jade.core.behaviours.OneShotBehaviour;
import smartfactory.dataStores.ResourceDataStore;
import smartfactory.interactors.resource.Decision;

public class DecisionBehaviour extends OneShotBehaviour {

	ServiceProvisioningResponderBehaviour interactionBehaviour;

	Decision interactor;

	public DecisionBehaviour(ServiceProvisioningResponderBehaviour interactionBehaviour, ResourceDataStore dataStore) {
		this.interactionBehaviour = interactionBehaviour;
		this.interactor = new Decision(dataStore);
	}

	@Override
	public void action() {
		interactionBehaviour.setResponse(interactor.execute(interactionBehaviour.getRequest()));
	}

	private static final long serialVersionUID = -7477532730880615646L;
}
