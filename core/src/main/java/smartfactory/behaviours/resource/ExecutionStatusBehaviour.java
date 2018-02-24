package smartfactory.behaviours.resource;

import jade.core.behaviours.TickerBehaviour;
import smartfactory.interactors.resource.ExecutionStatus;
import smartfactory.utility.AgentDataStore;

public class ExecutionStatusBehaviour extends TickerBehaviour {

	ExecutionStatus interactor;

	public ExecutionStatusBehaviour(ServiceProvisioningResponderBehaviour interactionBehaviour,
			AgentDataStore agentDataStore) {
		super(interactionBehaviour.getAgent(), 500);
		this.interactor = new ExecutionStatus(agentDataStore);
	}

	@Override
	protected void onTick() {
		interactor.execute();
	}

	private static final long serialVersionUID = 8310293249196722957L;
}
