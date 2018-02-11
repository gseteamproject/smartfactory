package smartfactory.behaviours.process;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.dataStores.ProcessDataStore;
import smartfactory.interactors.process.ProcessIsIncorrect;

public class ProcessIsIncorrectBehaviour extends OneShotInteractorBehaviour {

	public ProcessIsIncorrectBehaviour(ProcessDataStore dataStore) {
		super(new ProcessIsIncorrect(dataStore));
	}

	private static final long serialVersionUID = -3775938764499511209L;
}
