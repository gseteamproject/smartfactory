package smartfactory.interactors.resource;

import jade.lang.acl.ACLMessage;
import smartfactory.interactors.Interactor;
import smartfactory.utility.AgentDataStore;

public class ExecutionStatus extends Interactor {

	public ExecutionStatus(AgentDataStore dataStore) {
		super(dataStore);
	}

	public void execute(ACLMessage request) {
		// content = operation name
		String serviceName = request.getContent();
		agentDataStore.getAgentServices().statusOfService(serviceName);
	}
}
