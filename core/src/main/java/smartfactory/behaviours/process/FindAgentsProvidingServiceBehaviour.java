package smartfactory.behaviours.process;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.ProcessDataStore;
import smartfactory.interactors.process.FindAgentsProvidingService;

public class FindAgentsProvidingServiceBehaviour extends OneShotInteractorBehaviour {

	public FindAgentsProvidingServiceBehaviour(ProcessDataStore dataStore) {
		super(new FindAgentsProvidingService(dataStore));
	}

	private static final long serialVersionUID = -3668701638612933859L;
}
