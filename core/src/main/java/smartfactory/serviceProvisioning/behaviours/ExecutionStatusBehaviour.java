package smartfactory.serviceProvisioning.behaviours;

import jade.core.behaviours.TickerBehaviour;
import smartfactory.serviceProvisioning.interactors.ExecutionStatus;
import smartfactory.utility.AgentDataStore;

public class ExecutionStatusBehaviour extends TickerBehaviour {

	ServiceProvisioningResponderBehaviour interactionBehaviour;

	ExecutionStatus interactor;

	public ExecutionStatusBehaviour(ServiceProvisioningResponderBehaviour interactionBehaviour,
			AgentDataStore agentDataStore) {
		super(interactionBehaviour.getAgent(), 500);
		this.interactionBehaviour = interactionBehaviour;
		this.interactor = new ExecutionStatus(agentDataStore);
	}

	@Override
	protected void onTick() {
		interactor.execute(interactionBehaviour.getRequest());
	}

	private static final long serialVersionUID = 8310293249196722957L;
}
