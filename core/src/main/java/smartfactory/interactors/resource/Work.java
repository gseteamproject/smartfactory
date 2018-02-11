package smartfactory.interactors.resource;

import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.ResourceDataStore;

public class Work extends ResourceInteractor {

	public Work(ResourceDataStore dataStore) {
		super(dataStore);
	}

	public ACLMessage execute(ACLMessage request) {
		// content = operation name
		String operationName = request.getContent();

		dataStore.getResource().execute(operationName);

		ACLMessage response = request.createReply();
		response.setPerformative(ACLMessage.INFORM);

		return response;
	}

	public boolean done() {
		return dataStore.getResource().hasExecuted();
	}
}
