package smartfactory.behaviours.resource;

import jade.core.behaviours.WakerBehaviour;
import smartfactory.interactors.resource.Deadline;
import smartfactory.models.Resource;
import smartfactory.utility.AgentDataStore;

public class DeadlineBehaviour extends WakerBehaviour {

	ServiceProvisioningResponderBehaviour interactionBehaviour;

	Deadline interactor;

	public DeadlineBehaviour(ServiceProvisioningResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour.getAgent(), Resource.DURATION_LIMIT * 1000);
		this.interactionBehaviour = interactionBehaviour;
		this.interactor = new Deadline(dataStore);
	}

	@Override
	protected void onWake() {
		interactor.execute(interactionBehaviour.getRequest());
	}

	private static final long serialVersionUID = 9050743659839198854L;
}
