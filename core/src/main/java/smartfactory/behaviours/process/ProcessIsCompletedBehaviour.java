package smartfactory.behaviours.process;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.ProcessDataStore;
import smartfactory.interactors.process.ProcessIsCompleted;

public class ProcessIsCompletedBehaviour extends OneShotInteractorBehaviour {

	public ProcessIsCompletedBehaviour(ProcessDataStore dataStore) {
		super(new ProcessIsCompleted(dataStore));
	}

	private static final long serialVersionUID = 8566459465592445582L;
}
