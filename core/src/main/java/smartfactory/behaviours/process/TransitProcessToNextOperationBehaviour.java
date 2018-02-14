package smartfactory.behaviours.process;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.ProcessDataStore;
import smartfactory.interactors.process.TransitProcessToNextOperation;

public class TransitProcessToNextOperationBehaviour extends OneShotInteractorBehaviour {

	public TransitProcessToNextOperationBehaviour(ProcessDataStore dataStore) {
		super(new TransitProcessToNextOperation(dataStore));
	}

	private static final long serialVersionUID = 8235523498019703146L;
}
