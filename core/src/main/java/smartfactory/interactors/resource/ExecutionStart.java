package smartfactory.interactors.resource;

import jade.lang.acl.ACLMessage;
import smartfactory.interactors.Interactor;
import smartfactory.utility.AgentDataStore;

public class ExecutionStart extends Interactor {

	public ExecutionStart(AgentDataStore dataStore) {
		super(dataStore);
	}

	public void execute(ACLMessage request) {
		agentDataStore.setActivityRequest(request);
		// content = operation name
		String operationName = request.getContent();

		agentDataStore.getResource().execute(operationName);
	}

	public boolean done() {
		// TODO : check if this is necessary
		return true;
	}
}
