package smartfactory.behaviours.process;

import smartfactory.behaviours.OneShotInteractorBehaviour;
import smartfactory.interactors.process.ProcessIsCompleted;
import smartfactory.utility.AgentDataStore;

public class ProcessIsCompletedBehaviour extends OneShotInteractorBehaviour {

	public ProcessIsCompletedBehaviour(AgentDataStore dataObject) {
		super(new ProcessIsCompleted(dataObject));
	}

	private static final long serialVersionUID = 8566459465592445582L;
}
