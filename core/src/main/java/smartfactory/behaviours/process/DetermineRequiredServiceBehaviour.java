package smartfactory.behaviours.process;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.ProcessDataStore;
import smartfactory.interactors.process.DetermineRequiredService;

public class DetermineRequiredServiceBehaviour extends OneShotInteractorBehaviour {

	public DetermineRequiredServiceBehaviour(ProcessDataStore dataStore) {
		super(new DetermineRequiredService(dataStore));
	}

	private static final long serialVersionUID = 6700501632983375016L;
}
