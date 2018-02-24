package smartfactory.behaviours.process;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.interactors.process.TransitProcessToNextOperation;
import smartfactory.utility.AgentDataStore;

public class TransitProcessToNextOperationBehaviour extends OneShotInteractorBehaviour {

	public TransitProcessToNextOperationBehaviour(AgentDataStore dataStore) {
		super(new TransitProcessToNextOperation(dataStore));
	}

	private static final long serialVersionUID = 8235523498019703146L;
}
