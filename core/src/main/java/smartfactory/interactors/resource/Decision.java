package smartfactory.interactors.resource;

import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.ResourceDataStore;

public class Decision extends ResourceInteractor {

	public Decision(ResourceDataStore dataStore) {
		super(dataStore);
	}

	public ACLMessage execute(ACLMessage request) {
		// content = operation name
		String operationName = request.getContent();

		ACLMessage response = request.createReply();
		if (dataStore.getResource().willExecute(operationName)) {
			response.setPerformative(ACLMessage.AGREE);

		} else {
			response.setPerformative(ACLMessage.REFUSE);
		}
		return response;
	}
}
