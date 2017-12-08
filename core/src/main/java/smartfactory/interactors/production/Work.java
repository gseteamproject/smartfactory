package smartfactory.interactors.production;

import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.ProductionDataStore;

public class Work extends ProductionInteractor {

	public Work(ProductionDataStore dataStore) {
		super(dataStore);
	}

	public ACLMessage execute(ACLMessage request) {
		// content = operation name
		String operationName = request.getContent();

		dataStore.getProduction().execute(operationName);

		ACLMessage response = request.createReply();
		response.setPerformative(ACLMessage.INFORM);

		return response;
	}

	public boolean done() {
		return dataStore.getProduction().hasExecuted();
	}
}
