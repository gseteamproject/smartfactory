package smartfactory.interactors.production;

import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.ProductionDataStore;

public class Deadline extends ProductionInteractor {

	public Deadline(ProductionDataStore dataStore) {
		super(dataStore);
	}

	public ACLMessage execute(ACLMessage request) {
		// content = operation name
		String operationName = request.getContent();

		dataStore.getProduction().terminate(operationName);

		ACLMessage response = request.createReply();
		response.setPerformative(ACLMessage.FAILURE);

		return response;
	}
}
