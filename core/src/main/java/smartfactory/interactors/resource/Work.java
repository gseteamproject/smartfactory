package smartfactory.interactors.resource;

import jade.lang.acl.ACLMessage;
import smartfactory.interactors.Interactor;
import smartfactory.utility.AgentDataStore;

public class Work extends Interactor {

	public Work(AgentDataStore dataStore) {
		super(dataStore);
	}

	public void execute(ACLMessage request) {
		agentDataStore.setActivityRequest(request);
		// content = operation name
		String operationName = request.getContent();

		agentDataStore.getResource().execute(operationName);

		// TODO : remove
		/*
		ACLMessage response = request.createReply();
		response.setPerformative(ACLMessage.INFORM);

		agentDataStore.setActivityResult(response);
		*/
	}

	public boolean done() {
		return true;
	}
}
