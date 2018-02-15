package smartfactory.behaviours.resource;

import jade.core.behaviours.TickerBehaviour;
import smartfactory.interactors.resource.Status;
import smartfactory.utility.AgentDataStore;

public class StatusBehaviour extends TickerBehaviour {

	Status interactor;

	public StatusBehaviour(ServiceProvisioningResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour.getAgent(), 500);
		this.interactor = new Status(dataStore);
	}

	@Override
	protected void onTick() {
		interactor.execute();
	}

	private static final long serialVersionUID = 8310293249196722957L;
}
