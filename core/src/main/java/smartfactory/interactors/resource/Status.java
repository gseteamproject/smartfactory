package smartfactory.interactors.resource;

import smartfactory.interactors.Interactor;
import smartfactory.utility.AgentDataStore;

public class Status extends Interactor {

	public Status(AgentDataStore dataStore) {
		super(dataStore);
	}

	public void execute() {
		agentDataStore.getResource().getStatus();
	}
}
