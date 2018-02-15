package smartfactory.behaviours.process;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.interactors.process.NoAgentsProvidingService;
import smartfactory.utility.AgentDataStore;

public class NoAgentsProvidingServiceBehaviour extends OneShotInteractorBehaviour {

	public NoAgentsProvidingServiceBehaviour(AgentDataStore dataStore) {
		super(new NoAgentsProvidingService(dataStore));
	}

	private static final long serialVersionUID = 5429114934521142842L;
}
