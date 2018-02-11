package smartfactory.interactors.resource;

import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.ResourceDataStore;

public class Deadline extends ResourceInteractor {

	public Deadline(ResourceDataStore dataStore) {
		super(dataStore);
	}

	public ACLMessage execute(ACLMessage request) {
		// content = operation name
		String operationName = request.getContent();

		dataStore.getResource().terminate(operationName);

		ACLMessage response = request.createReply();
		response.setPerformative(ACLMessage.FAILURE);

		return response;
	}
}
