package smartfactory.behaviours.process;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.interactors.process.FindAgentsProvidingService;
import smartfactory.utility.AgentDataStore;

public class FindAgentsProvidingServiceBehaviour extends OneShotInteractorBehaviour {

	public FindAgentsProvidingServiceBehaviour(AgentDataStore dataStore) {
		super(new FindAgentsProvidingService(dataStore));
	}

	private static final long serialVersionUID = -3668701638612933859L;
}
