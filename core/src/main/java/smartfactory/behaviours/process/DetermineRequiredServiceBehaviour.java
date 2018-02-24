package smartfactory.behaviours.process;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.interactors.process.DetermineRequiredService;
import smartfactory.utility.AgentDataStore;

public class DetermineRequiredServiceBehaviour extends OneShotInteractorBehaviour {

	public DetermineRequiredServiceBehaviour(AgentDataStore dataStore) {
		super(new DetermineRequiredService(dataStore));
	}

	private static final long serialVersionUID = 6700501632983375016L;
}
