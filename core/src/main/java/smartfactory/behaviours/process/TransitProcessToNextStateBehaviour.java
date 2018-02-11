package smartfactory.behaviours.process;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.ProcessDataStore;
import smartfactory.interactors.process.TransitProcessToNextState;

public class TransitProcessToNextStateBehaviour extends OneShotInteractorBehaviour {

	public TransitProcessToNextStateBehaviour(ProcessDataStore dataStore) {
		super(new TransitProcessToNextState(dataStore));
	}

	private static final long serialVersionUID = 8235523498019703146L;
}
