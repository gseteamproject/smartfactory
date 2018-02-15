package smartfactory.behaviours.process;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.interactors.process.ProcessIsIncorrect;
import smartfactory.utility.AgentDataStore;

public class ProcessIsIncorrectBehaviour extends OneShotInteractorBehaviour {

	public ProcessIsIncorrectBehaviour(AgentDataStore dataStore) {
		super(new ProcessIsIncorrect(dataStore));
	}

	private static final long serialVersionUID = -3775938764499511209L;
}
