package smartfactory.interactors.resource;

import jade.lang.acl.ACLMessage;
import smartfactory.interactors.Interactor;
import smartfactory.utility.AgentDataStore;

public class ExecutionDeadline extends Interactor {

	public ExecutionDeadline(AgentDataStore dataStore) {
		super(dataStore);
	}

	public void execute(ACLMessage request) {
		// content = operation name
		String serviceName = request.getContent();
		agentDataStore.getAgentServices().terminateService(serviceName);
	}
}
