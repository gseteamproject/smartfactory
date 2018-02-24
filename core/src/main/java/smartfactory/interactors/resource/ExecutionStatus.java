package smartfactory.interactors.resource;

import smartfactory.interactors.Interactor;
import smartfactory.utility.AgentDataStore;

public class ExecutionStatus extends Interactor {

	public ExecutionStatus(AgentDataStore dataStore) {
		super(dataStore);
	}

	public void execute() {
		agentDataStore.getResource().getStatus();
	}
}
