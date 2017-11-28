package smartfactory.interactors.production;

import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.ProductionDataStore;

public class Work extends ProductionInteractor {

	public Work(ProductionDataStore dataStore) {
		super(dataStore);
	}

	public ACLMessage execute(ACLMessage request) {
		dataStore.getProduction().execute("production-xxx");

		ACLMessage response = request.createReply();
		response.setPerformative(ACLMessage.INFORM);

		return response;
	}

	public boolean done() {
		return dataStore.getProduction().hasExecuted();
	}
}
