package smartfactory.serviceProvisioning.behaviours;

import jade.core.behaviours.WakerBehaviour;
import smartfactory.models.Resource;
import smartfactory.serviceProvisioning.interactors.ExecutionDeadline;
import smartfactory.utility.AgentDataStore;

public class ExecutionDeadlineBehaviour extends WakerBehaviour {

	ServiceProvisioningResponderBehaviour interactionBehaviour;

	ExecutionDeadline interactor;

	public ExecutionDeadlineBehaviour(ServiceProvisioningResponderBehaviour interactionBehaviour,
			AgentDataStore agentDataStore) {
		super(interactionBehaviour.getAgent(), Resource.DURATION_LIMIT_IN_MILIS);
		this.interactionBehaviour = interactionBehaviour;
		this.interactor = new ExecutionDeadline(agentDataStore);
	}

	@Override
	protected void onWake() {
		interactor.execute(interactionBehaviour.getRequest());
	}

	private static final long serialVersionUID = 9050743659839198854L;
}
