package smartfactory.interactors.resource;

import smartfactory.interactors.Interactor;
import smartfactory.utility.AgentDataStore;

public class Status extends Interactor {

	public Status(AgentDataStore dataStore) {
		super(dataStore);
	}

	public void execute() {
		agentDataStore.getResource().getStatus();

		// content = operation name
		String operationName = agentDataStore.getActivityRequest().getContent();

		if (agentDataStore.getResource().hasExecuted(operationName)) {
			agentDataStore.getEventSubsribers().notifyAll("operation-completed");
		}
	}
}
