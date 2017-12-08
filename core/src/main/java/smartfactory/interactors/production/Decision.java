package smartfactory.interactors.production;

import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.ProductionDataStore;

public class Decision extends ProductionInteractor {

	public Decision(ProductionDataStore dataStore) {
		super(dataStore);
	}

	public ACLMessage execute(ACLMessage request) {
		// content = operation name
		String operationName = request.getContent();

		ACLMessage response = request.createReply();
		if (dataStore.getProduction().willExecute(operationName)) {
			response.setPerformative(ACLMessage.AGREE);

		} else {
			response.setPerformative(ACLMessage.REFUSE);
		}
		return response;
	}
}
