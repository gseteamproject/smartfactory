package smartfactory.behaviours.process;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.ProcessDataStore;
import smartfactory.interactors.process.NoAgentsProvidingService;

public class NoAgentsProvidingServiceBehaviour extends OneShotInteractorBehaviour {

	public NoAgentsProvidingServiceBehaviour(ProcessDataStore dataStore) {
		super(new NoAgentsProvidingService(dataStore));
	}

	private static final long serialVersionUID = 5429114934521142842L;
}
